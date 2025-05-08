package com.example.amirsinvoicer.Controller;

import com.example.amirsinvoicer.Config.JwtProvider;
import com.example.amirsinvoicer.Dto.authRequest;
import com.example.amirsinvoicer.Dto.authResponse;
import com.example.amirsinvoicer.Model.User;
import com.example.amirsinvoicer.Controller.AuthController;
import com.example.amirsinvoicer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.insertUser(user);
        return ResponseEntity.ok("Gebruiker succesvol geregistreerd");
    }

    @PostMapping("/login")
    public ResponseEntity<authResponse> login(@RequestBody authRequest request) {
        // stap 1: controler gebruikersnaam en wachtwoord
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // stap 2, token aanmaken
        String token = jwtProvider.generateToken(request.getUsername());
        // stap 3, token wordt teruggegeven
        return ResponseEntity.ok(new authResponse(token));
    }
}