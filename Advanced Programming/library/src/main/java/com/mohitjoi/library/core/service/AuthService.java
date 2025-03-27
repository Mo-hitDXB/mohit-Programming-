package com.mohitjoi.library.core.service;

import com.mohitjoi.library.core.payload.request.SigninRequest;
import com.mohitjoi.library.core.payload.request.SignupRequest;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;

public interface AuthService {

    ResponseEntityDto signup(SignupRequest request);

    ResponseEntityDto signin(SigninRequest request);
}

