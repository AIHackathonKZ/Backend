package ai.hackaton.backend.service;

import ai.hackaton.backend.dto.JwtAuthenticationResponse;
import ai.hackaton.backend.dto.SignInRequest;
import ai.hackaton.backend.dto.SignUpRequest;
import ai.hackaton.backend.entity.User;
import ai.hackaton.backend.helper.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        userService.create(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .status("REGISTRATION_SUCCESSFUL")
                .token(jwt)
                .build();
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        var user = userService.userDetailsService()
                .loadUserByUsername(request.email());

        return JwtAuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .status("LOGIN SUCCESSFUL")
                .build();
    }


}
