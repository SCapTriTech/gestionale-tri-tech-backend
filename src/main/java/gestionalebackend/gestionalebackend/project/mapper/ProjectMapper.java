package gestionalebackend.gestionalebackend.project.mapper;

import gestionalebackend.gestionalebackend.project.dto.ProjectDTO;
import gestionalebackend.gestionalebackend.project.model.Project;

public class ProjectMapper {
    
    public static ProjectDTO convertToDTO(Project project) {
        if (project == null) {
            return null;
        }
        
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getProjectCode(),
                project.getDescription(),
                project.getProjectManager(),
                project.getStartDate(),
                project.getEndDate(),
                project.getActive()
        );
    }
    
    public static Project convertToDAO(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return Project.builder()
                .id(dto.id())
                .name(dto.name())
                .projectCode(dto.projectCode())
                .description(dto.description())
                .projectManager(dto.projectManager())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .active(dto.active() != null ? dto.active() : true)
                .build();
    }
}