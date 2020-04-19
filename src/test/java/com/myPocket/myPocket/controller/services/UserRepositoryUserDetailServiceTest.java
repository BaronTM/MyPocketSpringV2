package com.myPocket.myPocket.controller.services;


import com.myPocket.myPocket.controller.repository.UserRepository;
import com.myPocket.myPocket.environment.TestEnvironment;
import com.myPocket.myPocket.model.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserRepositoryUserDetailService.class)
public class UserRepositoryUserDetailServiceTest {

    @MockBean
    private UserRepository userRepositoryMock;

    @Autowired
    private UserRepositoryUserDetailService userRepositoryUserDetailService;

    @Test
    public void loadsUserByUsername() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User testUser = testEnv.getBasicUser();

        Mockito.when(userRepositoryMock.findByUsername("basicUser")).thenReturn(testUser);

        UserDetails returnedUser = userRepositoryUserDetailService.loadUserByUsername("basicUser");

        assertEquals(testUser, returnedUser);
    }

    @Test
    public void throwsExceptionWhenCantLoadUserByUsername() {
        Mockito.when(userRepositoryMock.findByUsername("basicUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            UserDetails returnedUser = userRepositoryUserDetailService.loadUserByUsername("basicUser");
        });
    }

}
