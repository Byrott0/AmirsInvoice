package com.example.amirsinvoicer.Dto;

import com.example.amirsinvoicer.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
