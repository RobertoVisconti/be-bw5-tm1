package robertovisconti.be_bw5_tm1.specifications;

import org.springframework.data.jpa.domain.Specification;
import robertovisconti.be_bw5_tm1.entities.Cliente;

public class ClienteSpecification {

    // LISTA INTERA
    public static Specification<Cliente> isNotDeleted() {
        return (root, query, cb) -> cb.equal(root.get("isDeleted"), false);
    }

    // LISTA PER RAGIONE SOCIALE
    public static Specification<Cliente> hasRagioneSociale(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("ragioneSociale")), "%" + nome.toLowerCase() + "%");
        };
    }
}
