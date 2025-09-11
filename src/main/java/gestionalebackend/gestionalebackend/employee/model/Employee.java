package gestionalebackend.gestionalebackend.employee.model;

import gestionalebackend.gestionalebackend.project.model.Project;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import gestionalebackend.gestionalebackend.technology.model.EmployeeTechnologySkill;
import gestionalebackend.gestionalebackend.role.model.Role;
import gestionalebackend.gestionalebackend.permission.model.Permission;
import gestionalebackend.gestionalebackend.office.model.Office;
import jakarta.persistence.Entity;
import lombok.*;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "EMPLOYEES")
@Table(name = "EMPLOYEES")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(unique = true)
    private String email;
    
    private String googleId;

    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String phoneNumber;
    private String address;
    private Date birthDate;
    private Date hireDate;
    private Date terminationDate;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;
    
    @ManyToOne
    @JoinColumn(name = "team_leader_email")
    private Employee teamLeader;
    
    @OneToMany(mappedBy = "teamLeader")
    private Set<Employee> teamMembers = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "EMPLOYEES_ADDITIONAL_PERMISSIONS",
        joinColumns = @JoinColumn(name = "employee_email"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> additionalPermissions = new HashSet<>();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeTechnologySkill> technologySkills = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "EMPLOYEES_PROJECTS",
        joinColumns = @JoinColumn(name = "employee_email"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

}