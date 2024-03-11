//Projekto kūrimo metu, Spring lange pasirenkam Security -> Spring Security ir ten yra linkas
//https://spring.io/guides/gs/securing-web
package com.example.javamvcsample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        //.requestMatchers("/public").permitAll() //leidžiami puslapiai be prisijungimo
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        //.loginPage("/login") //jei norim naudoti default login puslapį šitą uždiseiblinam
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
