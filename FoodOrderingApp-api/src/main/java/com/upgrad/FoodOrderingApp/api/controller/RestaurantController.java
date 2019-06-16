package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.business.CategoryBusinessService;
import com.upgrad.FoodOrderingApp.service.business.RestaurantBusinessService;
import com.upgrad.FoodOrderingApp.service.business.RestaurantCategoryBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantCategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantBusinessService restaurantBusinessService;

    @Autowired
    private RestaurantCategoryBusinessService restaurantCategoryBusinessService;

    @Autowired
    private CategoryBusinessService categoryBusinessService;

    @RequestMapping(method = RequestMethod.GET,path = "/restaurant",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantListResponse>> getAllRestaurants()  {

        List<RestaurantListResponse> restList = new ArrayList<>();
        final List<RestaurantEntity> allRestaurants = restaurantBusinessService.getAllRestaurants();

        for(RestaurantEntity restaurantEntity : allRestaurants){
            String allCategoriesLinkedToRestaurant = restaurantBusinessService.getCategoriesLinkedToRestaurant(restaurantEntity.getId());
            RestaurantList restaurantList = new RestaurantList().id(UUID.fromString(restaurantEntity.getUuid())).restaurantName(restaurantEntity.getRestaurantName()).photoURL(restaurantEntity.getPhotoURL())
                    .customerRating(restaurantEntity.getCustomeRating()).averagePrice(restaurantEntity.getAvgPriceForTwo()).numberCustomersRated(restaurantEntity.getNumbrOfCustomersRated())
                    .address(
                        new RestaurantDetailsResponseAddress().id(restaurantEntity.getAddressEntity().getUuid()).city(restaurantEntity.getAddressEntity().getCity()).flatBuildingName(restaurantEntity.getAddressEntity().getFlat_buil_number())
                            .locality(restaurantEntity.getAddressEntity().getLocality()).pincode(restaurantEntity.getAddressEntity().getPincode())
                            .state(new RestaurantDetailsResponseAddressState().id(restaurantEntity.getAddressEntity().getStateEntity().uuid).stateName(restaurantEntity.getAddressEntity().getStateEntity().getState_name()))

                    )
                    .categories(allCategoriesLinkedToRestaurant);
            RestaurantListResponse restaurantListResponse = new RestaurantListResponse().addRestaurantsItem(restaurantList);
            restList.add(restaurantListResponse);
        }

        return new ResponseEntity<List<RestaurantListResponse>>(restList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/restaurant/name/{reastaurant_name}",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantListResponse>> getAllRestaurantsByName(@PathVariable("reastaurant_name") String restaurantName) throws RestaurantNotFoundException {

        String resNameKey = "%"+restaurantName+"%";
        List<RestaurantListResponse> restList = new ArrayList<>();
        final List<RestaurantEntity> allRestaurantsByName = restaurantBusinessService.getAllRestaurantsByName(resNameKey);

        for(RestaurantEntity restaurantEntity : allRestaurantsByName){
            String allCategoriesLinkedToRestaurant = restaurantBusinessService.getCategoriesLinkedToRestaurant(restaurantEntity.getId());
            RestaurantList restaurantList = new RestaurantList().id(UUID.fromString(restaurantEntity.getUuid())).restaurantName(restaurantEntity.getRestaurantName()).photoURL(restaurantEntity.getPhotoURL())
                    .customerRating(restaurantEntity.getCustomeRating()).averagePrice(restaurantEntity.getAvgPriceForTwo()).numberCustomersRated(restaurantEntity.getNumbrOfCustomersRated())
                    .address(
                            new RestaurantDetailsResponseAddress().id(restaurantEntity.getAddressEntity().getUuid()).city(restaurantEntity.getAddressEntity().getCity()).flatBuildingName(restaurantEntity.getAddressEntity().getFlat_buil_number())
                                    .locality(restaurantEntity.getAddressEntity().getLocality()).pincode(restaurantEntity.getAddressEntity().getPincode())
                                    .state(new RestaurantDetailsResponseAddressState().id(restaurantEntity.getAddressEntity().getStateEntity().uuid).stateName(restaurantEntity.getAddressEntity().getStateEntity().getState_name()))

                    )
                    .categories(allCategoriesLinkedToRestaurant);
            RestaurantListResponse restaurantListResponse = new RestaurantListResponse().addRestaurantsItem(restaurantList);
            restList.add(restaurantListResponse);
        }

        return new ResponseEntity<List<RestaurantListResponse>>(restList,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET,path = "/restaurant/category/{category_id}",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantListResponse>> getAllRestaurantsByCategory(@PathVariable("category_id") String categoryUUID) throws CategoryNotFoundException {

        List<RestaurantListResponse> restList = new ArrayList<>();
        CategoryEntity categoryByUUID = categoryBusinessService.getCategoryByUUID(categoryUUID);
        List<RestaurantEntity> allRestaurantsByCategory = restaurantBusinessService.getAllRestaurantsByCategory(categoryByUUID.getId());

        for(RestaurantEntity restaurantEntity : allRestaurantsByCategory){
            String allCategoriesLinkedToRestaurant = restaurantBusinessService.getCategoriesLinkedToRestaurant(restaurantEntity.getId());
            RestaurantList restaurantList = new RestaurantList().id(UUID.fromString(restaurantEntity.getUuid())).restaurantName(restaurantEntity.getRestaurantName()).photoURL(restaurantEntity.getPhotoURL())
                    .customerRating(restaurantEntity.getCustomeRating()).averagePrice(restaurantEntity.getAvgPriceForTwo()).numberCustomersRated(restaurantEntity.getNumbrOfCustomersRated())
                    .address(
                            new RestaurantDetailsResponseAddress().id(restaurantEntity.getAddressEntity().getUuid()).city(restaurantEntity.getAddressEntity().getCity()).flatBuildingName(restaurantEntity.getAddressEntity().getFlat_buil_number())
                                    .locality(restaurantEntity.getAddressEntity().getLocality()).pincode(restaurantEntity.getAddressEntity().getPincode())
                                    .state(new RestaurantDetailsResponseAddressState().id(restaurantEntity.getAddressEntity().getStateEntity().uuid).stateName(restaurantEntity.getAddressEntity().getStateEntity().getState_name()))

                    )
                    .categories(allCategoriesLinkedToRestaurant);
            RestaurantListResponse restaurantListResponse = new RestaurantListResponse().addRestaurantsItem(restaurantList);
            restList.add(restaurantListResponse);
        }

        return new ResponseEntity<List<RestaurantListResponse>>(restList,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT,path = "/restaurant/{restaurant_id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantUpdatedResponse> updateRestaurantDetails(@PathVariable("restaurant_id") String restaurantUUID, @RequestHeader("authorization") final String authorization, @RequestParam("customerRating") final BigDecimal customerRating) throws AuthorizationFailedException,RestaurantNotFoundException {

        RestaurantEntity updatedRestaurantDetails = restaurantBusinessService.updateRestaurantDetails(authorization,restaurantUUID, customerRating);
        final RestaurantUpdatedResponse restaurantUpdatedResponse = new RestaurantUpdatedResponse().id(UUID.fromString(updatedRestaurantDetails.getUuid())).status("RESTAURANT RATING UPDATED SUCCESSFULLY");
        return new ResponseEntity<RestaurantUpdatedResponse>(restaurantUpdatedResponse,HttpStatus.OK);

    }
}