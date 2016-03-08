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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="orders")
public class Order {
	
	private enum State{ONE,TWO}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Transient
	private OrderState currentState = new OrderState();

	@Enumerated(EnumType.STRING)
	@Column(name="state")
	private State state = State.ONE;
	
	@Transient
	private Double finalPrice;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	/*
	@ManyToMany(targetEntity=Pizza.class, cascade={CascadeType.ALL})
	@JoinTable(name="order_pizza",
			joinColumns={@JoinColumn(name="order_id")},
			inverseJoinColumns={@JoinColumn(name="pizza_id")})
	private List<Pizza> pizzas = new ArrayList<>();
	*/
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="orderPizzaId.order")
	private List<OrderPizza> orderPizza = new ArrayList<>();
	
	public Order() {
		
	}
	/*
	public Order(Customer customer, List<Pizza> pizzas) {				
		this.customer = customer;
		this.pizzas = pizzas;
	}
	*/
	public Order(Customer customer) {				
		this.customer = customer;		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public OrderState getCurrentState() {
		return currentState;
	}


	public void setCurrentState(OrderState currentState) {
		this.currentState = currentState;
	}


	public Double getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

/*
	public List<Pizza> getPizzas() {
		return pizzas;
	}


	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
*/
	public List<OrderPizza> getOrderPizza() {
		return this.orderPizza;
	}

	public void setPizzas(List<OrderPizza> orderPizza) {
		this.orderPizza = orderPizza;
	}

	private class OrderState {
		private String name="defaultValue!!!";

		@Override
		public String toString() {
			return "OrderState [name=" + name + "]";
		}
		
	}
}
