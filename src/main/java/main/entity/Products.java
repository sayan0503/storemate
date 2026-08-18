package main.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table
public class Products {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int quantity;
	
	@Column
	private BigDecimal costPrice;
	
	@Column
	private BigDecimal sellPrice;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Stores store;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products( String name, int quantity, BigDecimal costPrice, BigDecimal sellPrice, Stores store) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.store = store;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}
	
	
}
