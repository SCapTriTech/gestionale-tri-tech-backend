package gestionalebackend.gestionalebackend.technology.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE_TECHNOLOGY_SKILLS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTechnologySkill {
    
    @EmbeddedId
    private EmployeeTechnologySkillId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeEmail")
    @JoinColumn(name = "employee_email")
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("technologyId")
    @JoinColumn(name = "technology_id")
    private Technology technology;
    
    @Column(nullable = false)
    private Integer skillLevel;
    
    @Column(name = "last_assessment_date")
    private LocalDate lastAssessmentDate;
    
    @Column(length = 500)
    private String notes;
    
    @Column(name = "is_certified")
    private Boolean isCertified;
    
    @Column(name = "certification_date")
    private LocalDate certificationDate;
}