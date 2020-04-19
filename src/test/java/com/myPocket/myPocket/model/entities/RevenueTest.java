package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RevenueTest {

    @Test
    public void createsRevenue() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Revenue testRevenue = new Revenue(testEnv.getWorkRevenueCategory(), testEnv.getFirstAccount(), 300);

        assertNotNull(testRevenue);
        assertEquals(testEnv.getWorkRevenueCategory(), testRevenue.getRevenueCategory());
        assertEquals(testEnv.getFirstAccount(), testRevenue.getAccount());
        assertEquals(300, testRevenue.getValue());
    }
}
