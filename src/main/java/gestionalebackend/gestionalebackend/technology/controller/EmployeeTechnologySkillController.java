package gestionalebackend.gestionalebackend.technology.controller;

import gestionalebackend.gestionalebackend.technology.dto.EmployeeTechnologySkillDTO;
import gestionalebackend.gestionalebackend.technology.service.EmployeeTechnologySkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeTechnologySkillController {
    
    private final EmployeeTechnologySkillService skillService;
    
    @PostMapping
    public ResponseEntity<EmployeeTechnologySkillDTO> saveOrUpdateSkill(@RequestBody EmployeeTechnologySkillDTO skillDTO) {
        EmployeeTechnologySkillDTO savedSkill = skillService.saveOrUpdateSkill(skillDTO);
        return ResponseEntity.ok(savedSkill);
    }
    
    @PostMapping("/batch/{employeeEmail}")
    public ResponseEntity<List<EmployeeTechnologySkillDTO>> saveOrUpdateMultipleSkills(
            @PathVariable String employeeEmail,
            @RequestBody List<EmployeeTechnologySkillDTO> skills) {
        List<EmployeeTechnologySkillDTO> savedSkills = skillService.saveOrUpdateMultipleSkills(employeeEmail, skills);
        return ResponseEntity.ok(savedSkills);
    }
    
    @GetMapping("/employee/{employeeEmail}")
    public ResponseEntity<List<EmployeeTechnologySkillDTO>> getSkillsByEmployee(@PathVariable String employeeEmail) {
        List<EmployeeTechnologySkillDTO> skills = skillService.getSkillsByEmployee(employeeEmail);
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/technology/{technologyId}")
    public ResponseEntity<List<EmployeeTechnologySkillDTO>> getSkillsByTechnology(@PathVariable Long technologyId) {
        List<EmployeeTechnologySkillDTO> skills = skillService.getSkillsByTechnology(technologyId);
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/employee/{employeeEmail}/category/{categoryId}")
    public ResponseEntity<List<EmployeeTechnologySkillDTO>> getSkillsByEmployeeAndCategory(
            @PathVariable String employeeEmail,
            @PathVariable Long categoryId) {
        List<EmployeeTechnologySkillDTO> skills = skillService.getSkillsByEmployeeAndCategory(employeeEmail, categoryId);
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/employee/{employeeEmail}/technology/{technologyId}")
    public ResponseEntity<EmployeeTechnologySkillDTO> getSkill(
            @PathVariable String employeeEmail,
            @PathVariable Long technologyId) {
        EmployeeTechnologySkillDTO skill = skillService.getSkill(employeeEmail, technologyId);
        if (skill != null) {
            return ResponseEntity.ok(skill);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/employee/{employeeEmail}/technology/{technologyId}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable String employeeEmail,
            @PathVariable Long technologyId) {
        skillService.deleteSkill(employeeEmail, technologyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}