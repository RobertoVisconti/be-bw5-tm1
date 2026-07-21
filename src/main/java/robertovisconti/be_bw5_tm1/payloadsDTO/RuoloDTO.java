package robertovisconti.be_bw5_tm1.payloadsDTO;

import jakarta.validation.constraints.NotBlank;

public record RuoloDTO(
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String ruolo) {
}
