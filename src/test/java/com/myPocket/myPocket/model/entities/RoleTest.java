package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    public void createsRole() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Role testRole = new Role(testEnv.getBasicUser(), "testRole");

        assertEquals(testEnv.getBasicUser(), testRole.getUser());
        assertEquals("testRole", testRole.getRoleName());
    }

}
