package com.example.amirsinvoicer.Dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UserDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
