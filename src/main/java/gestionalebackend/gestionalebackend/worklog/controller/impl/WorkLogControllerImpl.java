package gestionalebackend.gestionalebackend.worklog.controller.impl;

import gestionalebackend.gestionalebackend.worklog.controller.WorkLogController;
import gestionalebackend.gestionalebackend.worklog.dto.WorkLogDTO;
import gestionalebackend.gestionalebackend.worklog.dto.YearlyConsuntivoDTO;
import gestionalebackend.gestionalebackend.worklog.service.WorkLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WorkLogControllerImpl implements WorkLogController {
    
    private final WorkLogService workLogService;
    
    public WorkLogControllerImpl(WorkLogService workLogService) {
        this.workLogService = workLogService;
    }
    
    @Override
    public ResponseEntity<WorkLogDTO> createWorkLog(@RequestBody WorkLogDTO workLogDTO) {
        WorkLogDTO createdWorkLog = workLogService.createWorkLog(workLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkLog);
    }
    
    @Override
    public ResponseEntity<WorkLogDTO> updateWorkLog(@PathVariable Long id, @RequestBody WorkLogDTO workLogDTO) {
        WorkLogDTO updatedWorkLog = workLogService.updateWorkLog(id, workLogDTO);
        return ResponseEntity.ok(updatedWorkLog);
    }
    
    @Override
    public ResponseEntity<Void> deleteWorkLog(@PathVariable Long id) {
        workLogService.deleteWorkLog(id);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    public ResponseEntity<WorkLogDTO> getWorkLogById(@PathVariable Long id) {
        WorkLogDTO workLog = workLogService.getWorkLogById(id);
        return ResponseEntity.ok(workLog);
    }
    
    @Override
    public ResponseEntity<List<WorkLogDTO>> getWorkLogsByEmployee(
            @PathVariable String employeeEmail, 
            LocalDate startDate, 
            LocalDate endDate) {
        List<WorkLogDTO> workLogs = workLogService.getWorkLogsByEmployee(employeeEmail, startDate, endDate);
        return ResponseEntity.ok(workLogs);
    }
    
    @Override
    public ResponseEntity<List<WorkLogDTO>> getWorkLogsByProject(
            @PathVariable Long projectId,
            LocalDate startDate,
            LocalDate endDate) {
        List<WorkLogDTO> workLogs = workLogService.getWorkLogsByProject(projectId, startDate, endDate);
        return ResponseEntity.ok(workLogs);
    }
    
    @Override
    public ResponseEntity<List<WorkLogDTO>> getWorkLogsByDateRange(
            LocalDate startDate,
            LocalDate endDate) {
        List<WorkLogDTO> workLogs = workLogService.getWorkLogsByDateRange(startDate, endDate);
        return ResponseEntity.ok(workLogs);
    }
    
    @Override
    public ResponseEntity<YearlyConsuntivoDTO> getYearlyConsuntivo(
            @PathVariable Integer year,
            @RequestParam(required = false) Long projectId) {
        YearlyConsuntivoDTO yearlyConsuntivo = workLogService.getYearlyConsuntivo(year, projectId);
        return ResponseEntity.ok(yearlyConsuntivo);
    }
    
    @Override
    public ResponseEntity<List<WorkLogDTO>> getMonthlyWorkLogs(
            @PathVariable Integer year,
            @PathVariable Integer month) {
        List<WorkLogDTO> workLogs = workLogService.getMonthlyWorkLogs(year, month);
        return ResponseEntity.ok(workLogs);
    }
    
    @Override
    public ResponseEntity<List<WorkLogDTO>> getEmployeeYearlyWorkLogs(
            @PathVariable String employeeEmail,
            @PathVariable Integer year) {
        List<WorkLogDTO> workLogs = workLogService.getEmployeeYearlyWorkLogs(employeeEmail, year);
        return ResponseEntity.ok(workLogs);
    }
}