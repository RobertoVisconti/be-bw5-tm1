package robertovisconti.be_bw5_tm1.controllers;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_bw5_tm1.entities.Fattura;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.RichiestaNuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.RispostaNuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.services.FatturaService;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    // dependency injection

    private final FatturaService fatturaService;

    public FatturaController(FatturaService fatturaService) {
        this.fatturaService = fatturaService;
    }


    // ********************  endpoints **********************************************************************


    /// SAVE - POST [...](http://localhost:PORT/fatture/crea) + {payload} ---> 201 CREATED
    @PostMapping("/crea")
    @ResponseStatus(HttpStatus.CREATED)
    public RispostaNuovaFatturaDTO save(@RequestBody RichiestaNuovaFatturaDTO payload) {
        Fattura nuovaFattura = this.fatturaService.save(payload);
        return new RispostaNuovaFatturaDTO(nuovaFattura.getId());
    }



    /// FIND ALL - GET [...](http://localhost:PORT/fatture) ---> 200 OK
    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findAll(page, size);
    }



    /// FIND BY ID - GET [...](http://localhost:PORT/fatture/{id}) ---> 200 OK
    @GetMapping("/{id}")
    public Fattura findById(@RequestParam("id") UUID uuid) {
        return this.fatturaService.findById(uuid);
    }



    /// FIND BY DATA
    @GetMapping("/{data}")
    public Page<Fattura> findByData(@RequestParam LocalDate data, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findByData(data, page, size);
    }


    /// FIND BY ANNO
    @GetMapping("/{anno}")
    public Page<Fattura> findByAnno(@RequestParam int anno, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findByAnno(anno, page, size);
    }



    /// FIND BY MESE
    @GetMapping("/{mese}")
    public Page<Fattura> findByMese(@RequestParam int mese, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findByMese(mese, page, size);
    }



    /// FIND BY ID CLIENTE - GET [...](http://localhost:PORT/fatture/{id_cliente}) ---> 200 OK
    @GetMapping("/clienti/{id_cliente}")
    public Page<Fattura> findByIdCliente(@RequestParam("id_cliente") UUID uuid, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findByIdCliente(uuid, page, size);
    }



    /// FIND BY STATO FATTURA
    @GetMapping("/{stato_fattura}")
    public Page<Fattura> findByStatoFattura(@RequestParam("stato_fattura") StatoFattura statoFattura, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.fatturaService.findByStatoFattura(statoFattura, page, size);
    }



    /// UPDATE BY ID
    @PutMapping()






}
