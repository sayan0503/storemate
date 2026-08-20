package main.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class SaleItems {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int quantity;
	
	@Column
	private BigDecimal price;
	
	@Column
	private BigDecimal subTotal;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sales sale;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products product;

	public SaleItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleItems(int quantity, BigDecimal price, BigDecimal subTotal, Sales sale, Products product) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
		this.sale = sale;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
	
	
}
