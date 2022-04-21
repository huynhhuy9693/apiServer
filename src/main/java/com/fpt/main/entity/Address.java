package com.fpt.main.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Long id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "longtitude")
	private Double longtitude;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "cities")
	private String cities;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "lane")
	private String lane;
	
	@OneToOne
    @PrimaryKeyJoinColumn
	private Restaurant restaurant;
	
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	private Collection<User> users;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Order order;
	
	public String getFullAddress() {	
		return String.format("%s, %s, %s ",street, ward, district);
		
	}
	
}





