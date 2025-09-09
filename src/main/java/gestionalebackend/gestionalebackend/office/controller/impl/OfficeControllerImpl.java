package gestionalebackend.gestionalebackend.office.controller.impl;

import gestionalebackend.gestionalebackend.office.controller.OfficeController;
import gestionalebackend.gestionalebackend.office.dto.OfficeRequestDto;
import gestionalebackend.gestionalebackend.office.dto.OfficeResponseDto;
import gestionalebackend.gestionalebackend.office.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfficeControllerImpl implements OfficeController {
    
    private final OfficeService officeService;
    
    @Override
    public ResponseEntity<OfficeResponseDto> createOffice(OfficeRequestDto requestDto) {
        OfficeResponseDto responseDto = officeService.createOffice(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    
    @Override
    public ResponseEntity<OfficeResponseDto> getOfficeById(Long id) {
        OfficeResponseDto responseDto = officeService.getOfficeById(id);
        return ResponseEntity.ok(responseDto);
    }
    
    @Override
    public ResponseEntity<List<OfficeResponseDto>> getAllOffices() {
        List<OfficeResponseDto> offices = officeService.getAllOffices();
        return ResponseEntity.ok(offices);
    }
    
    @Override
    public ResponseEntity<OfficeResponseDto> updateOffice(Long id, OfficeRequestDto requestDto) {
        OfficeResponseDto responseDto = officeService.updateOffice(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
    
    @Override
    public ResponseEntity<Void> deleteOffice(Long id) {
        officeService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }
}