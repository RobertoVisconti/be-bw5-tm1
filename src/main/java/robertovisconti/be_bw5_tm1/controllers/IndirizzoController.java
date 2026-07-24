package robertovisconti.be_bw5_tm1.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.entities.Indirizzo;
import robertovisconti.be_bw5_tm1.payloadsDTO.IndirizzoDTO;
import robertovisconti.be_bw5_tm1.services.IndirizzoService;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    private final IndirizzoService indirizzoService;

    public IndirizzoController(IndirizzoService indirizzoService) {
        this.indirizzoService = indirizzoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public Indirizzo createIndirizzo(@RequestBody @Validated IndirizzoDTO body) {
        return this.indirizzoService.save(body);
    }
}
