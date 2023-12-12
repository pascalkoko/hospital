package com.pixel.hospital.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.pixel.hospital.security.service.UserDetailServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // je prepare la securité des methodes definies dans mon controleur
@AllArgsConstructor
public class SecurityConfig {

     private PasswordEncoder passwordEncoder;
     private UserDetailServiceImpl  userDetailServiceImpl;

    // @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return  new JdbcUserDetailsManager(dataSource);
    }
     //@Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
            return  new InMemoryUserDetailsManager(
                    User.withUsername("pascal").password(passwordEncoder.encode("1234")).roles("USER").build(),
                    User.withUsername("koko").password(passwordEncoder.encode("1234")).roles("USER").build(),
                    User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER","ADMIN").build()
            );
        }

    //on cree une methode qui permet de retourner securityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/login").permitAll();
        httpSecurity.rememberMe();
        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
        //httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        //httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();

        // on securise les accès  dans l'URL pour eviter aux utilisateurs de taper n'importe  quoi dans l'URL
        httpSecurity.exceptionHandling().accessDeniedPage("/accesNonAutorise");
        httpSecurity.userDetailsService(userDetailServiceImpl);
        return httpSecurity.build();
    }
}