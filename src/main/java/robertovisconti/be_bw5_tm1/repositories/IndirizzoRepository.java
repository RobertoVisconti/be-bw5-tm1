package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import robertovisconti.be_bw5_tm1.entities.Indirizzo;

import java.util.List;
import java.util.UUID;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {

    List<Indirizzo> findByComune_NomeContainingIgnoreCase(String parteNome);

    List<Indirizzo> findByComune_Nome(String nomeComune);

    List<Indirizzo> findByComune_Provincia_Nome(String nomeProvincia);
}
