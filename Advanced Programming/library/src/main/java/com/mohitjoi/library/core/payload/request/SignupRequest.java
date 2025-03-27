package com.mohitjoi.library.core.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

}
