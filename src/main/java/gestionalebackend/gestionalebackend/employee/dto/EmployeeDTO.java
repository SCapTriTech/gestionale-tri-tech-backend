package gestionalebackend.gestionalebackend.employee.dto;

import java.sql.Date;
import java.util.Set;

public record EmployeeDTO(

        String email,
        
        String googleId,

        String firstName,
        String lastName,
        String fiscalCode,
        String phoneNumber,
        String address,
        Date birthDate,
        Date hireDate,
        Date terminationDate,
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