package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "fatture")
public class Fattura {

    // attributes

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    public UUID id;

    @Column
    private LocalDate data;

    @Column
    private double importo;

    @Column(unique = true)
    private int numero;

    @Column(name = "id_utente")
    private UUID idUtente;

    @Column(name = "id_stato_fattura")
    private UUID idStatoFattura;


    // constructor

    public Fattura(LocalDate data, double importo, int numero, UUID idUtente, UUID idStatoFattura) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.idUtente = idUtente;
        this.idStatoFattura = idStatoFattura;
    }


    // to string

    @Override
    public String toString() {
        return "Fattura{" +
                "id=" + id +
                ", data=" + data +
                ", importo=" + importo +
                ", numero=" + numero +
                ", idUtente=" + idUtente +
                ", idStatoFattura=" + idStatoFattura +
                '}';
    }
}
