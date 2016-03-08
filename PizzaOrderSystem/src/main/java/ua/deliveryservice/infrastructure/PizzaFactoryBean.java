package ua.deliveryservice.infrastructure;

import org.springframework.beans.factory.FactoryBean;

import ua.deliveryservice.domain.Pizza;

public class PizzaFactoryBean implements FactoryBean<Pizza> {

	private Integer id;
	private String name;
	private Pizza.PizzaType type;
	private Double price;
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Pizza.PizzaType type) {
		this.type = type;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public Pizza getObject() throws Exception {
		return new Pizza(id, name, type, price);
	}
	
	@Override
	public Class<?> getObjectType() {
		return Pizza.class;
	}
	
	@Override
	public boolean isSingleton() {
		return true;
	}
}
