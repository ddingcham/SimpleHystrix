package com.ddingcham.example.hystrix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodCategory {
    @Column(name = "food_category_id")
    @Id
    private Long id;

    @Column(name = "food_category_name")
    private String name;
}
