package robertovisconti.be_bw5_tm1.entities;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import robertovisconti.be_bw5_tm1.services.ComuneService;

@Component
@AllArgsConstructor
public class ComuneInitializer implements CommandLineRunner {

    private ComuneService comuneService;

    @Override
    public void run(String... args) throws Exception {
        comuneService.importaComuniEProvince();
    }
}
