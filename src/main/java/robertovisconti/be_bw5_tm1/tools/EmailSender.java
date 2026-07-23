package robertovisconti.be_bw5_tm1.tools;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import robertovisconti.be_bw5_tm1.entities.Cliente;
import robertovisconti.be_bw5_tm1.entities.Fattura;

@Component
public class EmailSender {

    private final String domainName;
    private final String apiKey;

    public EmailSender(
            @Value("${mailgun.domainName}") String domainName,
            @Value("${mailgun.apiKey}") String apiKey

    ) {
        this.domainName = domainName;
        this.apiKey = apiKey;
    }

    public void sendRegistrationEmail(Cliente recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "giannibussoletti@gmail.com") // Metti la tua email o mittente
                .queryString("to", recipient.getEmailContatto())
                .queryString("subject", "Benvenuto sulla piattaforma")
                .queryString("text", "Ciao " + recipient.getNomeContatto() + ", la tua registrazione è completa")
                .asJson();

        System.out.println(response.getBody()); // Log della risposta di Mailgun
    }

    public void sendFattura(Cliente recipient, Fattura fattura) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "tancredidangelo@gmail.com")
                .queryString("to", recipient.getEmailContatto())
                .queryString("subject", "Fattura " + fattura.getId())
                .queryString("text", "Ciao " + recipient.getNomeContatto() + ", la fattura " + fattura.getId() + " è disponibile alla consultazione.")
                .queryString("text", fattura.toString())
                
                .asJson();

        System.out.println(response.getBody()); // Log della risposta di Mailgun
    }
}
