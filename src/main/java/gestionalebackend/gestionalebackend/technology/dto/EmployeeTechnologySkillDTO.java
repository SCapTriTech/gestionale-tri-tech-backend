package gestionalebackend.gestionalebackend.technology.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTechnologySkillDTO {
    
    private String employeeEmail;
    private Long technologyId;
    private String technologyName;
    private String categoryName;
    private Integer skillLevel;
    private LocalDate lastAssessmentDate;
    private String notes;
    private Boolean isCertified;
    private LocalDate certificationDate;
}