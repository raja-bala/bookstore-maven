package com.wecode.bookstoremaven.service;

import com.wecode.bookstoremaven.dto.UserDto;
import com.wecode.bookstoremaven.model.User;
import com.wecode.bookstoremaven.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void shouldReturnUserIdWhenCalledWithUserData() {
        Long id = 1L;

        when(passwordEncoder.encode(any())).thenReturn("koala");
        when(userRepository.saveAndFlush(any())).thenReturn(getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(getUser(id));

        Long uid = userService.addUser(getUserDto());

        assertThat(uid).isNotNull();
        assertThat(uid).isEqualTo(id);
    }
    @Test
    public void shouldReturnUserWhenCalledWhenEmailIsExist() {
        Long id = 1L;


        when(userRepository.findByEmail(any())).thenReturn(getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(getUserDto());

        UserDto user = userService.getUserByEmail("email");

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("email");
    }
    @Test
    public void shouldReturnUserWhenCalledWhenEmailIsNotExist() {
        Long id = 1L;


        when(userRepository.findByEmail(any())).thenThrow(new RuntimeException(("error")));

        assertThatThrownBy(()-> userService.getUserByEmail("email")).isInstanceOf(RuntimeException.class);

    }
    private UserDto getUserDto() {
        return  UserDto.builder()
                .password("password")
                .id(1L)
                .name("username")
                .email("email")
                .build();
    }
    private User getUser(Long id) {
        return User.builder()
                .password("password")
                .id(id)
                .name("username")
                .email("email")
                .build();
    }

}
