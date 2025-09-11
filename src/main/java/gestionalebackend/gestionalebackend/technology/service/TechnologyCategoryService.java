package gestionalebackend.gestionalebackend.technology.service;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyCategoryDTO;
import gestionalebackend.gestionalebackend.technology.model.TechnologyCategory;
import gestionalebackend.gestionalebackend.technology.repository.TechnologyCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TechnologyCategoryService {
    
    private final TechnologyCategoryRepository categoryRepository;
    
    public TechnologyCategoryDTO createCategory(TechnologyCategoryDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("La categoria " + dto.getName() + " esiste già");
        }
        
        TechnologyCategory category = TechnologyCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .displayOrder(dto.getDisplayOrder())
                .build();
        
        category = categoryRepository.save(category);
        return toDTO(category);
    }
    
    public TechnologyCategoryDTO updateCategory(Long id, TechnologyCategoryDTO dto) {
        TechnologyCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con ID: " + id));
        
        if (!category.getName().equals(dto.getName()) && categoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("La categoria " + dto.getName() + " esiste già");
        }
        
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setDisplayOrder(dto.getDisplayOrder());
        
        category = categoryRepository.save(category);
        return toDTO(category);
    }
    
    public void deleteCategory(Long id) {
        TechnologyCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con ID: " + id));
        
        if (!category.getTechnologies().isEmpty()) {
            throw new IllegalStateException("Impossibile eliminare la categoria: ci sono tecnologie associate");
        }
        
        categoryRepository.delete(category);
    }
    
    @Transactional(readOnly = true)
    public TechnologyCategoryDTO getCategoryById(Long id) {
        TechnologyCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con ID: " + id));
        return toDTO(category);
    }
    
    @Transactional(readOnly = true)
    public TechnologyCategoryDTO getCategoryByName(String name) {
        TechnologyCategory category = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con nome: " + name));
        return toDTO(category);
    }
    
    @Transactional(readOnly = true)
    public List<TechnologyCategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    private TechnologyCategoryDTO toDTO(TechnologyCategory category) {
        return TechnologyCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .displayOrder(category.getDisplayOrder())
                .build();
    }
}