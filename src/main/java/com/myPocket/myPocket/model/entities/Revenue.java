package com.myPocket.myPocket.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "revenue")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revenue")
    private Integer idRevenue;

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_revenue_category")
    private RevenueCategory revenueCategory;

    @Column(name = "value")
    private double value;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_account")
    private Account account;

    public Revenue(RevenueCategory revenueCategory, Account account, double value) {
        this.revenueCategory = revenueCategory;
        this.account = account;
        this.value = value;
    }

}
