package com.fpt.main.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="order_item")
@Getter
@Setter
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="image_url")
    private String imageUrl;
	
    @Column(name="unit_price")
    private BigDecimal unitPrice;
	
	@Column(name = "original_price")
	private BigDecimal originalPrice;
	
	@Column(name = "selling_price")
	private BigDecimal sellingPrice;
	
    @Column(name="quantity")
    private int quantity;
    
	@Column(name = "dishes_id")
	private Long dishesId;
	
	@ManyToOne(cascade = { CascadeType.MERGE , CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "order_id")
	private Order order;

}
