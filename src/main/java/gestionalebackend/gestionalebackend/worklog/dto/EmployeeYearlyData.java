package gestionalebackend.gestionalebackend.worklog.dto;

import gestionalebackend.gestionalebackend.worklog.model.DayType;

import java.math.BigDecimal;
import java.util.Map;

public record EmployeeYearlyData(
        String employeeEmail,
        String employeeName,
        Map<Integer, Map<Integer, Map<DayType, BigDecimal>>> monthlyData
        // Structure: Month (1-12) -> Day (1-31) -> DayType -> Hours
) {
}