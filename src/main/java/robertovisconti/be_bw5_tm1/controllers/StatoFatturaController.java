package robertovisconti.be_bw5_tm1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;
import robertovisconti.be_bw5_tm1.payloadsDTO.ResponseDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.StatoFatturaDTO;
import robertovisconti.be_bw5_tm1.services.StatoFatturaService;

import java.time.LocalDateTime;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN','SUPERADMIN')")
    public ResponseDTO save(@RequestBody @Validated StatoFatturaDTO body) {
        StatoFattura newStato = this.statoFatturaService.save(body);
        return new ResponseDTO("Stato Fattura salvato correttamente", newStato.getId(), LocalDateTime.now());
    }

    /// GET [...](http://localhost:PORT/stato-fattura)
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<StatoFattura> findAll() {
        return this.statoFatturaService.findAll();
    }


    /// GET [...](http://localhost:PORT/stato-fattura/titolo_fattura)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{titolo_fattura}")
    public StatoFattura findByTitolo(@RequestParam String titolo) {
        return this.statoFatturaService.findByTitolo(titolo.toUpperCase());
    }

}
