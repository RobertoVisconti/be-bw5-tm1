package robertovisconti.be_bw5_tm1.services;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Cliente;
import robertovisconti.be_bw5_tm1.entities.Fattura;
import robertovisconti.be_bw5_tm1.entities.StatoFattura;
import robertovisconti.be_bw5_tm1.exceptions.NotFoundException;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.RichiestaNuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.RispostaNuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.repositories.ClienteRepository;
import robertovisconti.be_bw5_tm1.repositories.FatturaRepository;
import robertovisconti.be_bw5_tm1.specifications.FatturaSpecification;

import java.util.UUID;

@Service
public class FatturaService {

    // dependency injection

    private final ClienteRepository clienteRepository;
    private final FatturaRepository fatturaRepository;
    private final StatoFatturaService statoFatturaService;

    public FatturaService(FatturaRepository fatturaRepository, StatoFatturaService statoFatturaService, ClienteRepository clienteRepository) {
        this.fatturaRepository = fatturaRepository;
        this.statoFatturaService = statoFatturaService;
        this.clienteRepository = clienteRepository;
    }

    // ******************************  methods  ***************************************************

    @Transactional
    public RispostaNuovaFatturaDTO save(RichiestaNuovaFatturaDTO body) {

        Fattura nuovaFattura = new Fattura();
        nuovaFattura.setData(body.data());
        nuovaFattura.setImporto(body.importo());
        nuovaFattura.setNumero(body.numero());
        Cliente found = this.clienteRepository.findById(body.clienteId()).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        nuovaFattura.setCliente(found);

        StatoFattura statoDefault = statoFatturaService.findByTitolo("DA PAGARE");
        nuovaFattura.setStatoFattura(statoDefault);

        Fattura fatturaSalvata = this.fatturaRepository.save(nuovaFattura);
<<<<<<< Updated upstream

=======
        Double nuovoFatturato = this.fatturaRepository.sumImportoByClienteId(found.getId());
        found.setFatturatoAnnuale(nuovoFatturato);
        this.clienteRepository.saveAndFlush(found);
>>>>>>> Stashed changes
        return new RispostaNuovaFatturaDTO(fatturaSalvata.getId());
    }

    public Page<Fattura> search(UUID idCliente, Integer anno, Integer mese, Double importoMin, Double importoMax, String statoFattura, int page, int size) {
        Specification<Fattura> spec = Specification
                .where(FatturaSpecification.hasIdCliente(idCliente))
                .and(FatturaSpecification.hasAnno(anno))
                .and(FatturaSpecification.hasMese(mese))
                .and(FatturaSpecification.importoBetween(importoMin, importoMax))
                .and(FatturaSpecification.hasStatoFattura(statoFattura));

        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());

        return this.fatturaRepository.findAll(spec, pageable);
    }




    /*


    /// SAVE
    public Fattura save(RichiestaNuovaFatturaDTO payload) {
        Fattura nuovaFattura = new Fattura();
        nuovaFattura.setData(payload.data());
        nuovaFattura.setNumero(payload.numero());
        nuovaFattura.setCliente(payload.cliente());
        nuovaFattura.setImporto(payload.importo());


        StatoFattura statoBozza = this.statoFatturaService.findByTitolo("BOZZA");
        nuovaFattura.setStatoFattura(statoBozza);

        return this.fatturaRepository.save(nuovaFattura);
    }


    /// FIND BY ID
    public Fattura findById(UUID id) {
        return this.fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Fattura non trovata."));
    }


    /// FIND BY ID CLIENTE
    public Page<Fattura> findByIdCliente(UUID uuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByIdCliente(uuid, pageable);
    }



    /// FIND BY DATA
    public Page<Fattura> findByData(LocalDate data, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByData(data, pageable);
    }


    /// FIND BY ANNO
    public Page<Fattura> findByAnno(int anno, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByAnno(anno, pageable);
    }


    /// FIND BY MESE
    public Page<Fattura> findByMese(int mese, int page, int size) {
        if (mese < 1 || mese > 12) { throw new BadRequestException("Month not valid."); };
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByMese(mese, pageable);
    }


    /// FIND BY ID STATO FATTURA
    public Page<Fattura> findByStatoFattura(StatoFattura statoFattura, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByStatoFattura(statoFattura, pageable);
    }


    /// FIND BY RANGE IMPORTI
    public Page<Fattura> findByRangeImporti(int importoMinimo, int importoMassimo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByRangeImporti(importoMinimo, importoMassimo, pageable);
    }


    /// FIND ALL
    public Page<Fattura> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findAll(pageable);
    }


    /// UPDATE
    public Fattura updateFattura(UUID id, RichiestaUpdateFatturaDTO payload) {
        Fattura fatturaTrovata = findById(id);

        fatturaTrovata.setStatoFattura(payload.statoFattura());
        return this.fatturaRepository.save(fatturaTrovata);
    }

    */


}
