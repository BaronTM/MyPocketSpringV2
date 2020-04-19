package com.myPocket.myPocket.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Long idWallet;

    @OneToOne(mappedBy = "wallet", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Account> accountList;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ExpenseCategory> expenceList;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RevenueCategory> revenueList;

    public Wallet(boolean createEmptyLists) {
        if (createEmptyLists) {
            this.accountList = new ArrayList<>();
            this.expenceList = new ArrayList<>();
            this.revenueList = new ArrayList<>();
        }
    }

    public void addAccount(String accountName) {
        Account account = new Account(accountName);
        accountList.add(account);
    }

    public void addExpenseCategory(String categoryName) {
        ExpenseCategory expenseCategory = new ExpenseCategory(categoryName, this);
        expenceList.add(expenseCategory);
    }

    public void addRevenueCategory(String categoryName) {
        RevenueCategory revenueCategory = new RevenueCategory(categoryName, this);
        revenueList.add(revenueCategory);
    }

    public Map<ExpenseCategory, Double> getExpensesMap() {
        Map<ExpenseCategory, Double> expensesMap = new HashMap<>();

        for (Account account : accountList) {
            for (Expense expense : account.getExpenses()) {
                Double val = null;
                ExpenseCategory expenseCategory = expense.getExpenseCategory();

                if (expensesMap.containsKey(expenseCategory)) {
                    val = expensesMap.get(expenseCategory);
                } else {
                    val = new Double(0);
                }

                val = val.doubleValue() + expense.getValue();
                expensesMap.put(expenseCategory, val);
            }
        }

        return expensesMap;
    }

    public Map<RevenueCategory, Double> getRevenueMap() {
        Map<RevenueCategory, Double> revenueMap = new HashMap<>();
        for (Account a : accountList) {
            for (Revenue r : a.getRevenues()) {
                Double val;
                if (revenueMap.containsKey(r.getRevenueCategory())) {
                    val = revenueMap.get(r.getRevenueCategory());
                } else {
                    val = new Double(0);
                }
                val += r.getValue();
                revenueMap.put(r.getRevenueCategory(), val);
            }
        }
        return revenueMap;
    }
}
