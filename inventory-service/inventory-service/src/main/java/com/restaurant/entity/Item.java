package com.restaurant.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int quantity;

    private String category;

    private int threshold;

    @JsonProperty("barcode")
    @Column(name = "barcode")
    private String barcode;

    @JsonProperty("image_url")
    @Column(name = "image_url")
    private String imageUrl;

    private Long restaurantId;

}