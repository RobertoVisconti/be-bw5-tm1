package robertovisconti.be_bw5_tm1.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Fattura;
import robertovisconti.be_bw5_tm1.exceptions.NotFoundException;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.NuovaFatturaDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.fattura.UpdateFatturaDTO;
import robertovisconti.be_bw5_tm1.repositories.FatturaRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class FatturaService {

    // dependency injection

    private final FatturaRepository fatturaRepository;

    public FatturaService(FatturaRepository fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }

    // ******************************  methods  ***************************************************

    /// SAVE
    public Fattura save(NuovaFatturaDTO payload) {
        Fattura nuovaFattura = new Fattura();
        nuovaFattura.setData(payload.data());
        nuovaFattura.setNumero(payload.numero());
        nuovaFattura.setIdUtente(payload.idUtente());
        nuovaFattura.setImporto(payload.importo());
        nuovaFattura.setIdStatoFattura(payload.idStatoFattura());

        return this.fatturaRepository.save(nuovaFattura);
    }


    /// FIND BY ID
    public Fattura findById(UUID id) {
        return this.fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Fattura non trovata."));
    }


    /// FIND BY ID CLIENTE
    public Page<Fattura> findByIdCliente(UUID id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByIdCliente(id, pageable);
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByMese(mese, pageable);
    }


    /// FIND BY ID STATO FATTURA
    public Page<Fattura> findByIdStatoFattura(UUID id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").descending());
        return this.fatturaRepository.findByIdStatoFattura(id, pageable);
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
    public Fattura updateFattura(UUID id, UpdateFatturaDTO payload) {
        Fattura fatturaTrovata = findById(id);

        fatturaTrovata.setIdStatoFattura(payload.idStatoFattura());
        return this.fatturaRepository.save(fatturaTrovata);
    }




}
