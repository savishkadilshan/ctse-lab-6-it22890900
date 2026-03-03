package com.sliit.itemservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    // Simple in-memory list
    private List<String> items = new ArrayList<>(List.of("Book", "Laptop", "Phone"));

    @GetMapping
    public List<String> getItems() {
        return items;
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody String item) {
        items.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added: " + item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getItemById(@PathVariable int id) {

        if (id < 0 || id >= items.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item not found with id: " + id);
        }

        return ResponseEntity.ok(items.get(id));
    }
}