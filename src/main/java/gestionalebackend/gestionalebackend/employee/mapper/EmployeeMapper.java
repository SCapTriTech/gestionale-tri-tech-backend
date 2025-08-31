package gestionalebackend.gestionalebackend.employee.mapper;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.permission.model.Permission;
import gestionalebackend.gestionalebackend.project.model.Project;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
                employee.getDataDiLicenziamento(),
                employee.getProjects() != null ? 
                    employee.getProjects().stream()
                        .map(Project::getId)
                        .collect(Collectors.toSet()) : null,
                employee.getRole() != null ? employee.getRole().getId() : null,
                employee.getRole() != null ? employee.getRole().getName() : null,
                employee.getTeamLeader() != null ? employee.getTeamLeader().getEmail() : null,
                employee.getTeamMembers() != null ?
                    employee.getTeamMembers().stream()
                        .map(Employee::getEmail)
                        .collect(Collectors.toSet()) : null,
                employee.getAdditionalPermissions() != null ?
                    employee.getAdditionalPermissions().stream()
                        .map(Permission::getId)
                        .collect(Collectors.toSet()) : null,
                employee.getAdditionalPermissions() != null ?
                    employee.getAdditionalPermissions().stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet()) : null
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
                .projects(new HashSet<>())
                .build();
    }
    
    public static Employee convertToDAO(EmployeeDTO employee, ProjectRepository projectRepository){
        Employee emp = Employee.builder()
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
                .projects(new HashSet<>())
                .build();

        if (employee.projectIds() != null && !employee.projectIds().isEmpty()) {
            Set<Project> projects = new HashSet<>(projectRepository.findAllById(employee.projectIds()));
            emp.setProjects(projects);
        }
        
        return emp;
    }
}