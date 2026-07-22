package robertovisconti.be_bw5_tm1.payloadsDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteResponseDTO(
        UUID id,
        String message,
        LocalDateTime createdAt
) {
}
