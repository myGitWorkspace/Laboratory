package ua.deliveryservice.service;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import ua.deliveryservice.repository.PizzaRepository;
import ua.deliveryservice.service.PizzaService;
import ua.deliveryservice.service.SimplePizzaService;

public class SimplePizzaServiceTest {

	@Test
	public void testFindMethod() {
		PizzaRepository pizzaRepository = mock(PizzaRepository.class);
		PizzaService pizzaService = new SimplePizzaService(pizzaRepository);
		Integer id = 1;
		pizzaService.find(id);
		verify(pizzaRepository, times(1)).find(eq(id));
	}
}
