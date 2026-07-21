package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "province")
@Getter
@Setter
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "id_provincia")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 2)
    private String sigla;

    public Provincia(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }
}
