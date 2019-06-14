package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantDao {

    @PersistenceContext
    EntityManager entityManager;


    public List<RestaurantEntity> getAllRestaurants(){
        Query query = entityManager.createQuery("select re from RestaurantEntity re order by re.restaurant_name");
        return new ArrayList<RestaurantEntity>(query.getResultList());
    }
}
