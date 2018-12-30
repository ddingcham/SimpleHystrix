package com.ddingcham.example.hystrix.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "BEST_SALES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class BestSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "best_sales_id")
    private Long id;

    @Column(name = "best_sales_stn_date")
    private String date;

    @Column(name = "best_sales_grade")
    private String grade;

    @Transient
    private String food;

    @Transient
    private String restArea;

}
