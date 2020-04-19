package com.myPocket.myPocket.model.entities;

import com.myPocket.myPocket.model.utils.Defaults;
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
    @Getter @Setter
    private Integer idRevenueCategoty;

    @Column(name = "revenue_category_name")
    @Getter @Setter
    private String categoryName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    @Getter @Setter
    private Wallet wallet;

    @Column(name = "color")
    @Getter @Setter
    private String color;

    public RevenueCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
        this.color = Defaults.getRandomColor();
    }

    @Override
    public String toString() {
        return "RevenueCategory{" +
                "idRevenueCategoty=" + idRevenueCategoty +
                ", categoryName='" + categoryName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
