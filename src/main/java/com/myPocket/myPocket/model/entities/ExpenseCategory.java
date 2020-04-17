package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.model.Defaults;
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
    @Getter
    @Setter
    private Integer idExpenseCategory;

    @Getter
    @Setter
    @Column(name = "expense_category_name")
    private String categoryName;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @Getter
    @Setter
    @Column(name = "color")
    private String color;

    public ExpenseCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
        this.color = Defaults.getRandomColor();
    }

}
