package com.fpt.main.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class Offer extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "actived_date")
	private LocalDateTime activedDate;
	
	@Column(name = "expired_date")
	private LocalDateTime expiredDate;
	
	@Column(name = "discount_percent")
	private int discount;
	
	@Column(name = "price_promo")
	private BigDecimal pricePromo;
	
	@Column(name = "img_url")
	private String imageURL;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id") 
	private Restaurant restaurant;
	
	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private Set<Dishes> dishes = new HashSet<Dishes>();
	
	public void add(Dishes item) {

        if (item != null) {

            if (dishes == null) {
            	dishes = new HashSet<>();
            }

            dishes.add(item);
            item.setOffer(this);
        }
    }
}
