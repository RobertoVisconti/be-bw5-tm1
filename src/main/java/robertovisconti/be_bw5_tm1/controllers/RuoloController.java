package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import robertovisconti.be_bw5_tm1.payloadsDTO.RuoloDTO;
import robertovisconti.be_bw5_tm1.services.RuoloService;

@RestController
@RequestMapping("/ruoli")
@AllArgsConstructor
public class RuoloController {

    private RuoloService ruoloService;

    public void save(RuoloDTO body) {
        this.ruoloService.save(body);
    }

}
