package com.react.restaurant.demo.controller;

import com.react.restaurant.demo.model.Restaurant;
import com.react.restaurant.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/createRestaurant")
    public ResponseEntity<Integer> createRestaurant(@RequestBody Restaurant restaurant) {
        try {
            Integer restaurantId = restaurantService.createRestaurant(restaurant);
            return new ResponseEntity<>(restaurantId, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("restaurant/createRestaurant " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("getRestaurantByName/{restaurantName}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String restaurantName) {
        try{
            Restaurant restaurant = restaurantService.getRestaurantByName(restaurantName);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch(Exception e){
            System.out.println("restaurant/getRestaurantByName " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllRestaurants")
    public ResponseEntity<List<Restaurant>> getRestaurantByName() {
        try{
            List<Restaurant> restaurants = restaurantService.getAllRestaurants();
            return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
        } catch(Exception e){
            System.out.println("restaurant/getRestaurantByName " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
