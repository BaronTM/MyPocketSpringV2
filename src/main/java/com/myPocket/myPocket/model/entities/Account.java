package com.myPocket.myPocket.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    @Getter @Setter
    private Integer idAccount;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    @Getter @Setter
    private Wallet wallet;

    @Column(name = "account_name")
    @Getter @Setter
    private String accountName;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Getter @Setter
    private List<Expense> expenses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Getter @Setter
    private List<Revenue> revenues;

    public Account(String accountName, Wallet wallet) {
        this.accountName = accountName;
        this.wallet = wallet;
        this.expenses = new ArrayList<>();
        this.revenues = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public void addRevenue(Revenue revenue) {
        this.revenues.add(revenue);
    }


    public double getAllExpensesValue() {
        return expenses.stream().mapToDouble(e -> e.getValue()).sum();
    }

    public double getAllRevenuesValue() {
        return revenues.stream().mapToDouble(r -> r.getValue()).sum();
    }

    public double getBalance() {
        return getAllRevenuesValue() - getAllExpensesValue();
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", accountName='" + accountName + '\'' +
                ", expenses=" + expenses +
                ", revenues=" + revenues +
                '}';
    }
}
