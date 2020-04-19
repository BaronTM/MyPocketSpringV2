package com.myPocket.myPocket.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense")
    private Integer idExpense;

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_expense_category")
    private ExpenseCategory expenseCategory;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_account")
    private Account account;

    @Column(name = "value")
    private double value;

    @Column(name = "description")
    private String description;

    public Expense(ExpenseCategory expenseCategory, Account account, double value) {
        this.expenseCategory = expenseCategory;
        this.account = account;
        this.value = value;
    }


}
