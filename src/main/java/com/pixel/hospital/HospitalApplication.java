package com.pixel.hospital;

import com.pixel.hospital.entities.Patient;
import com.pixel.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Override
	public void  run(String...args) throws Exception {

		patientRepository.save(new Patient(null, " Pascal", new Date(), false, 123));
		patientRepository.save(new Patient(null, " Yannick", new Date(), true, 100));
		patientRepository.save(new Patient(null, " Prince", new Date(), false, 82));
		patientRepository.save(new Patient(null, " Floribert", new Date(), false, 36));
		patientRepository.save(new Patient(null, " Lionnel", new Date(), true, 100));
		patientRepository.save(new Patient(null, " Pascaline", new Date(), false, 47));
		patientRepository.save(new Patient(null, " Princesse", new Date(), false, 112));
		patientRepository.save(new Patient(null, " Alfred", new Date(), false, 188));
		patientRepository.save(new Patient(null, " yan", new Date(), true, 108));
		patientRepository.save(new Patient(null, " Paulin", new Date(), false, 382));
		patientRepository.save(new Patient(null, " Flory", new Date(), true, 119));
		patientRepository.save(new Patient(null, " prospere", new Date(), true, 100));
		patientRepository.save(new Patient(null, " Christian", new Date(), false, 47));
		patientRepository.save(new Patient(null, " Alain", new Date(), true, 112));
		patientRepository.save(new Patient(null, " Carlos", new Date(), false, 2308));
		patientRepository.save(new Patient(null, " Paul", new Date(), true, 3782));
		patientRepository.save(new Patient(null, " Florentin", new Date(), true, 114));
		patientRepository.save(new Patient(null, " rodrigue", new Date(), true, 107));
		patientRepository.save(new Patient(null, " Christine", new Date(), true, 477));
		patientRepository.save(new Patient(null, " Adolphe", new Date(), false, 1012));
	}

   // insertion de quelques données test au demarrage de l'application dans la Base des données dans la table Utilisateur
	@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder = passwordEncoder();

       return args -> {

		     UserDetails utilisteur1 = jdbcUserDetailsManager.loadUserByUsername("admin");
             if (utilisteur1==null)
   			jdbcUserDetailsManager.createUser(
					   User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER","ADMIN").build()
			);

		   UserDetails utilisteur2 = jdbcUserDetailsManager.loadUserByUsername("pascal");
		   if (utilisteur2==null)
		   jdbcUserDetailsManager.createUser(
				   User.withUsername("pascal").password(passwordEncoder.encode("1234")).roles("USER").build()
		   );

		   UserDetails utilisteur3 = jdbcUserDetailsManager.loadUserByUsername("koko");
		   if (utilisteur3==null)
		   jdbcUserDetailsManager.createUser(
				   User.withUsername("koko").password(passwordEncoder.encode("1234")).roles("USER").build()
		   );


	   };
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
