package gestionalebackend.gestionalebackend.employee.controller;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    // Gestione ruoli
    @Operation(summary = "Assegna un ruolo a un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PutMapping("/{email}/role/{roleId}")
    ResponseEntity<EmployeeDTO> assignRoleToEmployee(@PathVariable String email, @PathVariable Long roleId);

    @Operation(summary = "Rimuovi il ruolo da un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @DeleteMapping("/{email}/role")
    ResponseEntity<EmployeeDTO> removeRoleFromEmployee(@PathVariable String email);

    // Gestione team
    @Operation(summary = "Assegna un team leader a un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PutMapping("/{email}/team-leader/{teamLeaderEmail}")
    ResponseEntity<EmployeeDTO> assignTeamLeader(@PathVariable String email, @PathVariable String teamLeaderEmail);

    @Operation(summary = "Rimuovi il team leader da un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @DeleteMapping("/{email}/team-leader")
    ResponseEntity<EmployeeDTO> removeTeamLeader(@PathVariable String email);

    @Operation(summary = "Ottieni i membri del team di un team leader")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/{teamLeaderEmail}/team-members")
    ResponseEntity<List<EmployeeDTO>> getTeamMembers(@PathVariable String teamLeaderEmail);

    // Gestione permessi extra
    @Operation(summary = "Aggiungi permessi extra a un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PostMapping("/{email}/permissions")
    ResponseEntity<EmployeeDTO> addPermissionsToEmployee(@PathVariable String email, @RequestBody Set<Long> permissionIds);

    @Operation(summary = "Rimuovi permessi extra da un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @DeleteMapping("/{email}/permissions")
    ResponseEntity<EmployeeDTO> removePermissionsFromEmployee(@PathVariable String email, @RequestBody Set<Long> permissionIds);

    @Operation(summary = "Ottieni tutti i permessi di un dipendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/{email}/permissions")
    ResponseEntity<Set<String>> getEmployeePermissions(@PathVariable String email);

}