package robertovisconti.be_bw5_tm1.payloadsDTO.fattura;


import jakarta.validation.constraints.NotBlank;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;

import java.util.UUID;

public record RichiestaUpdateFatturaDTO(@NotBlank StatoFattura statoFattura) {
}
