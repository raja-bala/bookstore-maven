package com.wecode.bookstoremaven.service;

import com.wecode.bookstoremaven.dto.UserDto;
import com.wecode.bookstoremaven.model.User;
import com.wecode.bookstoremaven.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
    public Long addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(null);
        User createdUser =userRepository.saveAndFlush(user);
       return createdUser.getId();

    }
    public UserDto getUserByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(Objects.isNull(byEmail)){
            throw new RuntimeException("user not exist with email" + email);
        }
       return modelMapper.map(byEmail, UserDto.class);

    }

}
