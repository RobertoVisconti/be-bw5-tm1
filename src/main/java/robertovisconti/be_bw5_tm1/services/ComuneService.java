package robertovisconti.be_bw5_tm1.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import robertovisconti.be_bw5_tm1.entities.Comune;
import robertovisconti.be_bw5_tm1.entities.Provincia;
import robertovisconti.be_bw5_tm1.repositories.ComuneRepository;
import robertovisconti.be_bw5_tm1.repositories.ProvinciaRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class ComuneService {

    private static final Set<String> PAROLE_DA_IGNORARE =
            Set.of("e", "di", "d", "della", "nell", "del", "dei", "delle", "la", "le");

    private ComuneRepository comuneRepository;
    private ProvinciaRepository provinciaRepository;

    public void importaComuniEProvince() throws IOException {
        Map<String, Provincia> provinceByNomeCanonico = importaProvince();
        importaComuni(provinceByNomeCanonico);
    }

    private Map<String, Provincia> importaProvince() throws IOException {
        Map<String, Provincia> provinceByNomeCanonico = new HashMap<>();
        List<Provincia> daSalvare = new ArrayList<>();

        try (BufferedReader reader = apriRisorsaCsv("province-italiane.csv")) {
            reader.readLine();
            String riga;
            while ((riga = reader.readLine()) != null) {
                if (riga.isBlank()) continue;
                String[] campi = riga.split(";");
                if (campi.length < 2) continue;

                String sigla = pulisci(campi[0]);
                String nome = pulisci(campi[1]);

                Provincia esistente = provinciaRepository.findBySiglaIgnoreCase(sigla).orElse(null);
                if (esistente != null) {
                    provinceByNomeCanonico.put(normalizza(nome), esistente);
                    continue;
                }

                Provincia provincia = new Provincia(nome, sigla);
                daSalvare.add(provincia);
                provinceByNomeCanonico.put(normalizza(nome), provincia);
            }
        }

        provinciaRepository.saveAll(daSalvare);
        return provinceByNomeCanonico;
    }

    private void importaComuni(Map<String, Provincia> provinceByNomeCanonico) throws IOException {
        List<Comune> daSalvare = new ArrayList<>();

        try (BufferedReader reader = apriRisorsaCsv("comuni-italiani.csv")) {
            reader.readLine();
            String riga;
            while ((riga = reader.readLine()) != null) {
                if (riga.isBlank()) continue;
                String[] campi = riga.split(";");
                if (campi.length < 4) continue;

                String nomeComune = pulisci(campi[2]);
                String nomeProvinciaRiga = pulisci(campi[3]);

                Provincia provincia = trovaProvincia(nomeProvinciaRiga, provinceByNomeCanonico);
                if (provincia == null) continue;

                daSalvare.add(new Comune(nomeComune, provincia));

                if (daSalvare.size() == 500) {
                    comuneRepository.saveAll(daSalvare);
                    daSalvare.clear();
                }
            }
        }

        if (!daSalvare.isEmpty()) {
            comuneRepository.saveAll(daSalvare);
        }
    }

    private Provincia trovaProvincia(String nomeProvinciaRiga, Map<String, Provincia> provinceByNomeCanonico) {
        String canonico = normalizza(nomeProvinciaRiga);

        Provincia match = provinceByNomeCanonico.get(canonico);
        if (match != null) return match;

        Set<String> tokenRiga = new HashSet<>(Arrays.asList(canonico.split(" ")));
        for (Map.Entry<String, Provincia> entry : provinceByNomeCanonico.entrySet()) {
            Set<String> tokenProvincia = new HashSet<>(Arrays.asList(entry.getKey().split(" ")));
            if (tokenRiga.containsAll(tokenProvincia)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private String normalizza(String testo) {
        String senzaAccenti = Normalizer.normalize(testo, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        String[] token = senzaAccenti.toLowerCase().replaceAll("[^a-z]", " ").trim().split("\\s+");

        List<String> tokenPuliti = new ArrayList<>();
        for (String t : token) {
            if (!t.isBlank() && !PAROLE_DA_IGNORARE.contains(t)) {
                tokenPuliti.add(t);
            }
        }
        Collections.sort(tokenPuliti);
        return String.join(" ", tokenPuliti);
    }

    private String pulisci(String campo) {
        return campo.replace("﻿", "").trim();
    }

    private BufferedReader apriRisorsaCsv(String nomeFile) throws IOException {
        return new BufferedReader(
                new InputStreamReader(new ClassPathResource(nomeFile).getInputStream(), StandardCharsets.UTF_8));
    }
}
