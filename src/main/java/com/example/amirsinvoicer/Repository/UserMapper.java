package com.example.amirsinvoicer.Repository;

import com.example.amirsinvoicer.Dto.UserResponseDto;
import com.example.amirsinvoicer.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserResponseDto fromEntity(User user) {

        return UserResponseDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
