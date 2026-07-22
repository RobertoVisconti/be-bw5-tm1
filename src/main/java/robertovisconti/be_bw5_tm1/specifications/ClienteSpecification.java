package robertovisconti.be_bw5_tm1.specifications;

import org.springframework.data.jpa.domain.Specification;
import robertovisconti.be_bw5_tm1.entities.Cliente;

import java.time.LocalDateTime;

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

    // LISTA PER FATTURA
    public static Specification<Cliente> hasFatturatoBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return cb.conjunction();
            if (min != null && max != null) return cb.between(root.get("fatturatoAnnuale"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("fatturatoAnnuale"), min);
            return cb.lessThanOrEqualTo(root.get("fatturatoAnnuale"), max);
        };
    }

    // LISTA DATA INSERIMENTO
    public static Specification<Cliente> hasDataInserimentoBetween(LocalDateTime inizio, LocalDateTime fine) {
        return (root, query, cb) -> {
            if (inizio == null || fine == null) return cb.conjunction();
            return cb.between(root.get("dataInserimento"), inizio, fine);
        };
    }

    // LISTA DATA ULTIMO CONTATTO
    public static Specification<Cliente> hasDataUltimoContattoBetween(LocalDateTime inizio, LocalDateTime fine) {
        return (root, query, cb) -> {
            if (inizio == null || fine == null) return cb.conjunction();
            return cb.between(root.get("dataUltimoContatto"), inizio, fine);
        };
    }
}
