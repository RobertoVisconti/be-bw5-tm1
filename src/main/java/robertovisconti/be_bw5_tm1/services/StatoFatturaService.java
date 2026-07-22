package robertovisconti.be_bw5_tm1.services;

import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;
import robertovisconti.be_bw5_tm1.exceptions.NotFoundException;
import robertovisconti.be_bw5_tm1.repositories.StatoFatturaRepository;

import java.util.List;

@Service
public class StatoFatturaService {

    // dependency injection
    private final StatoFatturaRepository statoFatturaRepository;

    public StatoFatturaService(StatoFatturaRepository statoFatturaRepository) {
        this.statoFatturaRepository = statoFatturaRepository;
    }


    // methods

    public List<StatoFattura> findAll() {
        return this.statoFatturaRepository.findAll();
    }



    public StatoFattura findByTitolo(String titolo) {
        return this.statoFatturaRepository.findByTitolo(titolo).orElseThrow(() -> new NotFoundException("Stato fattura non trovato."));
    }

}
