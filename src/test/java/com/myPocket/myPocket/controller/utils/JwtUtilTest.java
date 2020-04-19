package com.myPocket.myPocket.controller.utils;

import com.myPocket.myPocket.environment.TestEnvironment;
import com.myPocket.myPocket.model.entities.User;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JwtUtil.class)
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Test
    public void createsJwt() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();

        String token = jwtUtil.generateToken(user);

        assertTrue(!token.isEmpty());
        assertTrue(token.length() > 10);
    }

    @Test
    public void positivelyValidatesToken() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();

        String token = jwtUtil.generateToken(user);

        assertTrue(jwtUtil.validateToken(token, user));
    }

    @Test
    public void negativelyValidatesToken() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();

        String token = jwtUtil.generateToken(user);

        User differentUser = new User("differentuser", "password");

        assertFalse(jwtUtil.validateToken(token, differentUser));
    }

    @Test
    public void extractsUsername() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();

        String token = jwtUtil.generateToken(user);

        assertEquals(user.getUsername(), jwtUtil.extractUsername(token));
    }

    @Test
    public void extractsExpiration() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();

        String token = jwtUtil.generateToken(user);

        Date expirationDate = jwtUtil.extractExpiration(token);

        assertNotNull(expirationDate);
        assertTrue(new Date(System.currentTimeMillis() + 1000 * 60 * 30).getTime() >= expirationDate.getTime());
        assertTrue(new Date(System.currentTimeMillis()).getTime() < expirationDate.getTime());
    }

    @Test
    public void createsTokenWithPastExpirationDate() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();
        Date expirationDate = new Date(System.currentTimeMillis() - 1000 * 60 * 30);

        String token = jwtUtil.generateTokenWithExpirationDate(user, expirationDate);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertThrows(ExpiredJwtException.class, () -> {
            Date tokenExpirationDate = jwtUtil.extractExpiration(token);
        });
    }

    @Test
    public void createsTokenWithExpirationDate() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User user = testEnv.getBasicUser();
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 30);

        String token = jwtUtil.generateTokenWithExpirationDate(user, expirationDate);

        assertNotNull(token);
        assertEquals((long) (expirationDate.getTime() / 1000),
                (long) (jwtUtil.extractExpiration(token).getTime() / 1000));
        assertEquals(user.getUsername(), jwtUtil.extractUsername(token));
    }

}
