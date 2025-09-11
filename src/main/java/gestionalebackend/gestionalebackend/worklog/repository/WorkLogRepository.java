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
    
    List<WorkLog> findByEmployeeEmailAndDateBetween(String employeeEmail, LocalDate startDate, LocalDate endDate);
    
    List<WorkLog> findByProjectIdAndDateBetween(Long projectId, LocalDate startDate, LocalDate endDate);
    
    List<WorkLog> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<WorkLog> findByEmployeeEmailAndProjectIdAndDateBetween(
            String employeeEmail, Long projectId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT w FROM WORK_LOGS w WHERE YEAR(w.date) = :year")
    List<WorkLog> findByYear(@Param("year") int year);
    
    @Query("SELECT w FROM WORK_LOGS w WHERE YEAR(w.date) = :year AND MONTH(w.date) = :month")
    List<WorkLog> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT w FROM WORK_LOGS w WHERE w.employee.email = :email AND YEAR(w.date) = :year")
    List<WorkLog> findByEmployeeAndYear(@Param("email") String email, @Param("year") int year);
    
    List<WorkLog> findByEmployeeEmailAndDate(String employeeEmail, LocalDate date);
    
    List<WorkLog> findByDayTypeAndDateBetween(DayType dayType, LocalDate startDate, LocalDate endDate);
}