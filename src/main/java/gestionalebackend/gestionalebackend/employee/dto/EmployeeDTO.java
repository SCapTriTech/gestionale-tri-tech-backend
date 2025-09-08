package gestionalebackend.gestionalebackend.employee.dto;

import java.sql.Date;
import java.util.Set;

public record EmployeeDTO(

        String email,
        
        String googleId,

        String nome,
        String cognome,
        String codFiscale,
        String numeroDiTelefono,
        String indirizzo,
        Date dataDiNascita,
        Date dataDiAssunzione,
        Date dataDiLicenziamento,
        Set<Long> projectIds,
        Long roleId,
        String roleName,
        Long officeId,
        String officeName,
        String teamLeaderEmail,
        Set<String> teamMemberEmails,
        Set<Long> additionalPermissionIds,
        Set<String> additionalPermissionNames

) {
}