package com.maurya.url_shortner_sb.repository;

import com.maurya.url_shortner_sb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
        Optional<?> findByUsername(String uname);
}
