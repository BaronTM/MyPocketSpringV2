package com.myPocket.myPocket.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Integer idAccount;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @Column(name = "account_name")
    private String accountName;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Revenue> revenues;

    public Account(String accountName) {
        this.accountName = accountName;
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


}
