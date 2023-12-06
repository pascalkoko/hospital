package com.pixel.hospital.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     private PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
     @Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
            return  new InMemoryUserDetailsManager(
                    User.withUsername("pascal").password(passwordEncoder.encode("1234")).roles("USER").build(),
                    User.withUsername("koko").password(passwordEncoder.encode("1234")).roles("USER").build(),
                    User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER","ADMIN").build()
            );
        }

    //on cree une methode qui permet de retourner securityFilterChain (l'annotation Bean permet que la methode puisse s'executer au demarrage de l'application)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
}
