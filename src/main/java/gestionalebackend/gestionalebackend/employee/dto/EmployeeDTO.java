package gestionalebackend.gestionalebackend.employee.dto;

import java.sql.Date;

public record EmployeeDTO(

        String email,

        String nome,
        String cognome,
        String password,
        String codFiscale,
        String numeroDiTelefono,
        String indirizzo,
        Date dataDiNascita,
        Date dataDiAssunzione,
        Date dataDiLicenziamento

) {
}