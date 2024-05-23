package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.LocalUser;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {

    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByEmailIgnoreCase(String email);
    Optional<LocalUser> findByUsername(String username);
    Optional<LocalUser> findById(Long id);
}
