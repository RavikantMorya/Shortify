package com.maurya.url_shortner_sb.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Id
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String role="ROLE_USER";

}
