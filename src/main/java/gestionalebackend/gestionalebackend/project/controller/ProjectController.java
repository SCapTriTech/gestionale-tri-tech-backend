package gestionalebackend.gestionalebackend.project.controller;

import gestionalebackend.gestionalebackend.project.dto.ProjectDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v1/progetti", produces = {"application/json"})
@Tag(name = "project-controller", description = "Gestione dei progetti")
public interface ProjectController {
    
    @Operation(summary = "Recupera tutti i progetti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/all")
    ResponseEntity<List<ProjectDTO>> getAllProjects();
    
    @Operation(summary = "Recupera solo i progetti attivi")
    @GetMapping("/attivi")
    ResponseEntity<List<ProjectDTO>> getActiveProjects();
    
    @Operation(summary = "Recupera un progetto per ID")
    @GetMapping("/{id}")
    ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id);
    
    @Operation(summary = "Recupera un progetto per codice")
    @GetMapping("/codice/{codiceProgetto}")
    ResponseEntity<ProjectDTO> getProjectByCodice(@PathVariable String codiceProgetto);
    
    @Operation(summary = "Crea un nuovo progetto")
    @PostMapping
    ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO);
    
    @Operation(summary = "Aggiorna un progetto esistente")
    @PutMapping("/{id}")
    ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO);
    
    @Operation(summary = "Elimina un progetto")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProject(@PathVariable Long id);
    
    @Operation(summary = "Cerca progetti per nome")
    @GetMapping("/search")
    ResponseEntity<List<ProjectDTO>> searchProjects(@RequestParam String nome);
    
    @Operation(summary = "Recupera progetti per lista di ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid IDs"),
    })
    @GetMapping("/batch")
    ResponseEntity<List<ProjectDTO>> getProjectsByIds(@RequestParam List<Long> ids);
}