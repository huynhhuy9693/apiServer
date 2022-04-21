package com.fpt.main.entity;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order extends BaseEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
	@Column(name = "order_tracking_number")
	private String orderTrackingNumber;
	
    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;
    
    @Column(name="ship_money")
    private BigDecimal shippingMoney;
	
    @Column(name="status")
    private String status;
	
	@Column(name = "offer_code")
	private String offerCode;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private Set<OrderItem> orderItem = new HashSet<>();
	
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id")
	private Address shippingAddress;
	
	public void add(OrderItem item) {
		
		if (item != null) {
			if(orderItem == null) {
				orderItem = new HashSet<>();
			}
		}		
		orderItem.add(item);
		item.setOrder(this);
	}
	
}
