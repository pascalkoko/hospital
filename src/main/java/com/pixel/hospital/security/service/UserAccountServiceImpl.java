package com.pixel.hospital.security.service;

import com.pixel.hospital.security.entities.AppUser;
import com.pixel.hospital.security.entities.UserRole;
import com.pixel.hospital.security.repo.AppRoleRepository;
import com.pixel.hospital.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService{

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser!=null) throw  new RuntimeException("this user already exists");
        if(!password.equals(confirmPassword))throw  new RuntimeException("password not match");
            appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        AppUser savedUser = appUserRepository.save(appUser);
        return savedUser;
    }

    @Override
    public UserRole addNewRole(String role) {

        UserRole userRole = appRoleRepository.findById(role).orElse(null);
        if(userRole!=null)throw  new RuntimeException("this role already exists");
        userRole = UserRole.builder()
                .role(role)
                .build();

        return appRoleRepository.save(userRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw  new RuntimeException("this user does not exist in the DataBase");
        UserRole userRole = appRoleRepository.findById(role).get();
        if(userRole == null)throw  new RuntimeException("this role does not exist in the DataBase");
        appUser.getRoles().add(userRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw  new RuntimeException("this user does not exist in the DataBase");
        UserRole userRole = appRoleRepository.findById(role).get();
        if(userRole == null)throw  new RuntimeException("this role does not exist in the DataBase");
        appUser.getRoles().remove(userRole);
    }

    @Override
    public AppUser loadUserByUsernama(String username) {
        return appUserRepository.findByUsername(username);
    }


}
