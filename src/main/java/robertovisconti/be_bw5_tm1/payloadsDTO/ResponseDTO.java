package robertovisconti.be_bw5_tm1.payloadsDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseDTO(String message, UUID id, Long longId, LocalDateTime createdAt) {

    public ResponseDTO(String message, UUID id, LocalDateTime createdAt) {
        this(message, id, null, createdAt);
    }

    public ResponseDTO(String message, Long longId, LocalDateTime createdAt) {
        this(message, null, longId, createdAt);
    }
}

