package robertovisconti.be_bw5_tm1.payloadsDTO;

import jakarta.validation.constraints.NotBlank;

public record UtenteDTO(
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String nome,
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String cognome,
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String username,
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String email,
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String password,
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String ruolo
) {
}
