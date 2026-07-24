package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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
    private Double importo;

    @Column(unique = true)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_stato_fattura")
    private StatoFattura statoFattura;


    // constructor

    public Fattura(LocalDate data, double importo, int numero, Cliente cliente, StatoFattura statoFattura) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.cliente = cliente;
        this.statoFattura = statoFattura;
    }


    // to string

    @Override
    public String toString() {
        return "Fattura{" +
                "id=" + id +
                ", data=" + data +
                ", importo=" + importo +
                ", numero=" + numero +
                ", cliente=" + cliente +
                ", statoFattura=" + statoFattura +
                '}';
    }
}
