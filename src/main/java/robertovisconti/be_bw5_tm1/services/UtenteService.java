package robertovisconti.be_bw5_tm1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Ruolo;
import robertovisconti.be_bw5_tm1.entities.Utente;
import robertovisconti.be_bw5_tm1.exceptions.AlreadyRegisteredUserException;
import robertovisconti.be_bw5_tm1.payloadsDTO.UtenteDTO;
import robertovisconti.be_bw5_tm1.repositories.UtenteRepository;

@Service
@AllArgsConstructor
public class UtenteService {

    private UtenteRepository utenteRepository;
    private RuoloService ruoloService;

    public Utente save(UtenteDTO body) {
        if (this.utenteRepository.existsByEmail(body.email())) {
            throw new AlreadyRegisteredUserException("La mail risulta già registrata");
        }
        Ruolo saved = this.ruoloService.existsByRuolo(body.ruolo().toUpperCase());

        Utente newUtente = new Utente(body.nome(), body.cognome(), body.username(), body.email(), body.password(), saved);
        return this.utenteRepository.save(newUtente);

    }
}
