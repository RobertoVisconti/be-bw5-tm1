package robertovisconti.be_bw5_tm1.payloadsDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseDTO(String message, UUID id, LocalDateTime createdAt) {
}
