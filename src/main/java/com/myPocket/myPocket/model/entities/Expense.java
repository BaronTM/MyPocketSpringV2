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
    @Getter @Setter
    private Integer idExpense;

    @Column(name = "date")
    @Getter @Setter
    private Date date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_expense_category")
    @Getter @Setter
    private ExpenseCategory expenseCategory;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_account")
    @Getter @Setter
    private Account account;

    @Column(name = "value")
    @Getter @Setter
    private double value;

    @Column(name = "description")
    @Getter @Setter
    private String description;

    public Expense(ExpenseCategory expenseCategory, Account account, double value) {
        this.expenseCategory = expenseCategory;
        this.account = account;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "idExpense=" + idExpense +
                ", date=" + date +
                ", expenseCategory=" + expenseCategory +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
