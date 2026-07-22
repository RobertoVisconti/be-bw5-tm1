package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.entities.Utente;
import robertovisconti.be_bw5_tm1.payloadsDTO.ResponseDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.UtenteDTO;
import robertovisconti.be_bw5_tm1.services.UtenteService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/utenti")
@AllArgsConstructor
public class UtenteController {

    private UtenteService utenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO save(@RequestBody @Validated UtenteDTO body) {
        Utente newUtente = this.utenteService.save(body);
        return new ResponseDTO("Utente creato correttamente", newUtente.getId(), LocalDateTime.now());

    }
}
