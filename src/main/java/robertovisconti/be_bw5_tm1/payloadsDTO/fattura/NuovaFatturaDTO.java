package robertovisconti.be_bw5_tm1.payloadsDTO.fattura;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record NuovaFatturaDTO(
        @NotBlank LocalDate data,
        @NotBlank double importo,
        @NotBlank int numero,
        @NotBlank UUID idUtente,
        UUID idStatoFattura) {
}
