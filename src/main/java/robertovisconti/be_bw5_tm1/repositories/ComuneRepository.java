package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import robertovisconti.be_bw5_tm1.entities.Comune;

import java.util.UUID;

public interface ComuneRepository extends JpaRepository<Comune, UUID> {
}
