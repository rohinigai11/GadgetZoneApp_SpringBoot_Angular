package com.gadgetzone;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	    
	    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

	    @Autowired
	    private ItemRepository repository;

	    // Create Item
	    public Item saveItem(Item item) {
	        logger.debug("Attempting to save item with email: {}", item.getItemId());
	        Item savedItem = repository.save(item);
	        logger.debug("Successfully saved item with ID: {}", savedItem.getItemId());
	        return savedItem;
	    }

	    // Get All Item
	    public List<Item> getAllItems() {
	        logger.debug("Retrieving all Items");
	        List<Item> items = repository.findAll();
	        logger.debug("Found {} items", items.size());
	        return items;
	    }

	    // Get Item by ID
	    public Optional<Item> getItemById(Integer id) {
	        logger.debug("Retrieving item with ID: {}", id);
	        Optional<Item> item = repository.findById(id);
	        if (item.isPresent()) {
	            logger.debug("Found item with ID: {}", id);
	        } else {
	            logger.debug("No item found with ID: {}", id);
	        }
	        return item;
	    }

	    // Get Item by name
	    public Optional<Item> getItemByName(String name) {
	        logger.debug("Retrieving item with email: {}", name);
	        Optional<Item> item = repository.findByName(name);
	        if (item.isPresent()) {
	            logger.debug("Found item with name: {}", name);
	        } else {
	            logger.debug("No item found with name: {}", name);
	        }
	        return item;
	    }

	    // Update Item
	    public Item updateItem(Integer id, Item updatedItem) {
	        logger.debug("Attempting to update item with ID: {}", id);
	        return repository.findById(id)
	                .map(item -> {
	                    logger.debug("Found item with ID: {}, updating details", id);
	                    item.setName(updatedItem.getName());
	                    item.setCategory(updatedItem.getCategory());
	                    item.setBrandName(updatedItem.getBrandName());
	                    item.setPrice(updatedItem.getPrice());
	                    Item savedItem = repository.save(item);
	                    logger.debug("Successfully updated item with ID: {}", id);
	                    return savedItem;
	                })
	                .orElseThrow(() -> {
	                    logger.debug("Failed to update - item not found with ID: {}", id);
	                    return new RuntimeException("Item not found with ID: " + id);
	                });
	    }

	    // Delete Item
	    public void deleteItem(Integer id) {
	        logger.debug("Attempting to delete item with ID: {}", id);
	        repository.deleteById(id);
	        logger.debug("Successfully deleted item with ID: {}", id);
	    }
}
