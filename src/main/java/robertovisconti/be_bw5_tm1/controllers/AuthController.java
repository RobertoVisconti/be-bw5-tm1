package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.payloadsDTO.LoginDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.LoginResponseDTO;
import robertovisconti.be_bw5_tm1.services.AuthService;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        String token = this.authService.login(body);
        return new LoginResponseDTO(token);
    }
}
