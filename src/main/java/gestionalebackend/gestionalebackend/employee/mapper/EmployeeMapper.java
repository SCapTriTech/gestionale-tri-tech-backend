package gestionalebackend.gestionalebackend.employee.mapper;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.model.Employee;

import java.sql.Date;

public class EmployeeMapper {

    public static EmployeeDTO convertToDTO(Employee employee){
        return new EmployeeDTO(
                employee.getEmail(),
                employee.getNome(),
                employee.getCognome(),
                employee.getPassword(),
                employee.getCodFiscale(),
                employee.getNumeroDiTelefono(),
                employee.getIndirizzo(),
                employee.getDataDiNascita(),
                employee.getDataDiAssunzione(),
                employee.getDataDiLicenziamento()
        );
    }

    public static Employee convertToDAO(EmployeeDTO employee){
        return Employee.builder()
                .email(employee.email())

                .nome(employee.nome())
                .cognome(employee.cognome())
                .password(employee.password())
                .codFiscale(employee.codFiscale())
                .numeroDiTelefono(employee.numeroDiTelefono())
                .indirizzo(employee.indirizzo())
                .dataDiNascita(employee.dataDiNascita())
                .dataDiAssunzione(employee.dataDiAssunzione())
                .dataDiLicenziamento(employee.dataDiLicenziamento())
                .build();
    }
}