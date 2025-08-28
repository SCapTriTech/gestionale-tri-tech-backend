package gestionalebackend.gestionalebackend.technology.controller.impl;

import gestionalebackend.gestionalebackend.technology.controller.TechnologyController;
import gestionalebackend.gestionalebackend.technology.dto.TechnologyDTO;
import gestionalebackend.gestionalebackend.technology.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TechnologyControllerImpl implements TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @Override
    public ResponseEntity<List<TechnologyDTO>> getAllTechnologies() {
        try {
            List<TechnologyDTO> technologies = technologyService.findAll();
            return ResponseEntity.ok(technologies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TechnologyDTO> getTechnologyById(Long id) {
        try {
            TechnologyDTO technology = technologyService.findById(id);
            return ResponseEntity.ok(technology);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TechnologyDTO> getTechnologyByName(String name) {
        try {
            TechnologyDTO technology = technologyService.findByName(name);
            return ResponseEntity.ok(technology);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TechnologyDTO> createTechnology(TechnologyDTO technologyDTO) {
        try {
            TechnologyDTO createdTechnology = technologyService.create(technologyDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTechnology);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<TechnologyDTO> updateTechnologyById(Long id, TechnologyDTO technologyDTO) {
        try {
            TechnologyDTO updatedTechnology = technologyService.updateById(id, technologyDTO);
            return ResponseEntity.ok(updatedTechnology);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<TechnologyDTO> updateTechnologyByName(String name, TechnologyDTO technologyDTO) {
        try {
            TechnologyDTO updatedTechnology = technologyService.updateByName(name, technologyDTO);
            return ResponseEntity.ok(updatedTechnology);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteTechnologyById(Long id) {
        try {
            technologyService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteTechnologyByName(String name) {
        try {
            technologyService.deleteByName(name);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}