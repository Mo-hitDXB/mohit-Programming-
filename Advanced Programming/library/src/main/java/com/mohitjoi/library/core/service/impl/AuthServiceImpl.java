package com.mohitjoi.library.core.service.impl;

import com.mohitjoi.library.core.mapper.UserMapper;
import com.mohitjoi.library.core.model.User;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.SigninRequest;
import com.mohitjoi.library.core.payload.request.SignupRequest;
import com.mohitjoi.library.core.payload.response.AuthResponseDto;
import com.mohitjoi.library.core.repository.UserRepository;
import com.mohitjoi.library.core.service.AuthService;
import com.mohitjoi.library.core.service.JwtService;
import com.mohitjoi.library.core.type.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @NonNull
    private final AuthenticationManager authenticationManager;

    @NonNull
    private final JwtService jwtService;

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final UserMapper userMapper;

    @Override
    public ResponseEntityDto signup(SignupRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhoneNumber());
        user.setRole(Role.USER);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);
        AuthResponseDto authResponseDto = userMapper.userToAuthResponseDto(user, jwt);

        return new ResponseEntityDto(false, authResponseDto);
    }

    @Override
    public ResponseEntityDto signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User login failed!"));

        String jwt = jwtService.generateToken(user);
        AuthResponseDto authResponseDto = userMapper.userToAuthResponseDto(user, jwt);

        return new ResponseEntityDto(false, authResponseDto);
    }

}
