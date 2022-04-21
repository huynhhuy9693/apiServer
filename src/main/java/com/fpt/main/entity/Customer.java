package com.fpt.main.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer", 
	   uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rating> ratings = new HashSet<>();
    
    
    
    @ManyToMany()
    @JoinTable(
    		name = "favourite",
	    	joinColumns = {
    				@JoinColumn(name="customer_id")
    		},
    		inverseJoinColumns = {
    				@JoinColumn(name="restaurant_id")
    		}
    )
    private Set<Restaurant> restaurantFavou = new HashSet<Restaurant>();

    public void add(Order order) {

        if (order != null) {

            if (orders == null) {
                orders = new HashSet<>();
            }

            orders.add(order);
            order.setCustomer(this);
        }
    }
    
    public void addRating(Rating item) {
        if (item != null) {

            if (ratings == null) {
            	ratings = new HashSet<>();
            }

            ratings.add(item);
            item.setCustomer(this);
        }       
    }
    
    public void addRestaurantFavou(Restaurant item) {
        if (item != null) {

            if (restaurantFavou == null) {
            	restaurantFavou = new HashSet<>();
            }

            restaurantFavou.add(item);
        }       
    }
    
    public void removeRestaurantFavou(Restaurant item) {
        if (item != null) {
            restaurantFavou.remove(item);
            item.getCustomerToFavou().remove(this);
        }       
    }
    
}









