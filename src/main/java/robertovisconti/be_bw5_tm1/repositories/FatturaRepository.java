package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import robertovisconti.be_bw5_tm1.entities.Fattura;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    Page<Fattura> findAll(Pageable pageable);

    Page<Fattura> findByIdCliente(UUID idCliente, Pageable pageable);

    Page<Fattura> findByIdStatoFattura(UUID idStatoFattura, Pageable pageable);

    Page<Fattura> findByData(LocalDate data, Pageable pageable);

    @Query("""
    SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno
    """)
    Page<Fattura> findByAnno(@Param("anno") int anno, Pageable pageable);

    @Query("""
    SELECT f FROM Fattura f WHERE MONTH(f.data) = :mese
    """)
    Page<Fattura> findByMese(@Param("mese") int mese, Pageable pageable);

    @Query("""
    SELECT f FROM Fattura f WHERE f.importo > :importoMinimo AND f.importo < :importoMassimo
    """)
    Page<Fattura> findByRangeImporti(@Param("importoMinimo") int importoMinimo, @Param("importoMassimo") int importoMassimo, Pageable pageable);


}
