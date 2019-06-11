package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 200)
    private UUID uuid;

    @Column(name = "RESTAURANT_NAME")
    @Size(max = 50)
    private String restaurant_name;

    @Column(name = "PHOTO_URL")
    @Size(max = 255)
    private String photo_url;

    @Column(name = "RESTAURANT_RATING")
    private Float resturant_rating;

    @Column(name = "AVERAGE_PRICE_FOR_TWO")
    private Integer average_price_for_two;

    @Column(name = "NUMBER_OF_CUSTOMERS_RATED")
    private Integer num_customers_rated;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Float getResturant_rating() {
        return resturant_rating;
    }

    public void setResturant_rating(Float resturant_rating) {
        this.resturant_rating = resturant_rating;
    }

    public Integer getAverage_price_for_two() {
        return average_price_for_two;
    }

    public void setAverage_price_for_two(Integer average_price_for_two) {
        this.average_price_for_two = average_price_for_two;
    }

    public Integer getNum_customers_rated() {
        return num_customers_rated;
    }

    public void setNum_customers_rated(Integer num_customers_rated) {
        this.num_customers_rated = num_customers_rated;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
