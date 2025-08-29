package gestionalebackend.gestionalebackend.project.service;

import gestionalebackend.gestionalebackend.project.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    
    List<ProjectDTO> getAllProjects();
    
    List<ProjectDTO> getActiveProjects();
    
    ProjectDTO getProjectById(Long id);
    
    ProjectDTO getProjectByCodice(String codiceProgetto);
    
    ProjectDTO createProject(ProjectDTO projectDTO);
    
    ProjectDTO updateProject(Long id, ProjectDTO projectDTO);
    
    void deleteProject(Long id);
    
    List<ProjectDTO> searchProjects(String nome);
    
    List<ProjectDTO> getProjectsByIds(List<Long> ids);
}