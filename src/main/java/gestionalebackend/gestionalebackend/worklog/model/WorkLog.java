package gestionalebackend.gestionalebackend.worklog.model;

import gestionalebackend.gestionalebackend.employee.model.Employee;
import gestionalebackend.gestionalebackend.project.model.Project;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "WORK_LOGS")
@Table(name = "WORK_LOGS")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_email", nullable = false)
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal hours;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayType dayType;
    
    @Column(length = 500)
    private String notes;
    
}