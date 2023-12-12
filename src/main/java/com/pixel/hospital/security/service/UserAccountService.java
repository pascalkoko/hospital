package com.pixel.hospital.security.service;

import com.pixel.hospital.security.entities.AppUser;
import com.pixel.hospital.security.entities.UserRole;
public interface UserAccountService {

    AppUser addNewUser(String username, String password, String email, String confirmPassword);
    UserRole addNewRole (String role);
    void addRoleToUser (String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsernama(String username);
}
