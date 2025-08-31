package gestionalebackend.gestionalebackend.project.service.impl;

import gestionalebackend.gestionalebackend.project.dto.ProjectDTO;
import gestionalebackend.gestionalebackend.project.mapper.ProjectMapper;
import gestionalebackend.gestionalebackend.project.model.Project;
import gestionalebackend.gestionalebackend.project.repository.ProjectRepository;
import gestionalebackend.gestionalebackend.project.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .map(ProjectMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ProjectDTO> getActiveProjects() {
        return projectRepository.findByAttivoTrue()
                .stream()
                .map(ProjectMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Progetto non trovato con id: " + id));
        return ProjectMapper.convertToDTO(project);
    }
    
    @Override
    public ProjectDTO getProjectByCodice(String codiceProgetto) {
        Project project = projectRepository.findByCodiceProgetto(codiceProgetto)
                .orElseThrow(() -> new EntityNotFoundException("Progetto non trovato con codice: " + codiceProgetto));
        return ProjectMapper.convertToDTO(project);
    }
    
    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.convertToDAO(projectDTO);
        Project savedProject = projectRepository.save(project);
        return ProjectMapper.convertToDTO(savedProject);
    }
    
    @Override
    public ProjectDTO updateProject(String codiceProgetto, ProjectDTO projectDTO) {
        Project project = projectRepository.findByCodiceProgetto(codiceProgetto)
                .orElseThrow(() -> new EntityNotFoundException("Progetto non trovato con codiceProgetto: " + codiceProgetto));
        
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
        return ProjectMapper.convertToDTO(updatedProject);
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
                .map(ProjectMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ProjectDTO> getProjectsByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        
        return projectRepository.findAllById(ids)
                .stream()
                .map(ProjectMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    
}