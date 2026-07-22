package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.entities.Ruolo;
import robertovisconti.be_bw5_tm1.payloadsDTO.ResponseDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.RuoloDTO;
import robertovisconti.be_bw5_tm1.services.RuoloService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ruoli")
@AllArgsConstructor
public class RuoloController {

    private RuoloService ruoloService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO save(@RequestBody @Validated RuoloDTO body) {
        Ruolo newRuolo = this.ruoloService.save(body);
        return new ResponseDTO("Ruolo creato con successo", newRuolo.getId(), LocalDateTime.now());
    }

}
