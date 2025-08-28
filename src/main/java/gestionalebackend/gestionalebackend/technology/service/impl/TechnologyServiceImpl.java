package gestionalebackend.gestionalebackend.technology.service.impl;

import gestionalebackend.gestionalebackend.technology.dto.TechnologyDTO;
import gestionalebackend.gestionalebackend.technology.mapper.TechnologyMapper;
import gestionalebackend.gestionalebackend.technology.model.Technology;
import gestionalebackend.gestionalebackend.technology.repository.TechnologyRepository;
import gestionalebackend.gestionalebackend.technology.service.TechnologyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public List<TechnologyDTO> findAll() {
        return technologyRepository.findAll()
                .stream()
                .map(TechnologyMapper::convertToDTO)
                .toList();
    }

    @Override
    public TechnologyDTO findById(Long id) {
        Optional<Technology> technology = technologyRepository.findById(id);
        if (technology.isPresent()) {
            return TechnologyMapper.convertToDTO(technology.get());
        }
        throw new RuntimeException("Technology not found with id: " + id);
    }

    @Override
    public TechnologyDTO findByName(String name) {
        Optional<Technology> technology = technologyRepository.findByNameIgnoreCase(name);
        if (technology.isPresent()) {
            return TechnologyMapper.convertToDTO(technology.get());
        }
        throw new RuntimeException("Technology not found with name: " + name);
    }

    @Override
    @Transactional
    public TechnologyDTO create(TechnologyDTO technologyDTO) {
        Technology technology = TechnologyMapper.convertToDAO(technologyDTO);
        Technology savedTechnology = technologyRepository.save(technology);
        return TechnologyMapper.convertToDTO(savedTechnology);
    }

    @Override
    @Transactional
    public TechnologyDTO updateById(Long id, TechnologyDTO technologyDTO) {
        Optional<Technology> existingTechnology = technologyRepository.findById(id);
        if (existingTechnology.isPresent()) {
            Technology technology = existingTechnology.get();
            technology.setName(technologyDTO.name());
            technology.setDescription(technologyDTO.description());
            Technology updatedTechnology = technologyRepository.save(technology);
            return TechnologyMapper.convertToDTO(updatedTechnology);
        }
        throw new RuntimeException("Technology not found with id: " + id);
    }

    @Override
    @Transactional
    public TechnologyDTO updateByName(String name, TechnologyDTO technologyDTO) {
        Optional<Technology> existingTechnology = technologyRepository.findByNameIgnoreCase(name);
        if (existingTechnology.isPresent()) {
            Technology technology = existingTechnology.get();
            technology.setName(technologyDTO.name());
            technology.setDescription(technologyDTO.description());
            Technology updatedTechnology = technologyRepository.save(technology);
            return TechnologyMapper.convertToDTO(updatedTechnology);
        }
        throw new RuntimeException("Technology not found with name: " + name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (technologyRepository.existsById(id)) {
            technologyRepository.deleteById(id);
        } else {
            throw new RuntimeException("Technology not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Technology> technology = technologyRepository.findByNameIgnoreCase(name);
        if (technology.isPresent()) {
            technologyRepository.deleteByName(name);
        } else {
            throw new RuntimeException("Technology not found with name: " + name);
        }
    }
}