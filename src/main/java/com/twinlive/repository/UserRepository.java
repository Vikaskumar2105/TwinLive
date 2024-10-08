package com.twinlive.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twinlive.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}