package ua.postgresconnect.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="order_pizza")
@AssociationOverrides({
	@AssociationOverride(name="orderPizzaId.order", joinColumns={@JoinColumn(name="order_id")}),
	@AssociationOverride(name="orderPizzaId.pizza", joinColumns={@JoinColumn(name="pizza_id")})
})
public class OrderPizza {

	@EmbeddedId
	private OrderPizzaId orderPizzaId = new OrderPizzaId();
	
	@Column(name="price")
	private Double price;
	
	public OrderPizza() {
		
	}

	public OrderPizzaId getOrderPizzaId() {
		return orderPizzaId;
	}

	public void setOrderPizzaId(OrderPizzaId orderPizzaId) {
		this.orderPizzaId = orderPizzaId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Order getOrder() {
		return orderPizzaId.getOrder();
	}
	
	public void setOrder(Order order) {
		orderPizzaId.setOrder(order);
	}
	
	public Pizza getPizza() {
		return orderPizzaId.getPizza();
	}
	
	public void setPizza(Pizza pizza) {
		orderPizzaId.setPizza(pizza);
	}
	
}
