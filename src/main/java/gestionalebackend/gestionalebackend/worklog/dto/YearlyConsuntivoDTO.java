package gestionalebackend.gestionalebackend.worklog.dto;

import java.util.List;

public record YearlyConsuntivoDTO(
        Integer year,
        Long projectId,
        String projectName,
        List<EmployeeYearlyData> employees
) {
}