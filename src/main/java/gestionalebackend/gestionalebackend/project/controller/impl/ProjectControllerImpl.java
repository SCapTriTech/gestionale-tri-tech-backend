package gestionalebackend.gestionalebackend.project.controller.impl;

import gestionalebackend.gestionalebackend.project.controller.ProjectController;
import gestionalebackend.gestionalebackend.project.dto.ProjectDTO;
import gestionalebackend.gestionalebackend.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectControllerImpl implements ProjectController {
    
    private final ProjectService projectService;
    
    public ProjectControllerImpl(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @Override
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
    
    @Override
    public ResponseEntity<List<ProjectDTO>> getActiveProjects() {
        List<ProjectDTO> projects = projectService.getActiveProjects();
        return ResponseEntity.ok(projects);
    }
    
    @Override
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        ProjectDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }
    
    @Override
    public ResponseEntity<ProjectDTO> getProjectByCodice(@PathVariable String codiceProgetto) {
        ProjectDTO project = projectService.getProjectByCodice(codiceProgetto);
        return ResponseEntity.ok(project);
    }
    
    @Override
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }
    
    @Override
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(updatedProject);
    }
    
    @Override
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    public ResponseEntity<List<ProjectDTO>> searchProjects(@RequestParam String nome) {
        List<ProjectDTO> projects = projectService.searchProjects(nome);
        return ResponseEntity.ok(projects);
    }
    
    @Override
    public ResponseEntity<List<ProjectDTO>> getProjectsByIds(@RequestParam List<Long> ids) {
        List<ProjectDTO> projects = projectService.getProjectsByIds(ids);
        return ResponseEntity.ok(projects);
    }
}