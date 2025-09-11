package gestionalebackend.gestionalebackend.technology.repository;

import gestionalebackend.gestionalebackend.technology.model.TechnologyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyCategoryRepository extends JpaRepository<TechnologyCategory, Long> {
    Optional<TechnologyCategory> findByName(String name);
    boolean existsByName(String name);
}