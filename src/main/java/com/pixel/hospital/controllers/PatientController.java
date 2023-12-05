package com.pixel.hospital.controllers;

import com.pixel.hospital.entities.Patient;
import com.pixel.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    // injection de dependance par le constructeur avec parametres(utilisation des annotations Lombok)
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model){

        List<Patient> patientsList = patientRepository.findAll();
        model.addAttribute("listeDesPatients", patientsList);

        return "patients";
    }

}
