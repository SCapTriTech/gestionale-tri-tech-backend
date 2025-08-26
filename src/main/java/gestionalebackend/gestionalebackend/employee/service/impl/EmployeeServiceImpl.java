package gestionalebackend.gestionalebackend.employee.service.impl;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.mapper.EmployeeMapper;
import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeRepository.findAll(pageable)
                .stream()
                .map(EmployeeMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(String email) {
        return employeeRepository.findById(email)
                .map(EmployeeMapper::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con email: " + email));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.convertToDAO(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
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

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.convertToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(String email) {
        employeeRepository.deleteById(email);
    }

}