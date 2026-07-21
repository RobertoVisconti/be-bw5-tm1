package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import robertovisconti.be_bw5_tm1.entities.Fattura;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    Optional<Fattura> findByIdCliente(UUID idCliente);

    List<Fattura> findByIdStatoFattura(UUID idStatoFattura, Pageable pageable);

    List<Fattura> findByIData(LocalDate data, Pageable pageable);

    @Query("""
    SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno
    """)
    List<Fattura> findByAnno(@Param("anno") int anno);

    @Query("""
    SELECT f FROM Fattura f WHERE MONTH(f.data) = :anno
    """)
    List<Fattura> findByMese(@Param("anno") int anno);

    @Query("""
    SELECT f FROM Fattura f WHERE f.importo > :importoMinimo AND f.importo < :importoMassimo
    """)
    List<Fattura> findByRangeImporti(@Param("importoMinimo") int importoMinimo, @Param("importoMassimo") int importoMassimo);


}
