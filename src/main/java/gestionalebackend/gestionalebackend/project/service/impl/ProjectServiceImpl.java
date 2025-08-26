package gestionalebackend.gestionalebackend.project.service.impl;

import gestionalebackend.gestionalebackend.project.dto.ProjectDTO;
import gestionalebackend.gestionalebackend.project.model.Project;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;
import gestionalebackend.gestionalebackend.project.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    
    private final ProjectRepository projectRepository;
    
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ProjectDTO> getActiveProjects() {
        return projectRepository.findByAttivoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Progetto non trovato con id: " + id));
        return convertToDTO(project);
    }
    
    @Override
    public ProjectDTO getProjectByCodice(String codiceProgetto) {
        Project project = projectRepository.findByCodiceProgetto(codiceProgetto)
                .orElseThrow(() -> new EntityNotFoundException("Progetto non trovato con codice: " + codiceProgetto));
        return convertToDTO(project);
    }
    
    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project savedProject = projectRepository.save(project);
        return convertToDTO(savedProject);
    }
    
    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Progetto non trovato con id: " + id));
        
        if (projectDTO.nome() != null) {
            project.setNome(projectDTO.nome());
        }

        if (projectDTO.descrizione() != null) {
            project.setDescrizione(projectDTO.descrizione());
        }
        if (projectDTO.referenteProgetto() != null) {
            project.setReferenteProgetto(projectDTO.referenteProgetto());
        }
        if (projectDTO.dataInizio() != null) {
            project.setDataInizio(projectDTO.dataInizio());
        }
        if (projectDTO.dataFine() != null) {
            project.setDataFine(projectDTO.dataFine());
        }
        if (projectDTO.attivo() != null) {
            project.setAttivo(projectDTO.attivo());
        }
        
        Project updatedProject = projectRepository.save(project);
        return convertToDTO(updatedProject);
    }
    
    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Progetto non trovato con id: " + id);
        }
        projectRepository.deleteById(id);
    }
    
    @Override
    public List<ProjectDTO> searchProjects(String nome) {
        return projectRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private ProjectDTO convertToDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getNome(),
                project.getDescrizione(),
                project.getReferenteProgetto(),
                project.getDataInizio(),
                project.getDataFine(),
                project.getAttivo()
        );
    }
    
    private Project convertToEntity(ProjectDTO dto) {
        return Project.builder()
                .id(dto.id())
                .nome(dto.nome())
                .descrizione(dto.descrizione())
                .referenteProgetto(dto.referenteProgetto())
                .dataInizio(dto.dataInizio())
                .dataFine(dto.dataFine())
                .attivo(dto.attivo() != null ? dto.attivo() : true)
                .build();
    }
}