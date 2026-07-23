package robertovisconti.be_bw5_tm1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<StatoFattura> findAll() {
        return this.statoFatturaService.findAll();
    }


    /// GET [...](http://localhost:PORT/stato-fattura/titolo_fattura)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{titolo}")
    public  StatoFattura findByTitolo(@PathVariable String titolo) {
        return this.statoFatturaService.findByTitolo(titolo.toUpperCase());
    }

}
