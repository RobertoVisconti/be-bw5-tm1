package robertovisconti.be_bw5_tm1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {


        // disabilito per non far gestire l'autenticazione tramite i classici form generati dal server, come la pagina di login che mi crea di default spring
        httpSecurity.formLogin(formLogin -> formLogin.disable());


        // Dato che utilizzo JWT che è stateless devo disabilitare le sessioni
        httpSecurity.sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Disabilito la protezone perchè non utilizzo i cookie
        httpSecurity.csrf(csrf -> csrf.disable());

        // Vado ad eliminare il controllo di autenticazione su tutti gli end-point che fà Spring Security di default (401)
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());

        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
