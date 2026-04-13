package com.restaurants.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    @Column(name = "name")
    private String itemName;

    private int quantity;

    private int threshold;

    @Column(name = "category")
    private String category;
}