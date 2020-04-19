package com.myPocket.myPocket.controller.configuration.filters;

import com.myPocket.myPocket.controller.repository.UserRepository;
import com.myPocket.myPocket.controller.security.filters.JwtRequestFilter;
import com.myPocket.myPocket.controller.services.UserRepositoryUserDetailService;
import com.myPocket.myPocket.controller.utils.JwtUtil;
import com.myPocket.myPocket.environment.TestEnvironment;
import com.myPocket.myPocket.model.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Import(JwtRequestFilterTestConfiguration.class)
@SpringBootTest(classes = JwtRequestFilter.class)
public class JwtRequestFilterTest {

    @MockBean
    private UserRepository userRepositoryMock;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private FilterChain filterChainMock;

    private void setup() {
        filterChainMock = new MockFilterChain();
    }

    @Test
    public void notPassFilterIfNoHeader() throws ServletException, IOException {
        this.setup();

        Mockito.when(requestMock.getHeader("Authorization")).thenReturn(null);

        jwtRequestFilter.doFilterInternal(requestMock, responseMock, filterChainMock);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assertions.assertNull(authentication);
    }

    @Test
    public void notPassFilterIfEmptyHeader() throws ServletException, IOException {
        this.setup();

        Mockito.when(requestMock.getHeader("Authorization")).thenReturn("");

        jwtRequestFilter.doFilterInternal(requestMock, responseMock, filterChainMock);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assertions.assertNull(authentication);
    }

    @Test
    public void notPassFilterIfWrongHeaderSting() throws ServletException, IOException {
        this.setup();

        Mockito.when(requestMock.getHeader("Authorization")).thenReturn("someSting");

        jwtRequestFilter.doFilterInternal(requestMock, responseMock, filterChainMock);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assertions.assertNull(authentication);
    }

    @Test
    public void notPassFilterIfWrongTokenString() throws ServletException, IOException {
        this.setup();

        Mockito.when(requestMock.getHeader("Authorization")).thenReturn("Bearer " + "someString");

        jwtRequestFilter.doFilterInternal(requestMock, responseMock, filterChainMock);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assertions.assertNull(authentication);
    }

    @Test
    public void notPassFilterIfOverdueToken() throws ServletException, IOException {
        this.setup();

        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();
        User testUser = testEnv.getBasicUser();

        Date expirationDate = new Date(System.currentTimeMillis() - 1000 * 60 * 30);
        String token = jwtUtil.generateTokenWithExpirationDate(testUser, expirationDate);

        Mockito.when(requestMock.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userRepositoryMock.findByUsername(testUser.getUsername())).thenReturn(testUser);

        jwtRequestFilter.doFilterInternal(requestMock, responseMock, filterChainMock);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assertions.assertNull(authentication);
    }

    @Test
    public void passFilterIfProperToken() throws ServletException, IOException {
        this.setup();

        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();
        User testUser = testEnv.getBasicUser();

        String token = jwtUtil.generateToken(testUser);

        Mockito.when(requestMock.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userRepositoryMock.findByUsername(testUser.getUsername())).thenReturn(testUser);

        jwtRequestFilter.doFilterInternal(requestMock, responseMock, filterChainMock);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticationUser = (User) authentication.getPrincipal();

        Assertions.assertEquals(testUser.getUsername(), authenticationUser.getUsername());
        Assertions.assertEquals(testUser.getPassword(), authenticationUser.getPassword());
    }
}

@TestConfiguration
class JwtRequestFilterTestConfiguration {

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean @Autowired
    public UserRepositoryUserDetailService userRepositoryUserDetailService(UserRepository userRepository) {
        return new UserRepositoryUserDetailService(userRepository);
    }
}
