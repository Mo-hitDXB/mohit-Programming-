package com.mohitjoi.library.core.payload.response;

import com.mohitjoi.library.core.type.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Role role;

    private String token;

}

