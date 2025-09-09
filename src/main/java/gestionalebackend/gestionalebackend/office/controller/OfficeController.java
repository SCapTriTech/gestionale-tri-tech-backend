package gestionalebackend.gestionalebackend.office.controller;

import gestionalebackend.gestionalebackend.office.dto.OfficeRequestDto;
import gestionalebackend.gestionalebackend.office.dto.OfficeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/offices")
public interface OfficeController {
    
    @PostMapping
    ResponseEntity<OfficeResponseDto> createOffice(@RequestBody OfficeRequestDto requestDto);
    
    @GetMapping("/{id}")
    ResponseEntity<OfficeResponseDto> getOfficeById(@PathVariable Long id);
    
    @GetMapping
    ResponseEntity<List<OfficeResponseDto>> getAllOffices();
    
    @PutMapping("/{id}")
    ResponseEntity<OfficeResponseDto> updateOffice(@PathVariable Long id, @RequestBody OfficeRequestDto requestDto);
    
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOffice(@PathVariable Long id);
}