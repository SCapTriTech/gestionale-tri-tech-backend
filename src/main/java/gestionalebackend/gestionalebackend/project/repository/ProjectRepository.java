package gestionalebackend.gestionalebackend.project.repository;

import gestionalebackend.gestionalebackend.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    Optional<Project> findByCodiceProgetto(String codiceProgetto);
    
    List<Project> findByReferenteProgetto(String referenteProgetto);
    
    List<Project> findByAttivoTrue();
    
    List<Project> findByNomeContainingIgnoreCase(String nome);

}