package gestionalebackend.gestionalebackend.office.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeRequestDto {
    private String nomeSede;
    private String via;
    private String numeroCivico;
    private Integer postiDisponibili;
}