package gestionalebackend.gestionalebackend.office.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeResponseDto {
    private Long id;
    private String officeName;
    private String street;
    private String streetNumber;
    private Integer availableSeats;
}