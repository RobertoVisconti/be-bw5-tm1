package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;

import java.util.Optional;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

    Optional<StatoFattura> findByTitolo(String titolo);
}
