package main.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table
public class Sales {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDateTime saleDate;
	
	@Column
	private BigDecimal totalAmount;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Stores store;

	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sales(LocalDateTime saleDate, BigDecimal totalAmount, Stores store) {
		super();
		this.saleDate = saleDate;
		this.totalAmount = totalAmount;
		this.store = store;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
