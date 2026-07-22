package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "stato_fattura")
public class StatoFattura {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private int titolo;

    @Column
    private String descrizione;


}
