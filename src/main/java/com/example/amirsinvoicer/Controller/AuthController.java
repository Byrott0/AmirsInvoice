package com.example.amirsinvoicer.Controller;


import com.example.amirsinvoicer.Dto.Auth.*;
import com.example.amirsinvoicer.Dto.Auth.IdCheck;
import com.example.amirsinvoicer.Dto.Auth.authRequest;
import com.example.amirsinvoicer.Dto.UserCreateDto;
import com.example.amirsinvoicer.Model.ApiResponse;
import com.example.amirsinvoicer.Model.*;
import com.example.amirsinvoicer.Service.JwtService;
import com.example.amirsinvoicer.Service.UserService;
import com.example.amirsinvoicer.Service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticationService;
    private final JwtService jwtService;

    @GetMapping("/checkId/{id}")
    public ApiResponse<IdCheck> checkId(@PathVariable String id, Authentication authentication) {
        return new ApiResponse<>(new IdCheck(this.authenticationService.isIdOfSelf(UUID.fromString(id), authentication)), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ApiResponse<?> login(@Valid @RequestBody authRequest loginDTO, HttpServletResponse response) {
        authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword(), response);
        return new ApiResponse<>("Login succes", HttpStatus.OK);
    }

    // Voor testing doeleinden
    @PostMapping(value = "/register")
    public ApiResponse<authResponse> register(@RequestBody UserCreateDto userCreateDTO) {
        Optional<String> tokenResponse = authenticationService.register(
                userCreateDTO.getUsername(),
                userCreateDTO.getPassword()

        );

        if (tokenResponse.isEmpty()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        String token = tokenResponse.get();
        return new ApiResponse<>(new authResponse(token));
    }

    @GetMapping(value = "/authenticated")
    public ApiResponse<AuthCheckResponseDto> checkAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        AuthCheckResponseDto authCheckResponseDTO = this.authenticationService.checkAuthenticated(request);

        if (!authCheckResponseDTO.isAuthenticated()) {
            // Clears the cookies in case the browser still has them stored whilst they're invalid
            Cookie cookie = this.authenticationService.getEmptyCookie("token");
            response.addCookie(cookie);
        }

        return new ApiResponse<>(authCheckResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        response.addCookie(this.authenticationService.logout());
    }

    @GetMapping("/isAdmin")
    public ApiResponse<AdminCheckResponseDto> isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
        return new ApiResponse<>(new AdminCheckResponseDto(isAdmin), HttpStatus.OK);
    }
}
