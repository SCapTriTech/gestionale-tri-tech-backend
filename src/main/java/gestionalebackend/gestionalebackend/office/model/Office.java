package gestionalebackend.gestionalebackend.office.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OFFICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "office_name", nullable = false)
    private String officeName;
    
    @Column(name = "street", nullable = false)
    private String street;
    
    @Column(name = "street_number", nullable = false)
    private String streetNumber;
    
    @Column(name = "available_seats")
    private Integer availableSeats;
    
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}