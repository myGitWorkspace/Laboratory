package ua.deliveryservice.domain;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {
/*
	@Test(expected = IllegalArgumentException.class)
	public void passNullValueArgumentInSetPizzasMethodToRaiseException() {
		Customer c = mock(Customer.class);
		ArrayList<Pizza> a = mock(ArrayList.class);
		Order order = new Order(c, a);
		order.setPizzas(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void passLimitNumberOfPizzasInSetPizzasMethodToRaiseException() {
		Customer c = mock(Customer.class);
		ArrayList<Pizza> a = mock(ArrayList.class);
		ArrayList<Pizza> array = mock(ArrayList.class);
		when(array.size()).thenReturn(20);
		Order order = new Order(c, a);
		order.setPizzas(array);
	}
	
	@Test
	public void passAcceptableNumberOfPizzasInSetPizzasMethod() {
		Customer c = mock(Customer.class);
		ArrayList<Pizza> a = mock(ArrayList.class);				
		Order order = new Order(c, a);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		order.setPizzas(array);
		assertEquals(order.getPizzas().size(), 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void passNullValueArgumentInAddPizzaMethodToRaiseException() {
		Customer c = mock(Customer.class);
		ArrayList<Pizza> a = mock(ArrayList.class);
		Order order = new Order(c, a);
		order.addPizza(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void passLimitNumberOfPizzasInAddPizzaMethodToRaiseException() {
		Customer c = mock(Customer.class);
		ArrayList<Pizza> a = mock(ArrayList.class);		
		when(a.size()).thenReturn(20);
		Order order = new Order(c, a);
		order.addPizza(new Pizza(1, "", null, 3.));
	}
	
	@Test
	public void passAcceptableNumberOfPizzasInAddPizzaMethod() {
		Customer c = mock(Customer.class);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		Order order = new Order(c, array);
		order.addPizza(new Pizza(1, "", null, 3.));
		assertEquals(order.getPizzas().size(), 2);
	}
	
	@Test
	public void testCalculationOfTotalPriceForOrder() {
		Customer c = mock(Customer.class);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		array.add(new Pizza(2, "", null, 4.));
		array.add(new Pizza(3, "", null, 5.));
		Order order = new Order(c, array);
		assertEquals((Double)order.getOrderTotalPrice(), (Double)12.);
	}
	
	@Test
	public void testMakingDiscountForOrder() {
		Customer c = mock(Customer.class);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		array.add(new Pizza(2, "", null, 4.));
		array.add(new Pizza(3, "", null, 5.));
		array.add(new Pizza(4, "", null, 6.));
		array.add(new Pizza(5, "", null, 7.));
		Order order = new Order(c, array);
		order.makeDiscount();
		assertEquals((Double)order.getPizzas().get(0).getPrice(), (Double)4.9);
	}
	
	@Test
	public void testInsufficientNumberOfPizzasForOrderDiscount() {
		Customer c = mock(Customer.class);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		array.add(new Pizza(2, "", null, 4.));
		array.add(new Pizza(3, "", null, 5.));
		array.add(new Pizza(4, "", null, 6.));
		Order order = new Order(c, array);
		order.makeDiscount();
		assertEquals((Double)order.getPizzas().get(0).getPrice(), (Double)3.);		
	}
	
	@Test
	public void testCustomerCardDiscount() {
		Customer c = new Customer(1,"",null);
		CustomerCard card = new CustomerCard();
		card.addNewOrderPrice(10.);
		c.setCustomerCard(card);
		Order order = new Order(c, null);
		assertTrue(order.useCustomerCard(7.) - 1.7 < 0.0001);
	}
	
	@Test
	public void testCustomerCardDiscountWithLimit() {
		Customer c = new Customer(1,"",null);
		CustomerCard card = new CustomerCard();
		card.addNewOrderPrice(20.);
		c.setCustomerCard(card);
		Order order = new Order(c, null);
		assertTrue(order.useCustomerCard(7.) - 2.1 < 0.0001);
	}
	
	@Test
	public void testIfEmptyCustomerCard() {
		Customer c = new Customer(1,"",null);
		Order order = new Order(c, null);
		assertEquals((Double)order.useCustomerCard(7.), (Double)0.);
	}
	
	@Test
	public void testOrderFinalPriceWithPizzaDiscount() {
		Customer c = mock(Customer.class);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		array.add(new Pizza(2, "", null, 4.));
		array.add(new Pizza(3, "", null, 5.));
		array.add(new Pizza(4, "", null, 6.));
		array.add(new Pizza(5, "", null, 7.));
		Order order = new Order(c, array);
		order.process();
		order.process();
		assertEquals((Double)order.getFinalPrice(), (Double)22.9);
	}
	*/
	@Test
	public void testOrderFinalPriceWithCardDiscount() {
		User c = new User(1,"",null);
		CustomerCard card = new CustomerCard();
		card.addNewOrderPrice(10.);
		c.setCustomerCard(card);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza(1, "", null, 3.));
		Order order = new Order(c, array);
		order.process();
		order.process();
		assertEquals((Double)order.getPrice(), (Double)2.);
	}
	
	@Test
	public void testOrderFinalPriceWithCardAndWithPizzaDiscount() {
		
	}
	
}
