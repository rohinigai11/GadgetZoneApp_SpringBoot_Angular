package com.gadgetzone;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/items")
public class ItemController {
    
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService service;

    // Register Item
    @PostMapping("/register")
    public ResponseEntity<Item> registerEmployee(@RequestBody Item item) {
        logger.debug("Received request to register item with email: {}", item.getItemId());
        Item savedItem = service.saveItem(item);
        logger.debug("Successfully registered employee with ID: {}", savedItem.getItemId());
        return ResponseEntity.ok(savedItem);
    }

    // Get All Items
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        logger.debug("Received request to get all items");
        List<Item> items = service.getAllItems();
        logger.debug("Returning {} employees", items.size());
        return ResponseEntity.ok(items);
    }

    // Get Item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Integer id) {
        logger.debug("Received request to get item with ID: {}", id);
        Optional<Item> item = service.getItemById(id);
        if (item.isPresent()) {
            logger.debug("Returning item with ID: {}", id);
            return ResponseEntity.ok(item.get());
        } else {
            logger.debug("Item with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Get item by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Item> getItemByName(@PathVariable String name) {
        logger.debug("Received request to get item with email: {}", name);
        Optional<Item> item = service.getItemByName(name);
        if (item.isPresent()) {
            logger.debug("Returning item with email: {}", name);
            return ResponseEntity.ok(item.get());
        } else {
            logger.debug("Item with email: {} not found", name);
            return ResponseEntity.notFound().build();
        }
    }

    // Update Item
    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Integer id, @RequestBody Item item) {
        logger.debug("Received request to update item with ID: {}", id);
        Item updatedItem = service.updateItem(id, item);
        logger.debug("Successfully updated item with ID: {}", id);
        return ResponseEntity.ok(updatedItem);
    }

    // Delete Item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        logger.debug("Received request to delete item with ID: {}", id);
        service.deleteItem(id);
        logger.debug("Successfully deleted item with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

}
