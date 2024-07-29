package com.react.restaurant.demo.service;

import com.react.restaurant.demo.model.Restaurant;
import com.react.restaurant.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    public Integer createRestaurant(Restaurant restaurant){
        Integer restaurantId = restaurantRepository.saveRestaurant(restaurant);
        return restaurantId;
    }

    public Restaurant getRestaurantByName(String restaurantName){
        Restaurant restaurant = restaurantRepository.getRestaurantByName(restaurantName);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
        return restaurants;
    }
}
