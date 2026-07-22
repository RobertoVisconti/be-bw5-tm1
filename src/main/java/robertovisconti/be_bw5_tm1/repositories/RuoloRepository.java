package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robertovisconti.be_bw5_tm1.entities.Ruolo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, UUID> {

    boolean existsByRuolo(String ruolo);

    Optional<Ruolo> findByRuolo(String ruolo);
}
