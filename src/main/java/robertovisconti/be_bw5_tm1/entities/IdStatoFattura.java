package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class IdStatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(unique = true)
    private int titolo;

    @Column
    private String descrizione;


}
