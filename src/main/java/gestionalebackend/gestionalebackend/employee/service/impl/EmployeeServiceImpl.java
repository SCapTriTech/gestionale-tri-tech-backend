package gestionalebackend.gestionalebackend.employee.service.impl;

import gestionalebackend.gestionalebackend.auth.service.AuthorizationService;
import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.mapper.EmployeeMapper;
import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.employee.service.EmployeeService;
import gestionalebackend.gestionalebackend.permission.model.Permission;
import gestionalebackend.gestionalebackend.permission.repository.PermissionRepository;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;
import gestionalebackend.gestionalebackend.role.model.Role;
import gestionalebackend.gestionalebackend.role.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final AuthorizationService authorizationService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, 
                              ProjectRepository projectRepository,
                              RoleRepository roleRepository,
                              PermissionRepository permissionRepository,
                              AuthorizationService authorizationService) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.authorizationService = authorizationService;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // Using optimized query that loads projects in single query
        return employeeRepository.findAllWithProjects(pageable)
                .stream()
                .map(EmployeeMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(String email) {
        // Using optimized query that loads projects in single query
        return employeeRepository.findByIdWithProjects(email)
                .map(EmployeeMapper::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.convertToDAO(employeeDTO, projectRepository);
        Employee savedEmployee = employeeRepository.save(employee);
        // Reload to get the projects relationship
        savedEmployee = employeeRepository.findByIdWithProjects(savedEmployee.getEmail()).orElse(savedEmployee);
        return EmployeeMapper.convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(String email, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));

        if (employeeDTO.nome() != null) {
            employee.setNome(employeeDTO.nome());
        }

        if (employeeDTO.cognome() != null) {
            employee.setCognome(employeeDTO.cognome());
        }

        if (employeeDTO.password() != null) {
            employee.setPassword(employeeDTO.password());
        }

        if (employeeDTO.codFiscale() != null) {
            employee.setCodFiscale(employeeDTO.codFiscale());
        }

        if (employeeDTO.numeroDiTelefono() != null) {
            employee.setNumeroDiTelefono(employeeDTO.numeroDiTelefono());
        }

        if (employeeDTO.indirizzo() != null) {
            employee.setIndirizzo(employeeDTO.indirizzo());
        }

        if (employeeDTO.dataDiNascita() != null) {
            employee.setDataDiNascita(employeeDTO.dataDiNascita());
        }

        if (employeeDTO.dataDiAssunzione() != null) {
            employee.setDataDiAssunzione(employeeDTO.dataDiAssunzione());
        }

        if (employeeDTO.dataDiLicenziamento() != null) {
            employee.setDataDiLicenziamento(employeeDTO.dataDiLicenziamento());
        }
        
        // Update projects if provided
        if (employeeDTO.projectIds() != null) {
            employee.getProjects().clear();
            if (!employeeDTO.projectIds().isEmpty()) {
                employee.getProjects().addAll(projectRepository.findAllById(employeeDTO.projectIds()));
            }
        }

        Employee updatedEmployee = employeeRepository.save(employee);
        // Reload to get the projects relationship
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(String email) {
        employeeRepository.deleteById(email);
    }

    @Override
    @Transactional
    public EmployeeDTO assignRoleToEmployee(String email, Long roleId) {
        Employee employee = employeeRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
        
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Ruolo non trovato con id: " + roleId));
        
        employee.setRole(role);
        Employee updatedEmployee = employeeRepository.save(employee);
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO removeRoleFromEmployee(String email) {
        Employee employee = employeeRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
        
        employee.setRole(null);
        Employee updatedEmployee = employeeRepository.save(employee);
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO assignTeamLeader(String employeeEmail, String teamLeaderEmail) {
        Employee employee = employeeRepository.findById(employeeEmail)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + employeeEmail));
        
        Employee teamLeader = employeeRepository.findById(teamLeaderEmail)
                .orElseThrow(() -> new EntityNotFoundException("Team leader non trovato con email: " + teamLeaderEmail));
        
        employee.setTeamLeader(teamLeader);
        Employee updatedEmployee = employeeRepository.save(employee);
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO removeTeamLeader(String employeeEmail) {
        Employee employee = employeeRepository.findById(employeeEmail)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + employeeEmail));
        
        employee.setTeamLeader(null);
        Employee updatedEmployee = employeeRepository.save(employee);
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getTeamMembers(String teamLeaderEmail) {
        Employee teamLeader = employeeRepository.findById(teamLeaderEmail)
                .orElseThrow(() -> new EntityNotFoundException("Team leader non trovato con email: " + teamLeaderEmail));
        
        return teamLeader.getTeamMembers().stream()
                .map(EmployeeMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO addPermissionsToEmployee(String email, Set<Long> permissionIds) {
        Employee employee = employeeRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
        
        Set<Permission> newPermissions = permissionIds.stream()
                .map(id -> permissionRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Permesso non trovato con id: " + id)))
                .collect(Collectors.toSet());
        
        employee.getAdditionalPermissions().addAll(newPermissions);
        Employee updatedEmployee = employeeRepository.save(employee);
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO removePermissionsFromEmployee(String email, Set<Long> permissionIds) {
        Employee employee = employeeRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
        
        employee.getAdditionalPermissions().removeIf(permission -> permissionIds.contains(permission.getId()));
        Employee updatedEmployee = employeeRepository.save(employee);
        updatedEmployee = employeeRepository.findByIdWithProjects(updatedEmployee.getEmail()).orElse(updatedEmployee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getEmployeePermissions(String email) {
        Employee employee = employeeRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
        
        return authorizationService.getAllPermissions(employee);
    }

}