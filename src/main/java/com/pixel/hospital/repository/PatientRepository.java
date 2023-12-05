package com.pixel.hospital.repository;

import com.pixel.hospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient>  findPatientByNomPatientContains(String motCle, Pageable pageable);

    @Query("select p from Patient p where p.nomPatient like :x")
    Page<Patient> chercherPatient(@Param("x") String motCle, Pageable pageable);
}
