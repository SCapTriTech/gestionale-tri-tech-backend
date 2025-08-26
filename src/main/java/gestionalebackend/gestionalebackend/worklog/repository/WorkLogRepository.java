package gestionalebackend.gestionalebackend.worklog.repository;

import gestionalebackend.gestionalebackend.worklog.model.WorkLog;
import gestionalebackend.gestionalebackend.worklog.model.DayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    
    List<WorkLog> findByEmployeeEmailAndDataBetween(String employeeEmail, LocalDate startDate, LocalDate endDate);
    
    List<WorkLog> findByProjectIdAndDataBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<WorkLog> findByDataBetween(LocalDate startDate, LocalDate endDate);
    
    List<WorkLog> findByEmployeeEmailAndProjectIdAndDataBetween(
            String employeeEmail, Long projectId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT w FROM ORE_LAVORATE w WHERE YEAR(w.data) = :year")
    List<WorkLog> findByYear(@Param("year") int year);
    
    @Query("SELECT w FROM ORE_LAVORATE w WHERE YEAR(w.data) = :year AND MONTH(w.data) = :month")
    List<WorkLog> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT w FROM ORE_LAVORATE w WHERE w.employee.email = :email AND YEAR(w.data) = :year")
    List<WorkLog> findByEmployeeAndYear(@Param("email") String email, @Param("year") int year);
    
    List<WorkLog> findByEmployeeEmailAndData(String employeeEmail, LocalDate data);
    
    List<WorkLog> findByTipoAndDataBetween(DayType tipo, LocalDate startDate, LocalDate endDate);
}