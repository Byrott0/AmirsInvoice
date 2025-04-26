package com.example.amirsinvoicer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING) // Voeg dit toe zodat Role mooi als tekst wordt opgeslagen
    private Role role;
}