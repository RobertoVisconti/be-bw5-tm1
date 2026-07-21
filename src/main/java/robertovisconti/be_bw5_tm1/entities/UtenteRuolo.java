package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "utente_ruoli")
@Getter
@Setter
public class UtenteRuolo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_ruolo")
    private Ruolo ruolo;

    public UtenteRuolo(Utente utente, Ruolo ruolo) {
        this.ruolo = ruolo;
        this.utente = utente;
    }
    
}
