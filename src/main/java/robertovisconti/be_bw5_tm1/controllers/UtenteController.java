package robertovisconti.be_bw5_tm1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import robertovisconti.be_bw5_tm1.entities.Utente;
import robertovisconti.be_bw5_tm1.payloadsDTO.ResponseDTO;
import robertovisconti.be_bw5_tm1.payloadsDTO.UtenteDTO;
import robertovisconti.be_bw5_tm1.services.UtenteService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
@AllArgsConstructor
public class UtenteController {

    private UtenteService utenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public ResponseDTO save(@RequestBody @Validated UtenteDTO body) {
        Utente newUtente = this.utenteService.save(body);
        return new ResponseDTO("Utente creato correttamente", newUtente.getId(), LocalDateTime.now());
    }


    @PatchMapping("/{id}/avatar")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String uploadLogo(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        return utenteService.uploadAvatar(id, file);
    }
}
