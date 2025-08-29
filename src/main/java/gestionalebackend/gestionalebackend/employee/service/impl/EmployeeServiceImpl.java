package gestionalebackend.gestionalebackend.employee.service.impl;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.mapper.EmployeeMapper;
import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.employee.service.EmployeeService;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
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

}