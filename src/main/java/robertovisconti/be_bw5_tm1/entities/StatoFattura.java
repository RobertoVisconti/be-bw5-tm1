package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "stato_fattura")
public class StatoFattura {

    // sttributes

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String titolo;


    // constructor

    public StatoFattura(String titolo) {
        this.titolo = titolo;
    }


    // to string

    @Override
    public String toString() {
        return "StatoFattura{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                '}';
    }
}
