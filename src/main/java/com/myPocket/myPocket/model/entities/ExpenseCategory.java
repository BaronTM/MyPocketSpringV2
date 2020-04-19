package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.model.utils.Defaults;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "expense_category")
public class ExpenseCategory {

    @Id
    @Column(name = "id_expense_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpenseCategory;

    @Column(name = "expense_category_name")
    private String categoryName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @Column(name = "color")
    private String color;

    public ExpenseCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
        this.color = Defaults.getRandomColor();
    }

}
