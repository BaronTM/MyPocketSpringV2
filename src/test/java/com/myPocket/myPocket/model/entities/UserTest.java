package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void createsUser() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User testUser = testEnv.getBasicUser();

        assertNotNull(testUser);
        assertNotNull(testUser.getWallet());
        assertEquals("basicUser", testUser.getUsername());
        assertEquals("basicUser123", testUser.getPassword());
        assertEquals("USER", testUser.getRoles().get(0).getRoleName());
        assertEquals(true, testUser.isEnabled());
        assertEquals(true, testUser.isAccountNonExpired());
        assertEquals(true, testUser.isAccountNonLocked());
        assertEquals(true, testUser.isCredentialsNonExpired());
    }

    @Test
    public void returnsAuthoritiesBasedOnRoles() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User testUser = testEnv.getBasicUser();
        
        testUser.getRoles().add(new Role(testUser, "ADMIN"));
        testUser.getRoles().add(new Role(testUser, "MODERATOR"));

        List<SimpleGrantedAuthority> authorities = testUser.getAuthorities();
        List<String> authorityNames = authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());

        assertTrue(authorityNames.contains("USER"));
        assertTrue(authorityNames.contains("ADMIN"));
        assertTrue(authorityNames.contains("MODERATOR"));
    }
}
