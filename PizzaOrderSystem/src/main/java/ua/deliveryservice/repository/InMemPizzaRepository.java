package ua.deliveryservice.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.annotations.Benchmark;
import ua.deliveryservice.annotations.PostCreate;
import ua.deliveryservice.domain.Pizza;

@Repository
public class InMemPizzaRepository implements PizzaRepository {

	private static final Map<Integer, Pizza> pizzas = new HashMap<>();
	
	@PostConstruct
	public void init() {
		pizzas.put(1, new Pizza(1, "PizzaName1", Pizza.PizzaType.CHEESE, 1.));
		pizzas.put(2, new Pizza(2, "PizzaName2", Pizza.PizzaType.FRESH, 2.));
		pizzas.put(3, new Pizza(3, "PizzaName3", Pizza.PizzaType.SEA, 3.));
		pizzas.put(4, new Pizza(4, "PizzaName4", Pizza.PizzaType.CHEESE, 4.));
		pizzas.put(5, new Pizza(5, "PizzaName5", Pizza.PizzaType.FRESH, 5.));
	}
	
	@Benchmark
	@PostCreate
	public void beforeCreate() {
		System.out.println("PostCreate");
	}
	
	@Benchmark
	@Override
	public Pizza find(Integer id) {		
		return pizzas.get(id);
	}
	
	@Override
	public Pizza save(Pizza pizza) {	
		return null;
	}
	
	@Override
	public void delete(Pizza pizza) {
	}
	
	@Override
	public List<Pizza> findAll() {
		return null;
	}
	
}
