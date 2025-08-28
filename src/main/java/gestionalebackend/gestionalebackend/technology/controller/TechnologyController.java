package gestionalebackend.gestionalebackend.technology.controller;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v1/tecnologie", produces = {"application/json"})
@Tag(name = "technology-controller", description = "Gestione delle tecnologie")
public interface TechnologyController {
    
    @Operation(summary = "Recupera tutte le tecnologie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping
    ResponseEntity<List<TechnologyDTO>> getAllTechnologies();
    
    @Operation(summary = "Recupera una tecnologia per ID")
    @GetMapping("/{id}")
    ResponseEntity<TechnologyDTO> getTechnologyById(@PathVariable Long id);
    
    @Operation(summary = "Recupera una tecnologia per nome")
    @GetMapping("/nome/{name}")
    ResponseEntity<TechnologyDTO> getTechnologyByName(@PathVariable String name);
    
    @Operation(summary = "Crea una nuova tecnologia")
    @PostMapping
    ResponseEntity<TechnologyDTO> createTechnology(@RequestBody TechnologyDTO technologyDTO);
    
    @Operation(summary = "Aggiorna una tecnologia per ID")
    @PutMapping("/{id}")
    ResponseEntity<TechnologyDTO> updateTechnologyById(@PathVariable Long id, @RequestBody TechnologyDTO technologyDTO);
    
    @Operation(summary = "Aggiorna una tecnologia per nome")
    @PutMapping("/nome/{name}")
    ResponseEntity<TechnologyDTO> updateTechnologyByName(@PathVariable String name, @RequestBody TechnologyDTO technologyDTO);
    
    @Operation(summary = "Elimina una tecnologia per ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTechnologyById(@PathVariable Long id);
    
    @Operation(summary = "Elimina una tecnologia per nome")
    @DeleteMapping("/nome/{name}")
    ResponseEntity<Void> deleteTechnologyByName(@PathVariable String name);
}