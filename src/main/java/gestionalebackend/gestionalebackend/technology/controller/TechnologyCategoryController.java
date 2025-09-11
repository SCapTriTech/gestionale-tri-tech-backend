package gestionalebackend.gestionalebackend.technology.controller;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyCategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v1/technology-categories", produces = {"application/json"})
@Tag(name = "technology-category-controller", description = "Gestione delle categorie di tecnologie")
public interface TechnologyCategoryController {
    
    @Operation(summary = "Recupera tutte le categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/all")
    ResponseEntity<List<TechnologyCategoryDTO>> getAllCategories();
    
    @Operation(summary = "Recupera una categoria per ID")
    @GetMapping("/{id}")
    ResponseEntity<TechnologyCategoryDTO> getCategoryById(@PathVariable Long id);
    
    @Operation(summary = "Recupera una categoria per nome")
    @GetMapping("/nome/{name}")
    ResponseEntity<TechnologyCategoryDTO> getCategoryByName(@PathVariable String name);
    
    @Operation(summary = "Crea una nuova categoria")
    @PostMapping
    ResponseEntity<TechnologyCategoryDTO> createCategory(@RequestBody TechnologyCategoryDTO categoryDTO);
    
    @Operation(summary = "Aggiorna una categoria")
    @PutMapping("/{id}")
    ResponseEntity<TechnologyCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody TechnologyCategoryDTO categoryDTO);
    
    @Operation(summary = "Elimina una categoria")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);
}