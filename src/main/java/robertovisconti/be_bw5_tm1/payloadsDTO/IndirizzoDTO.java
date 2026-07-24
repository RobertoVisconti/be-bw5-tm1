package robertovisconti.be_bw5_tm1.payloadsDTO;

import java.util.UUID;

public record IndirizzoDTO(
        String via,
        String civico,
        String localita,
        String cap,
        String tipoIndirizzo,
        UUID comuneId // Oppure String nomeComune
) {
}
