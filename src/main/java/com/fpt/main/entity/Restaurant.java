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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@NotBlank
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email")
	@Size(max = 50)
	@Email
	private String email;
	
	@Column(name = "rate_total", columnDefinition = "float default 0.0")
	private float rateTotal;
	
	@Column(name = "rate_count", columnDefinition = "int default 0")
	private int rateCount;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "img_url")
	private String imageURL;
	
	@Column(name = "is_active", columnDefinition = "Bit(1) default true")
	private boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "restaurant", fetch = FetchType.LAZY)
	private Set<Rating> ratingList = new HashSet<Rating>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.LAZY)
	private Set<Dishes> dishesList = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.LAZY)
	private Set<Offer> offerList = new HashSet<Offer>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToMany()
    @JoinTable(
    		name = "favourite",
	    	joinColumns = {
    				@JoinColumn(name="restaurant_id")
    		},
    		inverseJoinColumns = {
    				@JoinColumn(name="customer_id")
    		}
    )
	@JsonIgnore
    private Set<Customer> customerToFavou = new HashSet<>();
	
	public void addDishes(Dishes item) {		
		if (item != null) {
			if (dishesList == null) {
				dishesList = new HashSet<Dishes>();
			}
		}		
		dishesList.add(item);
		item.setRestaurant(this);
	}
	
	public void addOffer(Offer item) {
		if (item !=null) {
			offerList = new HashSet<Offer>();
		}		
		offerList.add(item);
		item.setRestaurant(this);
	}
	
	public void addRating(Rating item) {
		if (item != null) {
			if (ratingList == null) {
				ratingList = new HashSet<>();
			}
		}
		ratingList.add(item);
		item.setRestaurant(this);
	}
	
}
