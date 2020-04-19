package com.myPocket.myPocket.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "revenue")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revenue")
    @Getter @Setter
    private Integer idRevenue;

    @Column(name = "date")
    @Getter @Setter
    private Date date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_revenue_category")
    @Getter @Setter
    private RevenueCategory revenueCategory;

    @Column(name = "value")
    @Getter @Setter
    private double value;

    @Column(name = "description")
    @Getter @Setter
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_account")
    @Getter @Setter
    private Account account;

    public Revenue(RevenueCategory revenueCategory, Account account, double value) {
        this.revenueCategory = revenueCategory;
        this.account = account;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "idRevenue=" + idRevenue +
                ", date=" + date +
                ", revenueCategory=" + revenueCategory +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
