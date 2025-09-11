package gestionalebackend.gestionalebackend.project.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PROJECTS")
@Table(name = "PROJECTS")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String projectCode;
    
    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String projectManager;

    private Date startDate;
    
    private Date endDate;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @ManyToMany
    @JoinTable(
        name = "PROJECTS_TECHNOLOGIES",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();
    
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();
    
}