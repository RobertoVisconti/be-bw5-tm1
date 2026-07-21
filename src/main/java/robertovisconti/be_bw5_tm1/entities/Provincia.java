package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "provincia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

    @Id
    @GeneratedValue
    @Column(name = "id_provincia")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 2)
    private String sigla;

    @Column(nullable = false)
    private String regione;
}
