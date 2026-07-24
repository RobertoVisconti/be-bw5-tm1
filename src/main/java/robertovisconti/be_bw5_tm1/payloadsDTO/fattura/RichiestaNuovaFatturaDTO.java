package robertovisconti.be_bw5_tm1.payloadsDTO.fattura;

import jakarta.validation.constraints.NotNull;
import robertovisconti.be_bw5_tm1.entities.Cliente;

import java.time.LocalDate;

public record RichiestaNuovaFatturaDTO(
        @NotNull(message = "La data è obbligatoria") LocalDate data,
        @NotNull(message = "L'importo è obbligatorio") Double importo,
        @NotNull(message = "Il numero è obbligatorio") Integer numero,
        @NotNull(message = "Il cliente è obbligatorio") Cliente cliente
) {
}
