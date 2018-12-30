package com.ddingcham.example.hystrix.domain;

import com.ddingcham.example.hystrix.domain.support.RestAreaGrade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "REST_AREA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"name", "latitude", "longitude", "grade"})
public class RestArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_area_id")
    private Long id;

    @Column(name = "rest_area_name")
    private String name;

    @Column(name = "rest_area_latitude")
    private Double latitude;

    @Column(name = "rest_area_longitude")
    private Double longitude;

    @Column(name = "rest_area_rating")
    @Enumerated(EnumType.STRING)
    private RestAreaGrade grade;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rest_area_id")
    @JsonIgnore
    private List<Food> foods = new ArrayList<>();

    public void addFood(Food food) {
        if (food == null || !food.isMatchedRestArea(name)) {
            throw new IllegalArgumentException("Invalid Food");
        }
        foods.add(food);
    }

    public void addBestSalesToFood(BestSales bestSales) {
        foods.stream()
                .filter(food -> food.equalsFoodName(bestSales.getFood()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new)
                .addBestSales(bestSales);
    }
}
