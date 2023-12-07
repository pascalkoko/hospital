package com.pixel.hospital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/accesNonAutorise")
    public String accesNonAutorise(){
        return "nonAutorise";
    }

    @GetMapping("/login")
    public String seConnecter(){
        return "login";
    }
}
