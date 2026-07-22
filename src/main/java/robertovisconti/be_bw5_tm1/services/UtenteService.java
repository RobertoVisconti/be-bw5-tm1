package robertovisconti.be_bw5_tm1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import robertovisconti.be_bw5_tm1.entities.Ruolo;
import robertovisconti.be_bw5_tm1.entities.Utente;
import robertovisconti.be_bw5_tm1.exceptions.AlreadyRegisteredUserException;
import robertovisconti.be_bw5_tm1.exceptions.BadRequestException;
import robertovisconti.be_bw5_tm1.exceptions.NotFoundException;
import robertovisconti.be_bw5_tm1.payloadsDTO.UtenteDTO;
import robertovisconti.be_bw5_tm1.repositories.UtenteRepository;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UtenteService {

    private final Cloudinary fileUploader;
    private UtenteRepository utenteRepository;
    private RuoloService ruoloService;

    public Utente save(UtenteDTO body) {
        if (this.utenteRepository.existsByEmail(body.email().toLowerCase().trim())) {
            throw new AlreadyRegisteredUserException("La mail risulta già registrata");
        }
        Ruolo saved = this.ruoloService.findByRuolo(body.ruolo().toUpperCase());

        Utente newUtente = new Utente(body.nome(), body.cognome(), body.username(), body.email().toLowerCase().trim(), body.password(), saved);
        return this.utenteRepository.save(newUtente);

    }

    public Utente findById(UUID id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente findByEmailIgnoreCase(String email) {
        return this.utenteRepository.findByEmail(email.toLowerCase().trim()).orElseThrow(() -> new NotFoundException("Utente non trovato"));

    }


    public String uploadAvatar(UUID idUtente, MultipartFile file) {
        Utente utente = this.utenteRepository.findById(idUtente).orElseThrow(() -> new NotFoundException("Utente non trovato"));

        if (file != null && !file.isEmpty()) {
            try {
                String url = (String) fileUploader.uploader()
                        .upload(file.getBytes(), ObjectUtils.emptyMap())
                        .get("secure_url");

                utente.setAvatar(url);
                utenteRepository.save(utente);

                return url;

            } catch (IOException ex) {
                throw new RuntimeException("Errore durante l'upload del file logo", ex);
            }
        }

        throw new BadRequestException("Il file inviato è vuoto!");
    }
}
