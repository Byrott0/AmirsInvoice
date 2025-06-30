package com.example.amirsinvoicer.Controller;

import com.example.amirsinvoicer.Config.JwtProvider;
import com.example.amirsinvoicer.Dto.authRequest;
import com.example.amirsinvoicer.Dto.authResponse;
import com.example.amirsinvoicer.Model.User;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        System.out.println("Registratie ontvangen voor: " + user.getUsername());

        // Versleutel wachtwoord
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Opslaan
        userService.insertUser(user);

        System.out.println("Gebruiker opgeslagen in DB.");
        return ResponseEntity.ok("Gebruiker succesvol geregistreerd");
    }

    @PostMapping("/login")
    public ResponseEntity<authResponse> login(@RequestBody authRequest request) {
        System.out.println("Login voor: " + request.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtProvider.generateToken(request.getUsername());
        return ResponseEntity.ok(new authResponse(token));
    }
}
