package gestionalebackend.gestionalebackend.technology.service;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyDTO;

import java.util.List;

public interface TechnologyService {
    
    List<TechnologyDTO> findAll();
    
    TechnologyDTO findById(Long id);
    
    TechnologyDTO findByName(String name);
    
    TechnologyDTO create(TechnologyDTO technologyDTO);
    
    TechnologyDTO updateById(Long id, TechnologyDTO technologyDTO);
    
    TechnologyDTO updateByName(String name, TechnologyDTO technologyDTO);
    
    void deleteById(Long id);
    
    void deleteByName(String name);
    
}