package gestionalebackend.gestionalebackend.auth.service;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.permission.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public boolean canModifyEmployee(String currentUserEmail, String targetEmployeeEmail) {
        Employee currentUser = employeeRepository.findById(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Current user not found: " + currentUserEmail));
        
        if (currentUserEmail.equals(targetEmployeeEmail)) {
            return true;
        }
        
        if (hasPermission(currentUser, "EDIT_ALL_PROFILES")) {
            return true;
        }
        
        if (hasPermission(currentUser, "EDIT_TEAM_PROFILES")) {
            Employee targetEmployee = employeeRepository.findById(targetEmployeeEmail)
                    .orElseThrow(() -> new RuntimeException("Target employee not found: " + targetEmployeeEmail));
            
            return isTeamLeaderOf(currentUser, targetEmployee);
        }
        
        return false;
    }

    @Transactional(readOnly = true)
    public boolean canViewEmployee(String currentUserEmail, String targetEmployeeEmail) {
        return true;
    }

    @Transactional(readOnly = true)
    public boolean hasPermission(Employee employee, String permissionName) {
        Set<String> allPermissions = getAllPermissions(employee);
        return allPermissions.contains(permissionName);
    }

    @Transactional(readOnly = true)
    public Set<String> getAllPermissions(Employee employee) {
        Set<String> permissions = new HashSet<>();
        
        if (employee.getRole() != null && employee.getRole().getPermissions() != null) {
            permissions.addAll(employee.getRole().getPermissions().stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet()));
        }
        
        if (employee.getAdditionalPermissions() != null) {
            permissions.addAll(employee.getAdditionalPermissions().stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet()));
        }
        
        return permissions;
    }

    private boolean isTeamLeaderOf(Employee leader, Employee member) {
        return member.getTeamLeader() != null && 
               member.getTeamLeader().getEmail().equals(leader.getEmail());
    }

    @Transactional(readOnly = true)
    public Set<String> getEditableEmployees(String currentUserEmail) {
        Employee currentUser = employeeRepository.findById(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Current user not found: " + currentUserEmail));
        
        Set<String> editableEmails = new HashSet<>();
        editableEmails.add(currentUserEmail);
        
        if (hasPermission(currentUser, "EDIT_ALL_PROFILES")) {
            return employeeRepository.findAll().stream()
                    .map(Employee::getEmail)
                    .collect(Collectors.toSet());
        }
        
        if (hasPermission(currentUser, "EDIT_TEAM_PROFILES")) {
            if (currentUser.getTeamMembers() != null) {
                editableEmails.addAll(currentUser.getTeamMembers().stream()
                        .map(Employee::getEmail)
                        .collect(Collectors.toSet()));
            }
        }
        
        return editableEmails;
    }
}