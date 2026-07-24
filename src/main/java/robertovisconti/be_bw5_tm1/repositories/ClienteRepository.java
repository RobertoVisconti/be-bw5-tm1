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
    
}
