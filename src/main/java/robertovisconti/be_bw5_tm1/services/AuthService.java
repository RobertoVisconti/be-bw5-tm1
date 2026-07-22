package robertovisconti.be_bw5_tm1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Utente;
import robertovisconti.be_bw5_tm1.exceptions.UnauthorizedException;
import robertovisconti.be_bw5_tm1.payloadsDTO.LoginDTO;
import robertovisconti.be_bw5_tm1.security.TokenToolkit;

@Service
@AllArgsConstructor
public class AuthService {

    private UtenteService utenteService;
    private TokenToolkit tokenToolkit;

    public String login(LoginDTO body) {
        Utente found = this.utenteService.findMyEmail(body.email());
        if (!found.getPassword().equals(body.password())) throw new UnauthorizedException("Password errata");

        return this.tokenToolkit.tokenGenerator(found);
    }

}
