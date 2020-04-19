package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseCategoryTest {

    @Test
    public void createsExpenseCategory() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        ExpenseCategory testExpenseCategory = testEnv.getCarExpenseCategory();

        assertNotNull(testExpenseCategory);
        assertNotNull(testExpenseCategory.getColor());
        assertEquals("car", testExpenseCategory.getCategoryName());
        assertEquals(testEnv.getBasicUserWallet(), testExpenseCategory.getWallet());
    }
}
