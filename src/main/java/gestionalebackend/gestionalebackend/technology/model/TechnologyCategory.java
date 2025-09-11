package gestionalebackend.gestionalebackend.technology.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TECHNOLOGY_CATEGORIES")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Column(name = "display_order")
    private Integer displayOrder;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Technology> technologies = new HashSet<>();
}