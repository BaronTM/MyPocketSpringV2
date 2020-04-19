package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    @Test
    public void properlyCreatesAccount() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Account testAccount = testEnv.getFirstAccount();

        assertNotNull(testAccount);
        assertNotNull(testAccount.getWallet());
        assertNotNull(testAccount.getExpenses());
        assertNotNull(testAccount.getRevenues());
        assertEquals("firstAccount" ,testAccount.getAccountName());
        assertEquals(testEnv.getBasicUserWallet() ,testAccount.getWallet());
        assertTrue(testAccount.getRevenues().isEmpty());
        assertTrue(testAccount.getExpenses().isEmpty());
    }

    @Test
    public void properlyAddsExpenses() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Account testAccount = testEnv.getFirstAccount();

        testAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), testAccount, 1000));
        testAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), testAccount, 500));
        testAccount.addExpense(new Expense(testEnv.getHomeExpenseCategory(), testAccount, 30));

        assertEquals(3, testAccount.getExpenses().size());
    }

    @Test
    public void properlyAddsRevenues() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Account testAccount = testEnv.getFirstAccount();

        testAccount.addRevenue(new Revenue(testEnv.getWorkRevenueCategory(), testAccount, 1000));
        testAccount.addRevenue(new Revenue(testEnv.getWorkRevenueCategory(), testAccount, 500));
        testAccount.addRevenue(new Revenue(testEnv.getPassiveRevenueCategory(), testAccount, 30));

        assertEquals(3, testAccount.getRevenues().size());
    }

    @Test
    public void properlyCalculatesExpensesValue() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Account testAccount = testEnv.getFirstAccount();

        testAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), testAccount, 1000));
        testAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), testAccount, 500));
        testAccount.addExpense(new Expense(testEnv.getHomeExpenseCategory(), testAccount, 30));

        assertEquals(1530, testAccount.getAllExpensesValue());
    }

    @Test
    public void properlyCalculatesRevenuesValue() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Account testAccount = testEnv.getFirstAccount();

        testAccount.addRevenue(new Revenue(testEnv.getWorkRevenueCategory(), testAccount, 1000));
        testAccount.addRevenue(new Revenue(testEnv.getWorkRevenueCategory(), testAccount, 500));
        testAccount.addRevenue(new Revenue(testEnv.getPassiveRevenueCategory(), testAccount, 30));

        assertEquals(1530, testAccount.getAllRevenuesValue());
    }

    @Test
    public void properlyCalculatesBalance() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Account testAccount = testEnv.getFirstAccount();

        testAccount.addRevenue(new Revenue(testEnv.getWorkRevenueCategory(), testAccount, 1000));
        testAccount.addRevenue(new Revenue(testEnv.getWorkRevenueCategory(), testAccount, 500));
        testAccount.addRevenue(new Revenue(testEnv.getPassiveRevenueCategory(), testAccount, 30));

        testAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), testAccount, 100));
        testAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), testAccount, 50));
        testAccount.addExpense(new Expense(testEnv.getHomeExpenseCategory(), testAccount, 3));

        assertEquals(1377, testAccount.getBalance());
    }

}
