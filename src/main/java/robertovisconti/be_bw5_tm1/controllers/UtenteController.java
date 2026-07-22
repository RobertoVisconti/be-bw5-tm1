package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.payloadsDTO.UtenteDTO;
import robertovisconti.be_bw5_tm1.services.UtenteService;

@RestController
@RequestMapping("/utenti")
@AllArgsConstructor
public class UtenteController {

    private UtenteService utenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated UtenteDTO body) {
        this.utenteService.save(body);
    }
}
