package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class IdStatoFattura {

    // attributes

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(unique = true)
    private int titolo;

    @Column
    private String descrizione;


    // constructor

    public IdStatoFattura(int titolo, String descrizione) {
        this.titolo = titolo;
        this.descrizione = descrizione;
    }


    // to string

    @Override
    public String toString() {
        return "IdStatoFattura{" +
                "id=" + id +
                ", titolo=" + titolo +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
