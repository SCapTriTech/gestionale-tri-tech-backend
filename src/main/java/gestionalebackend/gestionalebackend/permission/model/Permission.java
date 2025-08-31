package gestionalebackend.gestionalebackend.permission.model;

import gestionalebackend.gestionalebackend.role.model.Role;
import gestionalebackend.gestionalebackend.employee.model.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "PERMESSI")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "additionalPermissions")
    private Set<Employee> employees = new HashSet<>();
}