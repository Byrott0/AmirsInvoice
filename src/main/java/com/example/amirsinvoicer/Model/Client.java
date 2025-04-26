package com.example.amirsinvoicer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    @ManyToOne
    private User user;  // De gebruiker die is toegevoegd door de Admin
}