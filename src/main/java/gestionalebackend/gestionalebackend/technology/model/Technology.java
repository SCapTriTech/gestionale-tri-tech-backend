package gestionalebackend.gestionalebackend.technology.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.project.model.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "TECHNOLOGIES")
@Table(name = "TECHNOLOGIES")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Technology {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private TechnologyCategory category;
    
    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeTechnologySkill> employeeSkills = new HashSet<>();
    
    @ManyToMany(mappedBy = "technologies")
    private Set<Project> projects = new HashSet<>();
    
}