package gestionalebackend.gestionalebackend.technology.repository;

import gestionalebackend.gestionalebackend.technology.model.EmployeeTechnologySkill;
import gestionalebackend.gestionalebackend.technology.model.EmployeeTechnologySkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTechnologySkillRepository extends JpaRepository<EmployeeTechnologySkill, EmployeeTechnologySkillId> {
    
    List<EmployeeTechnologySkill> findByEmployeeEmail(String employeeEmail);
    
    List<EmployeeTechnologySkill> findByTechnologyId(Long technologyId);
    
    @Query("SELECT ets FROM EmployeeTechnologySkill ets WHERE ets.employee.email = :email AND ets.skillLevel >= :minLevel")
    List<EmployeeTechnologySkill> findByEmployeeEmailAndMinSkillLevel(@Param("email") String email, @Param("minLevel") Integer minLevel);
    
    @Query("SELECT ets FROM EmployeeTechnologySkill ets WHERE ets.technology.category.id = :categoryId")
    List<EmployeeTechnologySkill> findByCategoryId(@Param("categoryId") Long categoryId);
    
    @Query("SELECT ets FROM EmployeeTechnologySkill ets WHERE ets.employee.email = :email AND ets.technology.category.id = :categoryId")
    List<EmployeeTechnologySkill> findByEmployeeEmailAndCategoryId(@Param("email") String email, @Param("categoryId") Long categoryId);
}