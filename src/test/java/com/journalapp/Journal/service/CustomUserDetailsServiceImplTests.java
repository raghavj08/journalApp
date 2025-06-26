package com.journalapp.Journal.service;

import com.journalapp.Journal.entity.User;
import com.journalapp.Journal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class CustomUserDetailsServiceImplTests {

    @InjectMocks
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void loadByUsernameTests(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("raghav").password("rjsgfjk").roles(new ArrayList<>()).build());
        UserDetails user = customUserDetailsService.loadUserByUsername("raghav");
        Assertions.assertNotNull(user);
    }

}
