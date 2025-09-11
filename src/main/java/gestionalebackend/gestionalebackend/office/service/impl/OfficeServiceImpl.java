package gestionalebackend.gestionalebackend.office.service.impl;

import gestionalebackend.gestionalebackend.office.dto.OfficeRequestDto;
import gestionalebackend.gestionalebackend.office.dto.OfficeResponseDto;
import gestionalebackend.gestionalebackend.office.model.Office;
import gestionalebackend.gestionalebackend.office.repository.OfficeRepository;
import gestionalebackend.gestionalebackend.office.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OfficeServiceImpl implements OfficeService {
    
    private final OfficeRepository officeRepository;
    
    @Override
    public OfficeResponseDto createOffice(OfficeRequestDto requestDto) {
        Office office = new Office();
        office.setOfficeName(requestDto.getOfficeName());
        office.setStreet(requestDto.getStreet());
        office.setStreetNumber(requestDto.getStreetNumber());
        office.setAvailableSeats(requestDto.getAvailableSeats());
        
        Office savedOffice = officeRepository.save(office);
        return convertToResponseDto(savedOffice);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OfficeResponseDto getOfficeById(Long id) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));
        return convertToResponseDto(office);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OfficeResponseDto> getAllOffices() {
        return officeRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public OfficeResponseDto updateOffice(Long id, OfficeRequestDto requestDto) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));
        
        office.setOfficeName(requestDto.getOfficeName());
        office.setStreet(requestDto.getStreet());
        office.setStreetNumber(requestDto.getStreetNumber());
        office.setAvailableSeats(requestDto.getAvailableSeats());
        
        Office updatedOffice = officeRepository.save(office);
        return convertToResponseDto(updatedOffice);
    }
    
    @Override
    public void deleteOffice(Long id) {
        if (!officeRepository.existsById(id)) {
            throw new RuntimeException("Office not found with id: " + id);
        }
        officeRepository.deleteById(id);
    }
    
    private OfficeResponseDto convertToResponseDto(Office office) {
        OfficeResponseDto responseDto = new OfficeResponseDto();
        responseDto.setId(office.getId());
        responseDto.setOfficeName(office.getOfficeName());
        responseDto.setStreet(office.getStreet());
        responseDto.setStreetNumber(office.getStreetNumber());
        responseDto.setAvailableSeats(office.getAvailableSeats());
        return responseDto;
    }
}