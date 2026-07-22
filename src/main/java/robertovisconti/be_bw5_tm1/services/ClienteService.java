package robertovisconti.be_bw5_tm1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import robertovisconti.be_bw5_tm1.entities.Cliente;
import robertovisconti.be_bw5_tm1.entities.Indirizzo;
import robertovisconti.be_bw5_tm1.exceptions.BadRequestException;
import robertovisconti.be_bw5_tm1.exceptions.NotFoundException;
import robertovisconti.be_bw5_tm1.payloadsDTO.ClienteDTO;
import robertovisconti.be_bw5_tm1.repositories.ClienteRepository;
import robertovisconti.be_bw5_tm1.repositories.IndirizzoRepository;
import robertovisconti.be_bw5_tm1.specifications.ClienteSpecification;
import robertovisconti.be_bw5_tm1.tools.EmailSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final IndirizzoRepository indirizzoRepository;
    private final Cloudinary fileUploader;
    private final EmailSender emailSender;

    public ClienteService(ClienteRepository clienteRepository, IndirizzoRepository indirizzoRepository, Cloudinary fileUploader, EmailSender emailSender) {
        this.clienteRepository = clienteRepository;
        this.indirizzoRepository = indirizzoRepository;
        this.fileUploader = fileUploader;
        this.emailSender = emailSender;
    }

    // CREAZIONE CLIENTE
    public Cliente save(ClienteDTO body) {
        if (clienteRepository.existsByPartitaIvaAndIsDeletedFalse(body.partitaIva())) {
            throw new BadRequestException("La partita IVA " + body.partitaIva() + " appartiene già ad un altro cliente!");
        }
        if (clienteRepository.existsByPecAndIsDeletedFalse(body.pec())) {
            throw new BadRequestException("La pec " + body.pec() + " appartiene già ad un altro cliente!");
        }
        if (clienteRepository.existsByEmailContattoAndIsDeletedFalse(body.emailContatto())) {
            throw new BadRequestException("L'email " + body.emailContatto() + " appartiene già ad un altro cliente!");
        }

        Cliente cliente = new Cliente();

        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setLogoAziendale(body.logoAziendale());
        cliente.setTipoCliente(body.tipoCliente());
        cliente.setDataUltimoContatto(LocalDateTime.now());

        if (body.idSedeLegale() != null) {
            Indirizzo sedeLegale = indirizzoRepository.findById(body.idSedeLegale()).orElseThrow(() -> new NotFoundException("Sede legale non trovata!"));
            cliente.setSedeLegale(sedeLegale);
        }
        if (body.idSedeOperativa() != null) {
            Indirizzo sedeOperativa = indirizzoRepository.findById(body.idSedeOperativa()).orElseThrow(() -> new NotFoundException("Sede Operativa non trovata!"));
            cliente.setSedeOperativa(sedeOperativa);
        }

        Cliente savedCliente = clienteRepository.save(cliente);

        this.emailSender.sendRegistrationEmail(savedCliente);

        return savedCliente;

    }

    // RICERCA PER ID
    public Cliente findById(UUID id) {
        return clienteRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NotFoundException("Cliente con ID: " + id + " non trovato!"));
    }

    // UPDATE
    public Cliente update(UUID id, ClienteDTO body) {
        Cliente cliente = findById(id);

        if (body.partitaIva() != null && !body.partitaIva().equals(cliente.getPartitaIva())) {
            if (clienteRepository.existsByPartitaIvaAndIsDeletedFalse(body.partitaIva())) {
                throw new BadRequestException("La partita IVA " + body.partitaIva() + " appartiene già ad un altro cliente!");
            }

            if (body.pec() != null && !body.pec().equals(cliente.getPec())) {
                if (clienteRepository.existsByPecAndIsDeletedFalse(body.pec())) {
                    throw new BadRequestException("La PEC " + body.pec() + " appartiene già ad un altro cliente!");
                }
            }

            if (body.emailContatto() != null && !body.emailContatto().equals(cliente.getEmailContatto())) {
                if (clienteRepository.existsByEmailContattoAndIsDeletedFalse(body.emailContatto())) {
                    throw new BadRequestException("L'email " + body.emailContatto() + " appartiene già un altro cliente!");
                }
            }
        }

        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setLogoAziendale(body.logoAziendale());
        cliente.setTipoCliente(body.tipoCliente());

        cliente.setDataUltimoContatto(LocalDateTime.now());

        if (body.idSedeLegale() != null) {
            Indirizzo sedeLegale = indirizzoRepository.findById(body.idSedeLegale()).orElseThrow(() -> new NotFoundException("Sede legale non trovata!"));
            cliente.setSedeLegale(sedeLegale);
        }
        if (body.idSedeOperativa() != null) {
            Indirizzo sedeOperativa = indirizzoRepository.findById(body.idSedeOperativa()).orElseThrow(() -> new NotFoundException("Sede Operativa non trovata!"));
            cliente.setSedeOperativa(sedeOperativa);
        }

        return clienteRepository.save(cliente);

    }


    // DELETE
    public void delete(UUID id) {
        Cliente cliente = this.findById(id);

        cliente.setDeleted(true);

        clienteRepository.save(cliente);
    }

    // FIND DINAMICO
    public Page<Cliente> searchClienti(String nome,
                                       Double minFatturato, Double maxFatturato,
                                       LocalDateTime datainserimentoInizio, LocalDateTime dataInserimentoFine,
                                       LocalDateTime dataUltimoContattoInizio, LocalDateTime dataUltimoContattoFine,
                                       Pageable pageable) {
        Specification<Cliente> spec = Specification.where(ClienteSpecification.isNotDeleted());

        // applicazione filtri
        if (nome == null || nome.isBlank()) {
            spec = spec.and(ClienteSpecification.hasRagioneSociale(nome));
        }
        if (minFatturato != null || maxFatturato != null) {
            spec = spec.and(ClienteSpecification.hasFatturatoBetween(minFatturato, maxFatturato));
        }
        if (datainserimentoInizio != null || dataInserimentoFine != null) {
            spec = spec.and(ClienteSpecification.hasDataInserimentoBetween(datainserimentoInizio.with(LocalDateTime.MIN), dataInserimentoFine.with(LocalDateTime.MAX)));
        }
        if (dataUltimoContattoInizio != null || dataUltimoContattoFine != null) {
            spec = spec.and(ClienteSpecification.hasDataUltimoContattoBetween(dataUltimoContattoInizio.with(LocalDateTime.MIN), dataUltimoContattoFine.with(LocalDateTime.MAX)));
        }
        return clienteRepository.findAll(spec, pageable);


    }

