package ua.deliveryservice.service;

import org.junit.Test;

import ua.deliveryservice.domain.User;
import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.repository.OrderRepository;
import ua.deliveryservice.service.SimpleOrderService;
import ua.deliveryservice.service.OrderService;
import ua.deliveryservice.service.PizzaService;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SimpleOrderServiceTest {
	
	@Test
	public void testOrderRepositoryInPlacingNewOrder() {
		
		OrderRepository orderRepository = mock(OrderRepository.class);
		PizzaService pizzaService = mock(PizzaService.class);
		OrderService orderService = new SimpleOrderService(orderRepository, pizzaService);
		User customer = mock(User.class);
		Integer id = 1;		
		orderService.placeNewOrder(customer, id);
		verify(orderRepository, times(1)).save(isA(Order.class));
	}
	
	@Test
	public void testPizzaServiceInPlacingNewOrder() {
		
		OrderRepository orderRepository = mock(OrderRepository.class);
		PizzaService pizzaService = mock(PizzaService.class);
		OrderService orderService = new SimpleOrderService(orderRepository, pizzaService);
		User customer = mock(User.class);
		Integer id = 1;		
		orderService.placeNewOrder(customer, id);
		verify(pizzaService, times(1)).find(eq(id));
	}
	
	@Test
	public void testPizzaServiceWithFewParamsInPlacingNewOrder() {
		
		OrderRepository orderRepository = mock(OrderRepository.class);
		PizzaService pizzaService = mock(PizzaService.class);
		OrderService orderService = new SimpleOrderService(orderRepository, pizzaService);
		User customer = mock(User.class);
		Integer[] ids = new Integer[]{1,2,3};		
		orderService.placeNewOrder(customer, ids);
		verify(pizzaService, times(3)).find(isA(Integer.class));
		verify(pizzaService, times(1)).find(ids[0]);
		verify(pizzaService, times(1)).find(ids[1]);
		verify(pizzaService, times(1)).find(ids[2]);
	}
}
