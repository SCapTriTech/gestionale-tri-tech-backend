package gestionalebackend.gestionalebackend.employee.controller.impl;

import gestionalebackend.gestionalebackend.employee.controller.EmployeeController;
import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees(pageNumber, pageSize);
        return ResponseEntity.ok(employeeDTOList);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String email) {
        EmployeeDTO employee = employeeService.getEmployeeById(email);
        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return  ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));

    }

    @Override
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Gestione ruoli
    @Override
    public ResponseEntity<EmployeeDTO> assignRoleToEmployee(@PathVariable String email, @PathVariable Long roleId) {
        EmployeeDTO updatedEmployee = employeeService.assignRoleToEmployee(email, roleId);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Override
    public ResponseEntity<EmployeeDTO> removeRoleFromEmployee(@PathVariable String email) {
        EmployeeDTO updatedEmployee = employeeService.removeRoleFromEmployee(email);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Gestione team
    @Override
    public ResponseEntity<EmployeeDTO> assignTeamLeader(@PathVariable String email, @PathVariable String teamLeaderEmail) {
        EmployeeDTO updatedEmployee = employeeService.assignTeamLeader(email, teamLeaderEmail);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Override
    public ResponseEntity<EmployeeDTO> removeTeamLeader(@PathVariable String email) {
        EmployeeDTO updatedEmployee = employeeService.removeTeamLeader(email);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getTeamMembers(@PathVariable String teamLeaderEmail) {
        List<EmployeeDTO> teamMembers = employeeService.getTeamMembers(teamLeaderEmail);
        return ResponseEntity.ok(teamMembers);
    }

    // Gestione permessi extra
    @Override
    public ResponseEntity<EmployeeDTO> addPermissionsToEmployee(@PathVariable String email, @RequestBody Set<Long> permissionIds) {
        EmployeeDTO updatedEmployee = employeeService.addPermissionsToEmployee(email, permissionIds);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Override
    public ResponseEntity<EmployeeDTO> removePermissionsFromEmployee(@PathVariable String email, @RequestBody Set<Long> permissionIds) {
        EmployeeDTO updatedEmployee = employeeService.removePermissionsFromEmployee(email, permissionIds);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Override
    public ResponseEntity<Set<String>> getEmployeePermissions(@PathVariable String email) {
        Set<String> permissions = employeeService.getEmployeePermissions(email);
        return ResponseEntity.ok(permissions);
    }
}