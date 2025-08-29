package gestionalebackend.gestionalebackend.employee.model;

import gestionalebackend.gestionalebackend.project.model.Project;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import jakarta.persistence.Entity;
import lombok.*;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "DIPENDENTI")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(unique = true)
    private String email;

    private String nome;
    private String cognome;
    private String password;
    private String codFiscale;
    private String numeroDiTelefono;
    private String indirizzo;
    private Date dataDiNascita;
    private Date dataDiAssunzione;
    private Date dataDiLicenziamento;
    
    @ManyToMany
    @JoinTable(
        name = "DIPENDENTI_TECNOLOGIE",
        joinColumns = @JoinColumn(name = "employee_email"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "DIPENDENTI_PROGETTI",
        joinColumns = @JoinColumn(name = "employee_email"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

}