package com.pixel.hospital.security.repo;

import com.pixel.hospital.security.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<UserRole, String> {
}
