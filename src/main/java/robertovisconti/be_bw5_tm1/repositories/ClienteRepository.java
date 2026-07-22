package robertovisconti.be_bw5_tm1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import robertovisconti.be_bw5_tm1.entities.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {

    boolean existsByPartitaIvaAndIsDeletedFalse(String partitaIva);

    boolean existsByPecAndIsDeletedFalse(String pec);

    boolean existsByEmailContattoAndIsDeletedFalse(String emailContatto);


    Optional<Cliente> findByIdAndIsDeletedFalse(UUID id);

//    Page<Cliente> findByIsDeletedFalse(Pageable pageable);

//    Page<Cliente> findByRagioneSocialeContainingIgnoreCaseAndIsDeletedFalse(String ragioneSociale, Pageable pageable);
//
//    Page<Cliente> findByFatturatoAnnualeBetweenAndIsDeletedFalse(Double min, Double max, Pageable pageable);
//
//    Page<Cliente> findByDataInserimentoBetweenAndIsDeletedFalse(LocalDateTime inizio, LocalDateTime fine, Pageable pageable);
//
//    Page<Cliente> findByDataUltimoContattoBetweenAndIsDeletedFalse(LocalDateTime inizio, LocalDateTime fine, Pageable pageable);
}
