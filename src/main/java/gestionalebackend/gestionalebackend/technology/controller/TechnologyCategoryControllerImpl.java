package gestionalebackend.gestionalebackend.technology.controller;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyCategoryDTO;
import gestionalebackend.gestionalebackend.technology.service.TechnologyCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TechnologyCategoryControllerImpl implements TechnologyCategoryController {
    
    private final TechnologyCategoryService categoryService;
    
    @Override
    public ResponseEntity<List<TechnologyCategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    
    @Override
    public ResponseEntity<TechnologyCategoryDTO> getCategoryById(Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    
    @Override
    public ResponseEntity<TechnologyCategoryDTO> getCategoryByName(String name) {
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }
    
    @Override
    public ResponseEntity<TechnologyCategoryDTO> createCategory(TechnologyCategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(categoryDTO));
    }
    
    @Override
    public ResponseEntity<TechnologyCategoryDTO> updateCategory(Long id, TechnologyCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }
    
    @Override
    public ResponseEntity<Void> deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}