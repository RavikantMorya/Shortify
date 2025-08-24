package com.maurya.url_shortner_sb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Id
    private Long id;
    private String email;
    private String password;
    private String uname;
    private String role="ROLE_USER";

}
