package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    
    @Test
    public void createsExpense() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Expense testExpense = new Expense(testEnv.getCarExpenseCategory(), testEnv.getFirstAccount(), 300);

        assertNotNull(testExpense);
        assertEquals(testEnv.getCarExpenseCategory(), testExpense.getExpenseCategory());
        assertEquals(testEnv.getFirstAccount(), testExpense.getAccount());
        assertEquals(300, testExpense.getValue());
    }
}
