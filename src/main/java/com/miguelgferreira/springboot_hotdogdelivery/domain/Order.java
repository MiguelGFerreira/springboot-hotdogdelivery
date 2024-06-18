package com.miguelgferreira.springboot_hotdogdelivery.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

import org.hibernate.annotations.CascadeType;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Client client;

	@ManyToAny
	@Cascade(CascadeType.MERGE)
	private List<Item> items;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;

	@Min(1)
	private Double totalValue;

	private String status;

	public Order() {
	}

	public Order(Long id, Client client, List<Item> items, Double totalValue) {
		super();
		this.id = id;
		this.client = client;
		this.items = items;
		this.date = new Date();
		this.totalValue = totalValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setData(Date date) {
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
