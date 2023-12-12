package com.pixel.hospital.security.repo;

import com.pixel.hospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByUsername (String username);
}
