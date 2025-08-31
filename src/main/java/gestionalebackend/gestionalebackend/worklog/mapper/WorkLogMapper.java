package gestionalebackend.gestionalebackend.worklog.mapper;

import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;
import gestionalebackend.gestionalebackend.worklog.dto.WorkLogDTO;
import gestionalebackend.gestionalebackend.worklog.model.WorkLog;
import jakarta.persistence.EntityNotFoundException;

public class WorkLogMapper {
    
    public static WorkLogDTO convertToDTO(WorkLog workLog) {
        if (workLog == null) {
            return null;
        }
        
        return new WorkLogDTO(
                workLog.getId(),
                workLog.getEmployee().getEmail(),
                workLog.getEmployee().getNome() + " " + workLog.getEmployee().getCognome(),
                workLog.getProject() != null ? workLog.getProject().getId() : null,
                workLog.getProject() != null ? workLog.getProject().getNome() : null,
                workLog.getData(),
                workLog.getOre(),
                workLog.getTipo(),
                workLog.getNote()
        );
    }
    
    public static WorkLog convertToDAO(WorkLogDTO dto, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        if (dto == null) {
            return null;
        }
        
        WorkLog workLog = new WorkLog();
        workLog.setId(dto.id());
        
        workLog.setEmployee(employeeRepository.findById(dto.employeeEmail())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato: " + dto.employeeEmail())));
        
        if (dto.projectId() != null) {
            workLog.setProject(projectRepository.findById(dto.projectId()).orElse(null));
        }
        
        workLog.setData(dto.data());
        workLog.setOre(dto.ore());
        workLog.setTipo(dto.tipo());
        workLog.setNote(dto.note());
        
        return workLog;
    }
}