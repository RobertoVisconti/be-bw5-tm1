package robertovisconti.be_bw5_tm1.payloadsDTO.fattura;

import jakarta.validation.constraints.NotBlank;
import robertovisconti.be_bw5_tm1.entities.Cliente;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;

import java.time.LocalDate;
import java.util.UUID;

public record RichiestaNuovaFatturaDTO(
        @NotBlank LocalDate data,
        @NotBlank double importo,
        @NotBlank int numero,
        @NotBlank Cliente cliente,
        StatoFattura statoFattura) {
}
