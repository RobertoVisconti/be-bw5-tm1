package robertovisconti.be_bw5_tm1.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import robertovisconti.be_bw5_tm1.enums.TipoCliente;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "id_cliente")
    private UUID idCliente;

    @Column(name = "ragione_sociale", nullable = false)
    private String ragioneSociale;

    @Column(name = "partita_iva", unique = true, nullable = false, updatable = false)
    private String partitaIva;

    @Column(name = "data_inserimento", nullable = false)
    private LocalDateTime dataInserimento;

    @Column(name = "data_ultimo_contatto")
    private LocalDateTime dataUltimoContatto;

    @Column(name = "fatturato_annuale")
    private double fatturatoAnnuale;

    @Column(unique = true)
    private String pec;

    @Column(unique = true)
    private String telefono;

    @Column(name = "email_contatto", unique = true)
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;

    @ManyToOne
    @JoinColumn(name = "sede_legale")
    private Indirizzo sedeLegale;

    @ManyToOne
    @Column(name = "sede_operativa")
    private Indirizzo sedeOperativa;

    // softDelete
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;


    public Cliente(String ragioneSociale, String partitaIva, LocalDateTime dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String nomeContatto, String cognomeContatto, String logoAziendale, TipoCliente tipoCliente, Indirizzo sedeLegale, Indirizzo sedeOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.logoAziendale = logoAziendale;
        this.tipoCliente = tipoCliente;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public void setDataInserimento(LocalDateTime dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFatturatoAnnuale(double fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public void setEmailContatto(String emailContatto) {
        this.emailContatto = emailContatto;
    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public void setCognomeContatto(String cognomeContatto) {
        this.cognomeContatto = cognomeContatto;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setDataUltimoContatto(LocalDateTime dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
    }

    public void setSedeLegale(Indirizzo sedeLegale) {
        this.sedeLegale = sedeLegale;
    }

    public void setSedeOperativa(Indirizzo sedeOperativa) {
        this.sedeOperativa = sedeOperativa;
    }

    @PrePersist
    public void setDataCreazione() {
        this.dataInserimento = LocalDateTime.now();
    }
}
