package com.dreamsdestroyer.coursework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "access_token",columnDefinition = "text", nullable = false, unique = true)
    private String accessToken;

    @Column(name = "refresh_token",columnDefinition = "text", nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @Column(name = "created_time_stamp", nullable = false)
    private Timestamp createdTimeStamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

}