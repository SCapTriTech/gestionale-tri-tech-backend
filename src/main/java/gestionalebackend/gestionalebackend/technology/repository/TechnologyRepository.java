package gestionalebackend.gestionalebackend.technology.repository;

import gestionalebackend.gestionalebackend.technology.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    
    Optional<Technology> findByName(String name);
    
    Optional<Technology> findByNameIgnoreCase(String name);
    
    @Modifying
    @Query("DELETE FROM TECNOLOGIE t WHERE t.name = :name")
    void deleteByName(@Param("name") String name);
    
}