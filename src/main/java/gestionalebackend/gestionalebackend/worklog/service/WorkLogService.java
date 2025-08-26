package gestionalebackend.gestionalebackend.worklog.service;

import gestionalebackend.gestionalebackend.worklog.dto.WorkLogDTO;
import gestionalebackend.gestionalebackend.worklog.dto.YearlyConsuntivoDTO;

import java.time.LocalDate;
import java.util.List;

public interface WorkLogService {
    
    WorkLogDTO createWorkLog(WorkLogDTO workLogDTO);
    
    WorkLogDTO updateWorkLog(Long id, WorkLogDTO workLogDTO);
    
    void deleteWorkLog(Long id);
    
    WorkLogDTO getWorkLogById(Long id);
    
    List<WorkLogDTO> getWorkLogsByEmployee(String employeeEmail, LocalDate startDate, LocalDate endDate);
    
    List<WorkLogDTO> getWorkLogsByProject(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<WorkLogDTO> getWorkLogsByDateRange(LocalDate startDate, LocalDate endDate);
    
    List<WorkLogDTO> getWorkLogsByEmployeeAndProject(String employeeEmail, Long projectId, LocalDate startDate, LocalDate endDate);
    
    YearlyConsuntivoDTO getYearlyConsuntivo(Integer year, Long projectId);
    
    List<WorkLogDTO> getMonthlyWorkLogs(Integer year, Integer month);
    
    List<WorkLogDTO> getEmployeeYearlyWorkLogs(String employeeEmail, Integer year);
}