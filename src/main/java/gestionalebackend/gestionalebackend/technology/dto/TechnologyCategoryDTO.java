package gestionalebackend.gestionalebackend.technology.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Integer displayOrder;
}