package gestionalebackend.gestionalebackend.technology.service;

import gestionalebackend.gestionalebackend.technology.dto.EmployeeTechnologySkillDTO;
import java.util.List;

public interface EmployeeTechnologySkillService {
    
    EmployeeTechnologySkillDTO saveOrUpdateSkill(EmployeeTechnologySkillDTO skillDTO);
    
    List<EmployeeTechnologySkillDTO> saveOrUpdateMultipleSkills(String employeeEmail, List<EmployeeTechnologySkillDTO> skills);
    
    List<EmployeeTechnologySkillDTO> getSkillsByEmployee(String employeeEmail);
    
    List<EmployeeTechnologySkillDTO> getSkillsByTechnology(Long technologyId);
    
    List<EmployeeTechnologySkillDTO> getSkillsByEmployeeAndCategory(String employeeEmail, Long categoryId);
    
    void deleteSkill(String employeeEmail, Long technologyId);
    
    EmployeeTechnologySkillDTO getSkill(String employeeEmail, Long technologyId);
}