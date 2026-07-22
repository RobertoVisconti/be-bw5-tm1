package robertovisconti.be_bw5_tm1.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import robertovisconti.be_bw5_tm1.entities.Fattura;

import java.util.UUID;

public class FatturaSpecification {

    public static Specification<Fattura> hasIdCliente(UUID uuid) {
        return (root, query, cb) ->
            uuid == null ? null : cb.equal(root.get("cliente").get("id"), uuid);
    }


    public static Specification<Fattura> hasAnno(Integer anno) {
        return ((root, query, cb) ->
                anno == null ? null :  cb.equal(cb.function("YEAR", Integer.class, root.get("data")), anno));
    }


    public static Specification<Fattura> hasMese(Integer mese) {
        return ((root, query, cb) ->
                mese == null ? null : cb.equal(cb.function("MONTH", Integer.class, root.get("data")), mese));
    }


    public static Specification<Fattura> importoBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThan(root.get("importo"), max);
            if (max == null) return cb.greaterThan(root.get("importo"), min);
            return cb.between(root.get("importo"), min, max);
        };
    }


    public static Specification<Fattura> hasStatoFattura(String titoloStato) {
        return (root, query, cb) ->
                titoloStato == null ? null : cb.equal(cb.lower(root.get("statoFattura").get("titolo")), titoloStato.toLowerCase());
    }


}
