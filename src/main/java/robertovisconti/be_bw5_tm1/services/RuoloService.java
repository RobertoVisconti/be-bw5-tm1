package robertovisconti.be_bw5_tm1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Ruolo;
import robertovisconti.be_bw5_tm1.exceptions.BadRequestException;
import robertovisconti.be_bw5_tm1.exceptions.RuoloNotFoundException;
import robertovisconti.be_bw5_tm1.payloadsDTO.RuoloDTO;
import robertovisconti.be_bw5_tm1.repositories.RuoloRepository;

@Service
@AllArgsConstructor

public class RuoloService {

    private RuoloRepository ruoloRepository;

    public Ruolo save(RuoloDTO body) {
        Ruolo newRuolo = new Ruolo(body.ruolo().toUpperCase());
        if (this.ruoloRepository.existsByRuolo(body.ruolo().toUpperCase()))
            throw new BadRequestException("Il ruolo è già presente nel database");
        this.ruoloRepository.save(newRuolo);
        return newRuolo;
    }


    public Ruolo findByRuolo(String ruolo) {

        return this.ruoloRepository.findByRuolo(ruolo).orElseThrow(() -> new RuoloNotFoundException("Il ruolo non è presente nel Database"));
    }

}
