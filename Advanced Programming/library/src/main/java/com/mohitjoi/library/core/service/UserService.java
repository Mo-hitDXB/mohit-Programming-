package com.mohitjoi.library.core.service;

import com.mohitjoi.library.core.model.User;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    User getCurrentUser();

    ResponseEntityDto getMe();
}