//    // FIND ALL
//    public Page<Cliente> findAll(int page, int size, String sortBy, String sortDir) {
//        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        return clienteRepository.findByIsDeletedFalse(pageable);
//    }
//
//    // FILTER NOME
//    public Page<Cliente> filterByNome(String nome, Pageable pageable) {
//        return clienteRepository.findByRagioneSocialeContainingIgnoreCaseAndIsDeletedFalse(nome, pageable);
//    }
//
//    // FILTER FATTURATO
//    public Page<Cliente> filterByFatturato(Double min, Double max, Pageable pageable) {
//        Double minVal = (min != null) ? min : 0.0;
//        Double maxVal = (max != null) ? max : Double.MAX_VALUE;
//        return clienteRepository.findByFatturatoAnnualeBetweenAndIsDeletedFalse(minVal, maxVal, pageable);
//    }
//
//    // FILTER DATA INSERIMENTO
//    public Page<Cliente> filterByDataInserimento(LocalDateTime inizio, LocalDateTime fine, Pageable pageable) {
//        LocalDateTime start = inizio.with(LocalTime.MIN);
//        LocalDateTime end = fine.with(LocalTime.MAX);
//
//        return clienteRepository.findByDataInserimentoBetweenAndIsDeletedFalse(start, end, pageable);
//    }
//
//    // FILTER DATA ULTIMO CONTATTO
//    public Page<Cliente> filterByDataUltimoContatto(LocalDateTime inizio, LocalDateTime fine, Pageable pageable) {
//        LocalDateTime start = inizio.with(LocalTime.MIN);
//        LocalDateTime end = fine.with(LocalTime.MAX);
//
//        return clienteRepository.findByDataUltimoContattoBetweenAndIsDeletedFalse(start, end, pageable);
//    }

    public String uploadLogo(UUID idCliente, MultipartFile file) {
        Cliente cliente = findById(idCliente);

        if (file != null && !file.isEmpty()) {
            try {
                String url = (String) fileUploader.uploader()
                        .upload(file.getBytes(), ObjectUtils.emptyMap())
                        .get("secure_url");

                cliente.setLogoAziendale(url);
                clienteRepository.save(cliente);

                return url;

            } catch (IOException ex) {
                throw new RuntimeException("Errore durante l'upload del file logo", ex);
            }
        }

        throw new BadRequestException("Il file inviato è vuoto!");
    }

}
