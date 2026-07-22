package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import robertovisconti.be_bw5_tm1.enums.TipoIndirizzo;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "indirizzi")
@Getter
@Setter
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private String civico;

    @Column(nullable = false)
    private String localita;

    @Column(nullable = false)
    private String cap;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comune", nullable = false)
    private Comune comune;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoIndirizzo tipo;

    public Indirizzo(String via, String civico, String localita, String cap, Comune comune, TipoIndirizzo tipo) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
        this.tipo = tipo;
    }
}
