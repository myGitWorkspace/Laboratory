package ua.postgresconnect.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pizza")
public class Pizza {
	
	public enum PizzaType {
		CHEESE, SEA, FRESH;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected Integer id;
	
	@Column(name="name", length=50, nullable=false)
	protected String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type", length=50, nullable=false)
	protected PizzaType type;
	
	@Column(name="price")
	protected Double price;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="orderPizzaId.pizza")
	private List<OrderPizza> orderPizza = new ArrayList<>();
	
	/*
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="pizzas", cascade={CascadeType.ALL})
	private List<Order> orders = new ArrayList<>();
	*/ 
	
	public Pizza() {
		
	}

	public Pizza(String name, PizzaType type, Double price) {				
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<OrderPizza> getOrderPizza() {
		return orderPizza;
	}

	public void setOrderPizza(List<OrderPizza> orderPizza) {
		this.orderPizza = orderPizza;
	}

	/*
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	*/
	
}
