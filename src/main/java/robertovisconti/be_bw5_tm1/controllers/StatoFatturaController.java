package robertovisconti.be_bw5_tm1.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import robertovisconti.be_bw5_tm1.entities.Fattura;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;
import robertovisconti.be_bw5_tm1.services.StatoFatturaService;

import java.util.List;

@RestController
@RequestMapping("/stato-fattura")
public class StatoFatturaController {

    // dependency injection

    private final StatoFatturaService statoFatturaService;

    public StatoFatturaController(StatoFatturaService statoFatturaService) {
        this.statoFatturaService = statoFatturaService;
    }



    // *******************  methods  *********************************************************

    /// GET [...](http://localhost:PORT/stato-fattura)
    @GetMapping
    public List<StatoFattura> findAll() {
        return this.statoFatturaService.findAll();
    }


    /// GET [...](http://localhost:PORT/stato-fattura/titolo_fattura)
    @GetMapping("/{titolo_fattura}")
    public  StatoFattura findByTitolo(@RequestParam String titolo) {
        return this.statoFatturaService.findByTitolo(titolo.toUpperCase());
    }

}
