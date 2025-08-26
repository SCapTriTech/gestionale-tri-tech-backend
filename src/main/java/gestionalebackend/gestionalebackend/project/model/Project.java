package gestionalebackend.gestionalebackend.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.sql.Date;

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
    
}