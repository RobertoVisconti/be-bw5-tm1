package robertovisconti.be_bw5_tm1.services;


import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Comune;
import robertovisconti.be_bw5_tm1.entities.Indirizzo;
import robertovisconti.be_bw5_tm1.enums.TipoIndirizzo;
import robertovisconti.be_bw5_tm1.exceptions.NotFoundException;
import robertovisconti.be_bw5_tm1.payloadsDTO.IndirizzoDTO;
import robertovisconti.be_bw5_tm1.repositories.ComuneRepository;
import robertovisconti.be_bw5_tm1.repositories.IndirizzoRepository;

@Service
public class IndirizzoService {

    private final IndirizzoRepository indirizzoRepository;
    private final ComuneRepository comuneRepository;

    public IndirizzoService(IndirizzoRepository indirizzoRepository, ComuneRepository comuneRepository) {
        this.indirizzoRepository = indirizzoRepository;
        this.comuneRepository = comuneRepository;
    }

    public Indirizzo save(IndirizzoDTO body) {

        Comune comune = comuneRepository.findById(body.comuneId())
                .orElseThrow(() -> new NotFoundException("Comune non trovato con ID: " + body.comuneId()));

        Indirizzo nuovoIndirizzo = new Indirizzo();
        nuovoIndirizzo.setVia(body.via());
        nuovoIndirizzo.setCivico(body.civico());
        nuovoIndirizzo.setLocalita(body.localita());
        nuovoIndirizzo.setCap(body.cap());
        switch (body.tipoIndirizzo().toUpperCase()) {
            case "SEDE LEGALE" -> nuovoIndirizzo.setTipo(TipoIndirizzo.SEDE_LEGALE);
            case "SEDE OPERATIVA" -> nuovoIndirizzo.setTipo(TipoIndirizzo.SEDE_OPERATIVA);
        }

        nuovoIndirizzo.setComune(comune);

        return indirizzoRepository.save(nuovoIndirizzo);
    }
}
