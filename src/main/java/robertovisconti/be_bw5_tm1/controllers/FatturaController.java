package robertovisconti.be_bw5_tm1.controllers;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.entities.Fattura;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.RichiestaNuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.RispostaNuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.services.FatturaService;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    // dependency injection

    private final FatturaService fatturaService;

    public FatturaController(FatturaService fatturaService) {
        this.fatturaService = fatturaService;
    }


    // ********************  endpoints ****************************************************

    /// SAVE - POST [...](http://localhost:PORT/fatture/crea) + {payload} ---> 201 CREATED
    @PostMapping("/crea")
    @ResponseStatus(HttpStatus.CREATED)
    public RispostaNuovaFatturaDTO save(@RequestBody RichiestaNuovaFatturaDTO payload) {
        Fattura nuovaFattura = this.fatturaService.save(payload);
        return new RispostaNuovaFatturaDTO(nuovaFattura.getId());
    }


    /// FIND ALL - GET [...](http://localhost:PORT/fatture) ---> 201 CREATED
    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findAll(page, size);
    }


    



}
