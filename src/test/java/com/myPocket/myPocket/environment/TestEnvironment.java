package com.myPocket.myPocket.environment;

import com.myPocket.myPocket.model.entities.*;
import lombok.Data;

import java.util.Optional;

@Data
public class TestEnvironment {

    private User basicUser;
    private Wallet basicUserWallet;
    private Account firstAccount;
    private Account secondAccount;
    private ExpenseCategory carExpenseCategory;
    private ExpenseCategory homeExpenseCategory;
    private RevenueCategory workRevenueCategory;
    private RevenueCategory passiveRevenueCategory;

    public TestEnvironment createBasicUser() {
        if (this.basicUser == null) {
            this.basicUser = new User("basicUser", "basicUser123");
            this.basicUserWallet = basicUser.getWallet();
        }

        return this;
    }

    public TestEnvironment createFirstAccount() {
        if (this.firstAccount == null) {
            createBasicUser();

            this.basicUserWallet.addAccount("firstAccount");
            Optional<Account> account = this.basicUserWallet.getAccountList()
                    .stream().filter(acc -> acc.getAccountName() == "firstAccount").findAny();
            this.firstAccount = account.get();
        }

        return this;
    }

    public TestEnvironment createSecondAccount() {
        if (this.secondAccount == null) {
            createBasicUser();

            this.basicUserWallet.addAccount("secondAccount");
            Optional<Account> account = this.basicUserWallet.getAccountList()
                    .stream().filter(acc -> acc.getAccountName() == "secondAccount").findAny();
            this.secondAccount = account.get();
        }

        return this;
    }

    public TestEnvironment createCarExpenseCategory() {
        if (this.carExpenseCategory == null) {
            createBasicUser();

            this.basicUserWallet.addExpenseCategory("car");
            Optional<ExpenseCategory> expenseCategory = this.basicUserWallet.getExpenceList()
                    .stream().filter(category -> category.getCategoryName() == "car").findAny();

            this.carExpenseCategory = expenseCategory.get();
        }

        return this;
    }

    public TestEnvironment createHomeExpenseCategory() {
        if (this.homeExpenseCategory == null) {
            createBasicUser();

            this.basicUserWallet.addExpenseCategory("home");
            Optional<ExpenseCategory> expenseCategory = this.basicUserWallet.getExpenceList()
                    .stream().filter(category -> category.getCategoryName() == "home").findAny();

            this.homeExpenseCategory = expenseCategory.get();
        }

        return this;
    }

    public TestEnvironment createWorkRevenueCategory() {
        if (this.workRevenueCategory == null) {
            createBasicUser();

            this.basicUserWallet.addRevenueCategory("work");
            Optional<RevenueCategory> revenueCategory = this.basicUserWallet.getRevenueList()
                    .stream().filter(category -> category.getCategoryName() == "work").findAny();

            this.workRevenueCategory = revenueCategory.get();
        }

        return this;
    }

    public TestEnvironment createPassiveRevenueCategory() {
        if (this.passiveRevenueCategory == null) {
            createBasicUser();

            this.basicUserWallet.addRevenueCategory("passive");
            Optional<RevenueCategory> revenueCategory = this.basicUserWallet.getRevenueList()
                    .stream().filter(category -> category.getCategoryName() == "passive").findAny();

            this.passiveRevenueCategory = revenueCategory.get();
        }

        return this;
    }

    public TestEnvironment createBasicEnvironment() {
        return this.createBasicUser()
                .createFirstAccount()
                .createSecondAccount()
                .createCarExpenseCategory()
                .createHomeExpenseCategory()
                .createWorkRevenueCategory()
                .createPassiveRevenueCategory();
    }
}
