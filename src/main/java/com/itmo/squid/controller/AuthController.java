package com.itmo.squid.controller;

import com.itmo.squid.auth.AuthRequest;
import com.itmo.squid.auth.AuthResponse;
import com.itmo.squid.auth.RegistrationRequest;
import com.itmo.squid.auth.RegistrationResponse;
import com.itmo.squid.auth.jwt.JwtProvider;
import com.itmo.squid.auth.service.UserAuthService;
import com.itmo.squid.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@CrossOrigin()
@RestController
public class AuthController {
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User user = new User();
        user.setPassword(registrationRequest.getPassword());
        user.setLogin(registrationRequest.getLogin());
        if (userAuthService.addNewUser(user) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(user.getLogin()));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegistrationResponse(user.getLogin()));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest request) {
        User user = userAuthService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String token = jwtProvider.generateToken(user.getLogin());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(token, user.getRole(), user.getId()));
    }


}
