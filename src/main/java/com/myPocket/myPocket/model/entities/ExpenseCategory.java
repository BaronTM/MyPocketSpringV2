package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.model.utils.Defaults;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "expense_category")
public class ExpenseCategory {

    @Id
    @Column(name = "id_expense_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer idExpenseCategory;

    @Column(name = "expense_category_name")
    @Getter @Setter
    private String categoryName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    @Getter @Setter
    private Wallet wallet;

    @Column(name = "color")
    @Getter @Setter
    private String color;

    public ExpenseCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
        this.color = Defaults.getRandomColor();
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "idExpenseCategory=" + idExpenseCategory +
                ", categoryName='" + categoryName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
