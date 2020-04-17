package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.model.Defaults;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "revenue_category")
public class RevenueCategory {

    @Id
    @Column(name = "id_revenue_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer idRevenueCategoty;

    @Getter
    @Setter
    @Column(name = "revenue_category_name")
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

    public RevenueCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
        this.color = Defaults.getRandomColor();
    }

}
