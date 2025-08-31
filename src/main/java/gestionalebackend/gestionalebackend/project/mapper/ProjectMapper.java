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
                project.getNome(),
                project.getCodiceProgetto(),
                project.getDescrizione(),
                project.getReferenteProgetto(),
                project.getDataInizio(),
                project.getDataFine(),
                project.getAttivo()
        );
    }
    
    public static Project convertToDAO(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return Project.builder()
                .id(dto.id())
                .nome(dto.nome())
                .codiceProgetto(dto.codiceProgetto())
                .descrizione(dto.descrizione())
                .referenteProgetto(dto.referenteProgetto())
                .dataInizio(dto.dataInizio())
                .dataFine(dto.dataFine())
                .attivo(dto.attivo() != null ? dto.attivo() : true)
                .build();
    }
}