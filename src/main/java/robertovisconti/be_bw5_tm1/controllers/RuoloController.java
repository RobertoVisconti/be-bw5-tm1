package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.payloadsDTO.RuoloDTO;
import robertovisconti.be_bw5_tm1.services.RuoloService;

@RestController
@RequestMapping("/ruoli")
@AllArgsConstructor
public class RuoloController {

    private RuoloService ruoloService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated RuoloDTO body) {
        this.ruoloService.save(body);
    }

}
