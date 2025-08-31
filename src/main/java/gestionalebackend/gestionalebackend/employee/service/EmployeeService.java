package gestionalebackend.gestionalebackend.employee.service;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees(Integer pageNumber, Integer pageSize);
    EmployeeDTO getEmployeeById(String email);
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(String email, EmployeeDTO employeeDTO);
    void deleteEmployee(String email);
    
    // Gestione ruoli
    EmployeeDTO assignRoleToEmployee(String email, Long roleId);
    EmployeeDTO removeRoleFromEmployee(String email);
    
    // Gestione team
    EmployeeDTO assignTeamLeader(String employeeEmail, String teamLeaderEmail);
    EmployeeDTO removeTeamLeader(String employeeEmail);
    List<EmployeeDTO> getTeamMembers(String teamLeaderEmail);
    
    // Gestione permessi extra
    EmployeeDTO addPermissionsToEmployee(String email, Set<Long> permissionIds);
    EmployeeDTO removePermissionsFromEmployee(String email, Set<Long> permissionIds);
    Set<String> getEmployeePermissions(String email);

}