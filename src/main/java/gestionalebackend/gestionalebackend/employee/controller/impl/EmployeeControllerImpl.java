package gestionalebackend.gestionalebackend.employee.controller.impl;

import gestionalebackend.gestionalebackend.employee.controller.EmployeeController;
import gestionalebackend.gestionalebackend.employee.dto.EmployeeDTO;
import gestionalebackend.gestionalebackend.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}