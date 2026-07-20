package robertovisconti.be_bw5_tm1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "comune")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @GeneratedValue
    @Column(name = "id_comune")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;
}
