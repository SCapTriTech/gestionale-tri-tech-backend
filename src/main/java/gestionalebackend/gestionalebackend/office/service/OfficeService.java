package gestionalebackend.gestionalebackend.office.service;

import gestionalebackend.gestionalebackend.office.dto.OfficeRequestDto;
import gestionalebackend.gestionalebackend.office.dto.OfficeResponseDto;
import java.util.List;

public interface OfficeService {
    OfficeResponseDto createOffice(OfficeRequestDto requestDto);
    OfficeResponseDto getOfficeById(Long id);
    List<OfficeResponseDto> getAllOffices();
    OfficeResponseDto updateOffice(Long id, OfficeRequestDto requestDto);
    void deleteOffice(Long id);
}