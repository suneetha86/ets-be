package com.ets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.model.AdminLoginUser;

public interface AdminLoginUserRepository extends JpaRepository<AdminLoginUser, Long> {

    Optional<AdminLoginUser> findByUsernameIgnoreCase(String username);

    Optional<AdminLoginUser> findByEmailIgnoreCase(String email);
}
