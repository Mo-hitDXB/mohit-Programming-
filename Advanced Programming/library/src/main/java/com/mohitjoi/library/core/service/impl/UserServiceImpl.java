package com.mohitjoi.library.core.service.impl;

import com.mohitjoi.library.core.model.User;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.repository.UserRepository;
import com.mohitjoi.library.core.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) userDetails;
    }

    @Override
    public ResponseEntityDto getMe() {
//        EmployeeResponseDto employeeResponseDto = mapper.userToEmployeeResponseDto(this.getCurrentUser());
//        return new ResponseEntityDto(false, employeeResponseDto);
        // TODO: fix this
        return null;
    }
}
