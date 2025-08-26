package gestionalebackend.gestionalebackend.employee.controller;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v1/dipendente", produces = {"application/json", "application/hal+json"})
@Tag(name = "employee-controller", description = "Gestione dei dipendenti")
public interface EmployeeController {

    @Operation(summary = "Recupera tutti i dipendenti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/all")
    ResponseEntity<List<EmployeeDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    );

    @Operation(summary = "Recupera il dipendente dall'ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/{email}")
    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String email);

    @Operation(summary = "Registra un nuovo dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO);

    @Operation(summary = "Aggiorna l'anagrafica di un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PutMapping("/{id}")
    ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO);

    @Operation(summary = "Elimina un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable String id);

}