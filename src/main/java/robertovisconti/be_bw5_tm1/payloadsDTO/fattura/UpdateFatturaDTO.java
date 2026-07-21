package robertovisconti.be_bw5_tm1.payloadsDTO.fattura;


import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateFatturaDTO(@NotBlank UUID idStatoFattura) {
}
