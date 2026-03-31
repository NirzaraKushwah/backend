package com.restaurant_service.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restaurant_service.entity.Restaurant;
import com.restaurant_service.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @PostMapping("/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.addRestaurant(restaurant);
    }

    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants(){
        return service.getAllRestaurants();
    }
}
