package com.restaurant.repository;



import com.restaurant.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByRestaurantId(Long restaurantId);

}