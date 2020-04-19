package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.environment.TestEnvironment;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    public void createsWallet() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Wallet testWallet = testEnv.getBasicUserWallet();

        assertNotNull(testWallet);
        assertNotNull(testWallet.getAccountList());
        assertNotNull(testWallet.getRevenueList());
        assertNotNull(testWallet.getExpenceList());
        assertEquals(testEnv.getBasicUser(), testWallet.getUser());
    }

    @Test
    public void addsAccounts() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Wallet testWallet = testEnv.getBasicUserWallet();

        assertTrue(testWallet.getAccountList().contains(testEnv.getFirstAccount()));
        assertTrue(testWallet.getAccountList().contains(testEnv.getSecondAccount()));
    }

    @Test
    public void addsRevenueCategories() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Wallet testWallet = testEnv.getBasicUserWallet();

        assertTrue(testWallet.getRevenueList().contains(testEnv.getWorkRevenueCategory()));
        assertTrue(testWallet.getRevenueList().contains(testEnv.getPassiveRevenueCategory()));
    }

    @Test
    public void addsExpenseCategories() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Wallet testWallet = testEnv.getBasicUserWallet();

        assertTrue(testWallet.getExpenceList().contains(testEnv.getCarExpenseCategory()));
        assertTrue(testWallet.getExpenceList().contains(testEnv.getHomeExpenseCategory()));
    }

    @Test
    public void returnsExpensesMap() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        Wallet testWallet = testEnv.getBasicUserWallet();

        Account firstAccount = testWallet.getAccountList().get(0);
        Account secondAccount = testWallet.getAccountList().get(1);

        firstAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), firstAccount, 1000));
        firstAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), firstAccount, 500));
        firstAccount.addExpense(new Expense(testEnv.getHomeExpenseCategory(), firstAccount, 30));

        secondAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), secondAccount, 5000));
        secondAccount.addExpense(new Expense(testEnv.getCarExpenseCategory(), secondAccount, 150));
        secondAccount.addExpense(new Expense(testEnv.getHomeExpenseCategory(), secondAccount, 21));

        Map<ExpenseCategory, Double> valuesByExpenseCategory = testWallet.getExpensesMap();

        assertEquals(6650, valuesByExpenseCategory.get(testEnv.getCarExpenseCategory()));
        assertEquals(51, valuesByExpenseCategory.get(testEnv.getHomeExpenseCategory()));
    }
}
