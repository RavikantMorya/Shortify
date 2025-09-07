package com.maurya.url_shortner_sb.service;

import com.maurya.url_shortner_sb.dtos.LoginRequest;
import com.maurya.url_shortner_sb.models.User;
import com.maurya.url_shortner_sb.repository.UserRepository;
import com.maurya.url_shortner_sb.security.jwt.JwtAuthenticationResponse;
import com.maurya.url_shortner_sb.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){
        //authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword())
        );
        //set securitycontext for the user
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //load user details
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }
}
