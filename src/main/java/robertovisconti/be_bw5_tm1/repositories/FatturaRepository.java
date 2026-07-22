package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import robertovisconti.be_bw5_tm1.entities.Cliente;
import robertovisconti.be_bw5_tm1.entities.Fattura;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID>, JpaSpecificationExecutor<Fattura> {


    // Page<Fattura> findAll(Specification<Fattura> spec, Pageable pageable);



    Page<Fattura> findAll(Pageable pageable);

    @Query("""
    SELECT f FROM Fattura f WHERE f.cliente.id = :uuid
    """)
    Page<Fattura> findByIdCliente(UUID uuid, Pageable pageable);

    Page<Fattura> findByStatoFattura(StatoFattura statoFattura, Pageable pageable);

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
