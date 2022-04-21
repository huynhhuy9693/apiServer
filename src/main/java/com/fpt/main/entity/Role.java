package com.fpt.main.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
	public Role() {}
	
	public Role(ERole name) {
		this.name = name;
	}	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "user_roles", 
				joinColumns = @JoinColumn(name= "role_id"), 
				inverseJoinColumns= @JoinColumn(name="user_id"))
	private Set<User> user = new HashSet<User>();
	
}