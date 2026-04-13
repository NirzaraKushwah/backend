package com.restaurant_service.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant_service.entity.Restaurant;
import com.restaurant_service.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants(){
        return repository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updated) {
        Restaurant existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setLocation(updated.getLocation());
            existing.setCuisineType(updated.getCuisineType());
            existing.setContactNumber(updated.getContactNumber());
            existing.setStatus(updated.getStatus());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteRestaurant(Long id) {
        repository.deleteById(id);
    }
}
