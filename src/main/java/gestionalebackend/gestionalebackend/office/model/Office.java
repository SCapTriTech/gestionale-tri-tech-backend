package gestionalebackend.gestionalebackend.office.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UFFICI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome_sede", nullable = false)
    private String nomeSede;
    
    @Column(name = "via", nullable = false)
    private String via;
    
    @Column(name = "numero_civico", nullable = false)
    private String numeroCivico;
    
    @Column(name = "posti_disponibili")
    private Integer postiDisponibili;
    
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}