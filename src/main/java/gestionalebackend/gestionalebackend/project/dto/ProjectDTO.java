package gestionalebackend.gestionalebackend.project.dto;
import java.sql.Date;

public record ProjectDTO(
        Long id,
        String nome,
        String codiceProgetto,
        String descrizione,
        String referenteProgetto,
        Date dataInizio,
        Date dataFine,
        Boolean attivo
) {
}