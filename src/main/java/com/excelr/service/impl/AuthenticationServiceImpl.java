package com.excelr.service.impl;

import com.excelr.dao.request.SignUpRequest;
import com.excelr.dao.request.SigninRequest;
import com.excelr.dao.response.JwtAuthenticationResponse;
import com.excelr.entity.Role;
import com.excelr.entity.User;
import com.excelr.repository.RoleRepository;
import com.excelr.repository.UserRepository;
import com.excelr.service.AuthenticationService;
import com.excelr.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private  final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;


    private final RoleRepository roleRepository;


    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {

        var user = User.builder().username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>())
                .build();
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER");
        Role adminRole = roleRepository.findByName("ADMIN");
        roles.add(userRole);
        roles.add(adminRole);

        user.setRoles(roles);

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);


        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("Incorrect Username or password!!")
        );

        var jwt = jwtService.generateToken(user);


        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
