package com.restaurants.service;

import com.restaurants.model.InventoryItem;
import com.restaurants.repository.AnalyticsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final AnalyticsRepo inventoryRepository;

    public AnalyticsService(AnalyticsRepo inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryItem> getInventoryUsage(Long restaurantId) {
        return inventoryRepository.findByRestaurantId(restaurantId);
    }

}