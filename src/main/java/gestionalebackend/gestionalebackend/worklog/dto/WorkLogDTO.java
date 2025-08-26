package gestionalebackend.gestionalebackend.worklog.dto;

import gestionalebackend.gestionalebackend.worklog.model.DayType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WorkLogDTO(
        Long id,
        String employeeEmail,
        String employeeName,
        Long projectId,
        String projectName,
        LocalDate data,
        BigDecimal ore,
        DayType tipo,
        String note
) {
}