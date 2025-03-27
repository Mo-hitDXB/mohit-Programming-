package com.mohitjoi.library.core.mapper;

import com.mohitjoi.library.core.model.User;
import com.mohitjoi.library.core.payload.response.AuthResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AuthResponseDto userToAuthResponseDto(User user, String token) {
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setId(user.getId());
        authResponseDto.setFirstName(user.getFirstName());
        authResponseDto.setLastName(user.getLastName());
        authResponseDto.setEmail(user.getEmail());
        authResponseDto.setPhone(user.getPhone());
        authResponseDto.setRole(user.getRole());
        authResponseDto.setToken(token);
        return authResponseDto;
    }

}
