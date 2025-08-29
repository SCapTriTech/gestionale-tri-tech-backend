package gestionalebackend.gestionalebackend.project.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PROGETTI")
@Table(name = "PROGETTI")
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
    private String codiceProgetto;
    
    @Column(nullable = false)
    private String nome;

    private String descrizione;

    @Column(nullable = false)
    private String referenteProgetto;

    private Date dataInizio;
    
    private Date dataFine;
    
    @Column(nullable = false)
    private Boolean attivo = true;
    
    @ManyToMany
    @JoinTable(
        name = "PROGETTI_TECNOLOGIE",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();
    
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();
    
}