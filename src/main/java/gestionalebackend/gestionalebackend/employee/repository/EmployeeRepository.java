package gestionalebackend.gestionalebackend.employee.repository;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    
    @Query("SELECT DISTINCT e FROM DIPENDENTI e LEFT JOIN FETCH e.projects")
    List<Employee> findAllWithProjects();
    
    @Query("SELECT DISTINCT e FROM DIPENDENTI e LEFT JOIN FETCH e.projects")
    Page<Employee> findAllWithProjects(Pageable pageable);
    
    @Query("SELECT e FROM DIPENDENTI e LEFT JOIN FETCH e.projects WHERE e.email = :email")
    Optional<Employee> findByIdWithProjects(String email);
}