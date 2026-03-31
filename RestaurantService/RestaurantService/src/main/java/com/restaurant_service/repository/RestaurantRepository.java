package com.restaurant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant_service.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{

}
