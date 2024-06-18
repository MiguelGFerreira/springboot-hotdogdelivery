package com.miguelgferreira.springboot_hotdogdelivery.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(min = 2, max = 300, message = "Name must have between {min} and {max} characters")
	private String name;

	@NotNull
	@Length(min = 2, max = 300, message = "Address must have between {min} and {max} characters")
	private String address;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<HotDogOrder> orders;

	public Client(Long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Client() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<HotDogOrder> getOrders() {
		return orders;
	}

	public void newOrder(HotDogOrder order) {
		if (this.orders == null)
			orders = new ArrayList<HotDogOrder>();
		orders.add(order);
	}
}
