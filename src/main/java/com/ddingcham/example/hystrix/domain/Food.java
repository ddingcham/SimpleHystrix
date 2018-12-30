package com.ddingcham.example.hystrix.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "FOOD")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "food_name")
    private String name;

    @Column(name = "food_price")
    private Long price;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "food_dscrp")
    private String description;

    @Column(name = "food_rcmmd")
    private Boolean recommendedFood;

    @Column(name = "food_season")
    private String season;

    @Column(name = "food_rprsn")
    private Boolean batchFood;

    @Column(name = "food_ingrd")
    private String ingredients;

    @Column(name = "rest_area_id")
    private Long restAreaId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    @Fetch(FetchMode.JOIN)
    private List<BestSales> bestSales = new ArrayList<>();

    @Transient
    private String restArea;

    public boolean isMatchedRestArea(String restAreaName) {
        if (isNullString(restAreaName)) {
            throw new IllegalArgumentException("invalid restAreaName");
        }
        return restAreaName.equals(this.restArea);
    }

    public boolean equalsFoodName(String foodName) {
        if (isNullString(foodName)) {
            throw new IllegalArgumentException("invalid foodName");
        }
        return foodName.equals(this.name);
    }

    public void addBestSales(BestSales bestSale) {
        this.bestSales.add(bestSale);
    }

    private boolean isNullString(String value) {
        return value == null || value.isEmpty();
    }


}
