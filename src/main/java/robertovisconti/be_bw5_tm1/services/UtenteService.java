package robertovisconti.be_bw5_tm1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Utente;
import robertovisconti.be_bw5_tm1.payloadsDTO.UtenteDTO;
import robertovisconti.be_bw5_tm1.repositories.UtenteRepository;

@Service
@AllArgsConstructor
public class UtenteService {

    private UtenteRepository utenteRepository;

    public Utente save(UtenteDTO body) {

        Utente newUtente = new Utente(body.nome(), body.cognome(), body.username(), body.email(), body.password());
        return this.utenteRepository.save(newUtente);

    }
}
