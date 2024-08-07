package com.react.restaurant.demo.repository;

import com.react.restaurant.demo.model.Restaurant;
import com.react.restaurant.demo.repository.rowMapper.RestaurantRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class RestaurantRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RestaurantRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer saveRestaurant(Restaurant restaurant){
        String sql = "INSERT INTO restaurant " + "(name, address, phone_number) " + "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getPhoneNumber());
            return ps;
        }, keyHolder);

        // Set the generated ID back to the restaurant object
        restaurant.setId(keyHolder.getKey().intValue());

        return restaurant.getId();
    }

    public Restaurant getRestaurantByName(String restaurantName){
        String sql = "SELECT * FROM restaurant WHERE name = ?";
        Restaurant restaurant = jdbcTemplate.queryForObject(sql, new Object[]{restaurantName}, new RestaurantRowMapper());
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        String sql = "SELECT * FROM restaurant";
        return jdbcTemplate.query(sql, new RestaurantRowMapper());
    }

    
}
