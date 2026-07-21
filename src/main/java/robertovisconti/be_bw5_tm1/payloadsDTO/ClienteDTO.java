package robertovisconti.be_bw5_tm1.payloadsDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import robertovisconti.be_bw5_tm1.enums.TipoCliente;

import java.util.UUID;

public record ClienteDTO(

        @NotBlank(message = "La ragione sociale è obbligatoria!")
        String ragioneSociale,

        @NotBlank(message = "La partita IVA è obbligatoria!")
        @Pattern(regexp = "^[0-9]{11}$", message = "La Partita IVA deve essere composta da esattamente 11 cifre")
        String partitaIva,

        @Email(message = "Inserire un indirizzo PEC valido!")
        String pec,

        @Pattern(regexp = "^\\+?[0-9]{6,15}$", message = "Il numero di telefono non è valido")
        String telefono,

        @Email(message = "Inserire un email valida!")
        String emailContatto,

        String nomeContatto,
        String cognomeContatto,
        String logoAziendale,

        @NotNull(message = "Il tipo cliente è obbligatorio!")
        TipoCliente tipoCliente
        
        UUID idSedeLegale,
        UUID idSedeOperativa
) {
}
