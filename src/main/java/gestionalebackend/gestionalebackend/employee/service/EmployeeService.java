package gestionalebackend.gestionalebackend.employee.service;

import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees(Integer pageNumber, Integer pageSize);
    EmployeeDTO getEmployeeById(String email);
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(String email, EmployeeDTO employeeDTO);
    void deleteEmployee(String email);

}