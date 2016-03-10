package ua.deliveryservice.infrastructure;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import ua.deliveryservice.infrastructure.JavaConfig;
import ua.deliveryservice.repository.OrderRepository;

public class JavaConfigApplicationContextTest {

	@Test
	public void testConfigObjectInvocation() throws Throwable {
		
		Config conf = mock(Config.class);
		JavaConfigApplicationContext ctx = new JavaConfigApplicationContext(conf);
		String beanName = "beanName";
		when(conf.getImpl(beanName)).thenReturn(Object.class);
		ctx.getBean(beanName);
		verify(conf, times(1)).getImpl(eq(beanName));
	}
	
	@Test
	public void testGetBeanMakesSingletonObject() throws Throwable {
		
		Config conf = new JavaConfig();
		JavaConfigApplicationContext ctx = new JavaConfigApplicationContext(conf);
		String beanName = "orderRepository";		
		OrderRepository orderRepository1 = (OrderRepository)ctx.getBean(beanName);
		OrderRepository orderRepository2 = (OrderRepository)ctx.getBean(beanName);
		assertEquals(orderRepository1, orderRepository2);		
	}
	
	@Test
	public void testGetBeanReturnsProperObject() throws Throwable {
		
		Config conf = new JavaConfig();
		JavaConfigApplicationContext ctx = new JavaConfigApplicationContext(conf);
		String beanName = "orderRepository";		
		OrderRepository orderRepository = (OrderRepository)ctx.getBean(beanName);
		String ifcNameFirstLetterUpperCase = orderRepository.getClass().getInterfaces()[0].getSimpleName();
		String ifcNameFirstLetterLowerCase = String.valueOf(ifcNameFirstLetterUpperCase.charAt(0)).toLowerCase() + ifcNameFirstLetterUpperCase.substring(1,ifcNameFirstLetterUpperCase.length());
		assertEquals(beanName, ifcNameFirstLetterLowerCase);
		
	}
}
