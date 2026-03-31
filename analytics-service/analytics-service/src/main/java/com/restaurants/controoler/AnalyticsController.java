package com.restaurants.controoler;

import com.restaurants.model.InventoryItem;
import com.restaurants.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/inventory/{restaurantId}")
    public List<InventoryItem> getInventoryAnalytics(@PathVariable Long restaurantId) {
        return analyticsService.getInventoryUsage(restaurantId);
    }
}