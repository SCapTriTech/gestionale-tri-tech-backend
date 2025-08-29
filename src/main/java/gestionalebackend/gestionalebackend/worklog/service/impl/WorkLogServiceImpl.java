package gestionalebackend.gestionalebackend.worklog.service.impl;

import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;
import gestionalebackend.gestionalebackend.worklog.dto.EmployeeYearlyData;
import gestionalebackend.gestionalebackend.worklog.dto.WorkLogDTO;
import gestionalebackend.gestionalebackend.worklog.dto.YearlyConsuntivoDTO;
import gestionalebackend.gestionalebackend.worklog.mapper.WorkLogMapper;
import gestionalebackend.gestionalebackend.worklog.model.DayType;
import gestionalebackend.gestionalebackend.worklog.model.WorkLog;
import gestionalebackend.gestionalebackend.worklog.repository.WorkLogRepository;
import gestionalebackend.gestionalebackend.worklog.service.WorkLogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkLogServiceImpl implements WorkLogService {
    
    private final WorkLogRepository workLogRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    
    public WorkLogServiceImpl(WorkLogRepository workLogRepository, 
                             EmployeeRepository employeeRepository,
                             ProjectRepository projectRepository) {
        this.workLogRepository = workLogRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }
    
    @Override
    public WorkLogDTO createWorkLog(WorkLogDTO workLogDTO) {
        WorkLog workLog = WorkLogMapper.convertToDAO(workLogDTO, employeeRepository, projectRepository);
        WorkLog savedWorkLog = workLogRepository.save(workLog);
        return WorkLogMapper.convertToDTO(savedWorkLog);
    }
    
    @Override
    public WorkLogDTO updateWorkLog(Long id, WorkLogDTO workLogDTO) {
        WorkLog workLog = workLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkLog non trovato con id: " + id));
        
        if (workLogDTO.projectId() != null) {
            workLog.setProject(projectRepository.findById(workLogDTO.projectId()).orElse(null));
        }
        if (workLogDTO.data() != null) {
            workLog.setData(workLogDTO.data());
        }
        if (workLogDTO.ore() != null) {
            workLog.setOre(workLogDTO.ore());
        }
        if (workLogDTO.tipo() != null) {
            workLog.setTipo(workLogDTO.tipo());
        }
        if (workLogDTO.note() != null) {
            workLog.setNote(workLogDTO.note());
        }
        
        WorkLog updatedWorkLog = workLogRepository.save(workLog);
        return WorkLogMapper.convertToDTO(updatedWorkLog);
    }
    
    @Override
    public void deleteWorkLog(Long id) {
        if (!workLogRepository.existsById(id)) {
            throw new EntityNotFoundException("WorkLog non trovato con id: " + id);
        }
        workLogRepository.deleteById(id);
    }
    
    @Override
    public WorkLogDTO getWorkLogById(Long id) {
        WorkLog workLog = workLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkLog non trovato con id: " + id));
        return WorkLogMapper.convertToDTO(workLog);
    }
    
    @Override
    public List<WorkLogDTO> getWorkLogsByEmployee(String employeeEmail, LocalDate startDate, LocalDate endDate) {
        return workLogRepository.findByEmployeeEmailAndDataBetween(employeeEmail, startDate, endDate)
                .stream()
                .map(WorkLogMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<WorkLogDTO> getWorkLogsByProject(Long projectId, LocalDate startDate, LocalDate endDate) {
        return workLogRepository.findByProjectIdAndDataBetween(projectId, startDate, endDate)
                .stream()
                .map(WorkLogMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<WorkLogDTO> getWorkLogsByDateRange(LocalDate startDate, LocalDate endDate) {
        return workLogRepository.findByDataBetween(startDate, endDate)
                .stream()
                .map(WorkLogMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<WorkLogDTO> getWorkLogsByEmployeeAndProject(String employeeEmail, Long projectId, 
                                                            LocalDate startDate, LocalDate endDate) {
        return workLogRepository.findByEmployeeEmailAndProjectIdAndDataBetween(
                employeeEmail, projectId, startDate, endDate)
                .stream()
                .map(WorkLogMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public YearlyConsuntivoDTO getYearlyConsuntivo(Integer year, Long projectId) {
        List<WorkLog> workLogs;
        String projectName = null;
        
        if (projectId != null) {
            workLogs = workLogRepository.findByProjectIdAndDataBetween(
                    projectId, 
                    LocalDate.of(year, 1, 1), 
                    LocalDate.of(year, 12, 31)
            );
            projectName = projectRepository.findById(projectId)
                    .map(p -> p.getNome())
                    .orElse(null);
        } else {
            workLogs = workLogRepository.findByYear(year);
        }
        
        // Group by employee
        Map<String, List<WorkLog>> employeeWorkLogs = workLogs.stream()
                .collect(Collectors.groupingBy(wl -> wl.getEmployee().getEmail()));
        
        List<EmployeeYearlyData> employeeDataList = new ArrayList<>();
        
        for (Map.Entry<String, List<WorkLog>> entry : employeeWorkLogs.entrySet()) {
            String email = entry.getKey();
            List<WorkLog> empLogs = entry.getValue();
            
            // Build monthly data structure
            Map<Integer, Map<Integer, Map<DayType, BigDecimal>>> monthlyData = new HashMap<>();
            
            for (WorkLog log : empLogs) {
                int month = log.getData().getMonthValue();
                int day = log.getData().getDayOfMonth();
                
                monthlyData.computeIfAbsent(month, k -> new HashMap<>())
                          .computeIfAbsent(day, k -> new HashMap<>())
                          .put(log.getTipo(), log.getOre());
            }
            
            String employeeName = empLogs.get(0).getEmployee().getNome() + " " + 
                                 empLogs.get(0).getEmployee().getCognome();
            
            employeeDataList.add(new EmployeeYearlyData(email, employeeName, monthlyData));
        }
        
        return new YearlyConsuntivoDTO(year, projectId, projectName, employeeDataList);
    }
    
    @Override
    public List<WorkLogDTO> getMonthlyWorkLogs(Integer year, Integer month) {
        return workLogRepository.findByYearAndMonth(year, month)
                .stream()
                .map(WorkLogMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<WorkLogDTO> getEmployeeYearlyWorkLogs(String employeeEmail, Integer year) {
        return workLogRepository.findByEmployeeAndYear(employeeEmail, year)
                .stream()
                .map(WorkLogMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
}