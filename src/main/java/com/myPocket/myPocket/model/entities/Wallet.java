package com.myPocket.myPocket.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wallet")
public class Wallet {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Integer idWallet;

    @Getter
    @Setter
    @OneToOne(mappedBy = "wallet", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Account> accountList;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ExpenseCategory> expenceList;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RevenueCategory> revenueList;

    public Wallet(User user) {
        this.user = user;
        this.accountList = new ArrayList<>();
        this.expenceList = new ArrayList<>();
        this.revenueList = new ArrayList<>();
    }

    public void addAccount(String accountName) {
        Account account = new Account(accountName, this);
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

    public Map<ExpenseCategory, Double> getExpencesMap() {
        Map<ExpenseCategory, Double> expencesMap = new HashMap<>();
        for (Account a : accountList) {
            for (Expense e : a.getExpenses()) {
                Double val;
                if (expencesMap.containsKey(e.getExpenseCategory())) {
                    val = expencesMap.get(e.getExpenseCategory());
                } else {
                    val = new Double(0);
                }
                val += e.getValue();
                expencesMap.put(e.getExpenseCategory(), val);
            }
        }
        return expencesMap;
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
