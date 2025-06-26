package com.journalapp.Journal.service;

import com.journalapp.Journal.entity.User;
import com.journalapp.Journal.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class User_ServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User_Service userService;

    @ParameterizedTest
    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUsername(User user){
        assertTrue(userService.saveNewEntry(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "2,1,1",
            "10,5,5"
    })
    public void testAdd(int expected, int a, int b){
        assertEquals(expected, a, b);
    }
}
