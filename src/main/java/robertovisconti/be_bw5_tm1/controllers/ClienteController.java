package robertovisconti.be_bw5_tm1.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import robertovisconti.be_bw5_tm1.entities.Cliente;
import robertovisconti.be_bw5_tm1.payloadsDTO.ClienteDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.ClienteResponseDTO;
import robertovisconti.be_bw5_tm1.services.ClienteService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // CRUD
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO createCliente(@RequestBody @Validated ClienteDTO body) {
        Cliente saved = clienteService.save(body);
        return new ClienteResponseDTO(saved.getId(), "Il cliente è stato aggiunto con successo", LocalDateTime.now());
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable UUID id) {
        return clienteService.findById(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO updateCliente(@PathVariable UUID id, @RequestBody @Validated ClienteDTO body) {
        Cliente update = clienteService.update(id, body);
        return new ClienteResponseDTO(update.getId(), "I campi sono stati aggiornati correttamente", LocalDateTime.now());
    }

    @PatchMapping("/{id}/logo")
    public String uploadLogo(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        return clienteService.uploadLogo(id, file);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable UUID id) {
        clienteService.delete(id);
    }

    // LIST
    @GetMapping
    public Page<Cliente> getAllClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ragioneSociale") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return clienteService.findAll(page, size, sortBy, sortDir);
    }

    // FILTRI
    @GetMapping("/search/nome")
    public Page<Cliente> filterByNome(@RequestParam String nome, Pageable pageable) {
        return clienteService.filterByNome(nome, pageable);
    }

    @GetMapping("/search/fatturato")
    public Page<Cliente> filterByFatturato(
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max,
            Pageable pageable
    ) {
        return clienteService.filterByFatturato(min, max, pageable);
    }

    @GetMapping("/search/data-inserimento")
    public Page<Cliente> filterByDataInserimento(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inizio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fine,
            Pageable pageable
    ) {
        return clienteService.filterByDataInserimento(inizio, fine, pageable);
    }

    @GetMapping("/search/data-ultimo-contatto")
    public Page<Cliente> filterByDataUltimoContatto(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inizio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fine,
            Pageable pageable
    ) {
        return clienteService.filterByDataUltimoContatto(inizio, fine, pageable);
    }

}
