package gestionalebackend.gestionalebackend.employee.model;

import jakarta.persistence.Entity;
import lombok.*;

import jakarta.persistence.*;

import java.sql.Date;

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

}