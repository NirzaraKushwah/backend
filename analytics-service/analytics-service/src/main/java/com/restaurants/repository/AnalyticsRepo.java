package com.restaurants.repository;

import com.restaurants.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnalyticsRepo extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByRestaurantId(Long restaurantId);

}