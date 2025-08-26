package gestionalebackend.gestionalebackend.worklog.controller;

import gestionalebackend.gestionalebackend.worklog.dto.WorkLogDTO;
import gestionalebackend.gestionalebackend.worklog.dto.YearlyConsuntivoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(path = "/api/v1/consuntivi", produces = {"application/json"})
@Tag(name = "worklog-controller", description = "Gestione dei consuntivi ore")
public interface WorkLogController {
    
    @Operation(summary = "Crea un nuovo consuntivo")
    @PostMapping
    ResponseEntity<WorkLogDTO> createWorkLog(@RequestBody WorkLogDTO workLogDTO);
    
    @Operation(summary = "Aggiorna un consuntivo esistente")
    @PutMapping("/{id}")
    ResponseEntity<WorkLogDTO> updateWorkLog(@PathVariable Long id, @RequestBody WorkLogDTO workLogDTO);
    
    @Operation(summary = "Elimina un consuntivo")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWorkLog(@PathVariable Long id);
    
    @Operation(summary = "Recupera un consuntivo per ID")
    @GetMapping("/{id}")
    ResponseEntity<WorkLogDTO> getWorkLogById(@PathVariable Long id);
    
    @Operation(summary = "Recupera i consuntivi per dipendente e periodo")
    @GetMapping("/dipendente/{employeeEmail}")
    ResponseEntity<List<WorkLogDTO>> getWorkLogsByEmployee(
            @PathVariable String employeeEmail,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    );
    
    @Operation(summary = "Recupera i consuntivi per progetto e periodo")
    @GetMapping("/progetto/{projectId}")
    ResponseEntity<List<WorkLogDTO>> getWorkLogsByProject(
            @PathVariable Long projectId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    );
    
    @Operation(summary = "Recupera i consuntivi per periodo")
    @GetMapping("/periodo")
    ResponseEntity<List<WorkLogDTO>> getWorkLogsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    );
    
    @Operation(summary = "Recupera il consuntivo annuale")
    @GetMapping("/annuale/{year}")
    ResponseEntity<YearlyConsuntivoDTO> getYearlyConsuntivo(
            @PathVariable Integer year,
            @RequestParam(required = false) Long projectId
    );
    
    @Operation(summary = "Recupera i consuntivi mensili")
    @GetMapping("/mensile/{year}/{month}")
    ResponseEntity<List<WorkLogDTO>> getMonthlyWorkLogs(
            @PathVariable Integer year,
            @PathVariable Integer month
    );
    
    @Operation(summary = "Recupera i consuntivi annuali di un dipendente")
    @GetMapping("/dipendente/{employeeEmail}/anno/{year}")
    ResponseEntity<List<WorkLogDTO>> getEmployeeYearlyWorkLogs(
            @PathVariable String employeeEmail,
            @PathVariable Integer year
    );
}