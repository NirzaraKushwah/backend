package com.restaurant.controller;


import com.restaurant.entity.Item;
import com.restaurant.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Item addItem(@RequestBody Item item) {
        return service.saveItem(item);
    }

    @GetMapping("/all")
    public List<Item> getItems() {
        return service.getAllItems();
    }
    @GetMapping("/low-stock/{restaurantId}")
    public List<Item> getLowStockItems(@PathVariable Long restaurantId) {

        return service.getLowStockItems(restaurantId);
    }
}