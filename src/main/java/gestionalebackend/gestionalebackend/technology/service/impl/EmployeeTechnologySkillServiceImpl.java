package gestionalebackend.gestionalebackend.technology.service.impl;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.employee.repository.EmployeeRepository;
import gestionalebackend.gestionalebackend.technology.dto.EmployeeTechnologySkillDTO;
import gestionalebackend.gestionalebackend.technology.model.EmployeeTechnologySkill;
import gestionalebackend.gestionalebackend.technology.model.EmployeeTechnologySkillId;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import gestionalebackend.gestionalebackend.technology.repository.EmployeeTechnologySkillRepository;
import gestionalebackend.gestionalebackend.technology.repository.TechnologyRepository;
import gestionalebackend.gestionalebackend.technology.service.EmployeeTechnologySkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeTechnologySkillServiceImpl implements EmployeeTechnologySkillService {
    
    private final EmployeeTechnologySkillRepository skillRepository;
    private final EmployeeRepository employeeRepository;
    private final TechnologyRepository technologyRepository;
    
    @Override
    public EmployeeTechnologySkillDTO saveOrUpdateSkill(EmployeeTechnologySkillDTO skillDTO) {
        Employee employee = employeeRepository.findById(skillDTO.getEmployeeEmail())
                .orElseThrow(() -> new RuntimeException("Employee not found: " + skillDTO.getEmployeeEmail()));
        
        Technology technology = technologyRepository.findById(skillDTO.getTechnologyId())
                .orElseThrow(() -> new RuntimeException("Technology not found: " + skillDTO.getTechnologyId()));
        
        EmployeeTechnologySkillId skillId = EmployeeTechnologySkillId.builder()
                .employeeEmail(skillDTO.getEmployeeEmail())
                .technologyId(skillDTO.getTechnologyId())
                .build();
        
        EmployeeTechnologySkill skill = skillRepository.findById(skillId)
                .orElse(EmployeeTechnologySkill.builder()
                        .id(skillId)
                        .employee(employee)
                        .technology(technology)
                        .build());
        
        skill.setSkillLevel(skillDTO.getSkillLevel());
        skill.setLastAssessmentDate(LocalDate.now());
        skill.setNotes(skillDTO.getNotes());
        skill.setIsCertified(skillDTO.getIsCertified());
        skill.setCertificationDate(skillDTO.getCertificationDate());
        
        EmployeeTechnologySkill savedSkill = skillRepository.save(skill);
        return mapToDTO(savedSkill);
    }
    
    @Override
    public List<EmployeeTechnologySkillDTO> saveOrUpdateMultipleSkills(String employeeEmail, List<EmployeeTechnologySkillDTO> skills) {
        List<EmployeeTechnologySkillDTO> savedSkills = new ArrayList<>();
        for (EmployeeTechnologySkillDTO skill : skills) {
            skill.setEmployeeEmail(employeeEmail);
            savedSkills.add(saveOrUpdateSkill(skill));
        }
        return savedSkills;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeTechnologySkillDTO> getSkillsByEmployee(String employeeEmail) {
        return skillRepository.findByEmployeeEmail(employeeEmail).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeTechnologySkillDTO> getSkillsByTechnology(Long technologyId) {
        return skillRepository.findByTechnologyId(technologyId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeTechnologySkillDTO> getSkillsByEmployeeAndCategory(String employeeEmail, Long categoryId) {
        return skillRepository.findByEmployeeEmailAndCategoryId(employeeEmail, categoryId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteSkill(String employeeEmail, Long technologyId) {
        EmployeeTechnologySkillId skillId = EmployeeTechnologySkillId.builder()
                .employeeEmail(employeeEmail)
                .technologyId(technologyId)
                .build();
        skillRepository.deleteById(skillId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public EmployeeTechnologySkillDTO getSkill(String employeeEmail, Long technologyId) {
        EmployeeTechnologySkillId skillId = EmployeeTechnologySkillId.builder()
                .employeeEmail(employeeEmail)
                .technologyId(technologyId)
                .build();
        
        return skillRepository.findById(skillId)
                .map(this::mapToDTO)
                .orElse(null);
    }
    
    private EmployeeTechnologySkillDTO mapToDTO(EmployeeTechnologySkill skill) {
        return EmployeeTechnologySkillDTO.builder()
                .employeeEmail(skill.getEmployee().getEmail())
                .technologyId(skill.getTechnology().getId())
                .technologyName(skill.getTechnology().getName())
                .categoryName(skill.getTechnology().getCategory() != null ? 
                        skill.getTechnology().getCategory().getName() : null)
                .skillLevel(skill.getSkillLevel())
                .lastAssessmentDate(skill.getLastAssessmentDate())
                .notes(skill.getNotes())
                .isCertified(skill.getIsCertified())
                .certificationDate(skill.getCertificationDate())
                .build();
    }
}