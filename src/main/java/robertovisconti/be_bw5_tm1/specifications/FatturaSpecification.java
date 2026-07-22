package robertovisconti.be_bw5_tm1.specifications;

import org.springframework.data.jpa.domain.Specification;
import robertovisconti.be_bw5_tm1.entities.Fattura;

import java.util.UUID;

public class FatturaSpecification {

    public Specification<Fattura> hasIdCliente(UUID uuid) {
        return (root, query, cb) ->
            uuid == null ? null : cb.equal(root.get("cliente").get("id"), uuid);
    }
}
