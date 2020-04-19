package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RevenueCategoryTest {

    @Test
    public void createsRevenueCategory() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        RevenueCategory testRevenueCategory = testEnv.getWorkRevenueCategory();

        assertNotNull(testRevenueCategory);
        assertNotNull(testRevenueCategory.getColor());
        assertEquals("work", testRevenueCategory.getCategoryName());
        assertEquals(testEnv.getBasicUserWallet(), testRevenueCategory.getWallet());
    }

}
