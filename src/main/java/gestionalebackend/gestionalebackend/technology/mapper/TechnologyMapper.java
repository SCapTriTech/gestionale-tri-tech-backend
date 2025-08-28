package gestionalebackend.gestionalebackend.technology.mapper;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyDTO;
import gestionalebackend.gestionalebackend.technology.model.Technology;

public class TechnologyMapper {

    public static TechnologyDTO convertToDTO(Technology technology) {
        return new TechnologyDTO(
                technology.getId(),
                technology.getName(),
                technology.getDescription()
        );
    }

    public static Technology convertToDAO(TechnologyDTO technologyDTO) {
        return Technology.builder()
                .id(technologyDTO.id())
                .name(technologyDTO.name())
                .description(technologyDTO.description())
                .build();
    }
}