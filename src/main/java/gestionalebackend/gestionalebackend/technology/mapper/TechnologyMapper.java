package gestionalebackend.gestionalebackend.technology.mapper;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyCategoryDTO;
import gestionalebackend.gestionalebackend.technology.dto.TechnologyDTO;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import gestionalebackend.gestionalebackend.technology.model.TechnologyCategory;

public class TechnologyMapper {

    public static TechnologyDTO convertToDTO(Technology technology) {
        TechnologyCategoryDTO categoryDTO = null;
        if (technology.getCategory() != null) {
            categoryDTO = TechnologyCategoryDTO.builder()
                    .id(technology.getCategory().getId())
                    .name(technology.getCategory().getName())
                    .description(technology.getCategory().getDescription())
                    .displayOrder(technology.getCategory().getDisplayOrder())
                    .build();
        }
        
        return new TechnologyDTO(
                technology.getId(),
                technology.getName(),
                technology.getDescription(),
                categoryDTO
        );
    }

    public static Technology convertToDAO(TechnologyDTO technologyDTO) {
        Technology.TechnologyBuilder builder = Technology.builder()
                .id(technologyDTO.id())
                .name(technologyDTO.name())
                .description(technologyDTO.description());
        
        if (technologyDTO.category() != null && technologyDTO.category().getId() != null) {
            TechnologyCategory category = TechnologyCategory.builder()
                    .id(technologyDTO.category().getId())
                    .build();
            builder.category(category);
        }
        
        return builder.build();
    }
}