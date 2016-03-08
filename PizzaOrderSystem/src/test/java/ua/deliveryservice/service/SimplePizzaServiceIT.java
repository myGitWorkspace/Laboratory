package ua.deliveryservice.service;

import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/appContext.xml",
		"classpath:/repositoryContext.xml"
})
@ActiveProfiles(profiles="prod")
@Transactional
@Rollback
public class SimplePizzaServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Test
	public void testFind() {
		System.out.println("find");
		Integer id = null;
		final String sql = "insert into pizza(name, type, price) values ('name', 'FRESH', 1.0)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		},keyHolder);
				
		id = (Integer)keyHolder.getKeys().get("id");
		
		Pizza result = pizzaService.find(id);
		System.out.println(result);
		assertNotNull(result);
		
	}
	
	@Test
	public void testSave() {
		System.out.println("save");
		Pizza pizza  = new Pizza();
		pizza.setName("Marg");
		pizza.setType(Pizza.PizzaType.CHEESE);
		pizza.setPrice(2.);
		
		Pizza result = pizzaService.save(pizza);
		em.flush();
		System.out.println(result.getId());
		assertNotNull(result.getId());
	}
	
	@Test
	public void testFindAll() {
		
		final String sql = "delete from pizza";
		
		jdbcTemplate.execute(sql);
		
		Pizza pizza1  = new Pizza();
		pizza1.setName("Name1");
		pizza1.setType(Pizza.PizzaType.CHEESE);
		pizza1.setPrice(2.);
		Pizza pizza2  = new Pizza();
		pizza2.setName("Name2");
		pizza2.setType(Pizza.PizzaType.CHEESE);
		pizza2.setPrice(3.);
		Pizza result1 = pizzaService.save(pizza1);
		Pizza result2 = pizzaService.save(pizza2);
		List<Pizza> list = pizzaService.findAll();
		assertEquals(result1, list.get(0));
		assertEquals(result2, list.get(1));
	}
	
	@Test
	public void testDelete() {
		Pizza pizza1  = new Pizza();
		pizza1.setName("Name1");
		pizza1.setType(Pizza.PizzaType.CHEESE);
		pizza1.setPrice(2.);
		Pizza result1 = pizzaService.save(pizza1);
		assertEquals(result1, pizzaService.find(result1.getId()));
		pizzaService.delete(result1);
		Assert.assertNull(pizzaService.find(result1.getId()));
	}
}
