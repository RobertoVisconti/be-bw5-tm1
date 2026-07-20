package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import robertovisconti.be_bw5_tm1.entities.Provincia;

import java.util.Optional;
import java.util.UUID;

public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

    Optional<Provincia> findBySiglaIgnoreCase(String sigla);
}
