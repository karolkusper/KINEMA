package com.karolkusper.KINEMA.controllers.auth;

import com.karolkusper.KINEMA.config.jwtService;
import com.karolkusper.KINEMA.dao.RoleRepository;
import com.karolkusper.KINEMA.dao.UserRepository;
import com.karolkusper.KINEMA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service

public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final jwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, com.karolkusper.KINEMA.config.jwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

//        var user = User.builder()
//                .username(request.getUserName())
//                .firstname(request.getFirstName())
//                .lastname(request.getLastName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRoles())
//                .build();
//            userRepository.save(user);
        User user = new User();
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.getRoleByName("ROLE_CLIENT")));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("No user found with this email"));
//        System.out.println("Authenticate: request_email="+request.getEmail()+" request_password="+request.getPassword());
//        System.out.println("Authenticate: user_email="+user.getEmail()+" user_password="+user.getPassword());
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }
}
