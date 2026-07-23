package robertovisconti.be_bw5_tm1.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder bcrypt;

    public String login(LoginDTO body) {
        Utente found = this.utenteService.findByEmailIgnoreCase(body.email().toLowerCase().trim());
        if (!this.bcrypt.matches(body.password(), found.getPassword()))
            throw new UnauthorizedException("Password errata");

        return this.tokenToolkit.tokenGenerator(found);
    }

}
