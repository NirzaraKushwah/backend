package com.restaurant.service;


import com.restaurant.entity.Item;
import com.restaurant.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repo;
    private final EmailService emailService;

    public ItemService(ItemRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    public Item saveItem(Item item) {

        Item savedItem = repo.save(item);

        if(savedItem.getQuantity() <= savedItem.getThreshold()){

            emailService.sendLowStockAlert(
                    "manager@restaurant.com",
                    savedItem
            );
        }

        return savedItem;
    }

    public List<Item> getAllItems() {
        return repo.findAll();
    }

    public List<Item> getLowStockItems(Long restaurantId) {

        return repo.findAll()
                .stream()
                .filter(item ->
                        item.getRestaurantId().equals(restaurantId)
                                && item.getQuantity() <= item.getThreshold())
                .toList();
    }

}
