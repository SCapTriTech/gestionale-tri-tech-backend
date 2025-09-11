package gestionalebackend.gestionalebackend.project.dto;
import java.sql.Date;

public record ProjectDTO(
        Long id,
        String name,
        String projectCode,
        String description,
        String projectManager,
        Date startDate,
        Date endDate,
        Boolean active
) {
}