package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.LocalUser;
import com.dreamsdestroyer.coursework.model.VerificationToken;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


    Optional<VerificationToken> findByAccessToken(String token);

    void deleteByUser(LocalUser user);
}
