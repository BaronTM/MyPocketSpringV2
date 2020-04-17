package com.myPocket.myPocket.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense")
    @Getter
    @Setter
    private Integer idExpense;

    @Getter
    @Setter
    @Column(name = "date")
    private Date date;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_expense_category")
    private ExpenseCategory expenseCategory;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_account")
    private Account account;

    @Getter
    @Setter
    @Column(name = "value")
    private double value;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    public Expense(ExpenseCategory expenseCategory, Account account, double value) {
        this.expenseCategory = expenseCategory;
        this.account = account;
        this.value = value;
    }


}
