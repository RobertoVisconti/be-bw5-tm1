package robertovisconti.be_bw5_tm1.payloadsDTO;

import lombok.Getter;

@Getter
public class IndirizzoDTO {

    private final String via;
    private final String civico;
    private final String localita;
    private final String cap;
    private final String comune;
    private final String provincia;
    private final String regione;
    private final String tipo;

    public IndirizzoDTO(String via, String civico, String localita, String cap, String comune, String provincia, String regione, String tipo) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
        this.regione = regione;
        this.tipo = tipo;
    }
}
