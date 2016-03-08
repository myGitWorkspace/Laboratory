package ua.postgresconnect;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.postgresconnect.domain.Customer;
import ua.postgresconnect.domain.Order;
import ua.postgresconnect.domain.OrderPizza;
import ua.postgresconnect.domain.Pizza;


public class App 
{
    public static void main( String[] args ) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
 
		Customer c = new Customer("Name1");
		Order o1 = new Order(c);
		
		Pizza p1 = new Pizza("pizza1",Pizza.PizzaType.CHEESE, 1.);		

		session.save(p1);
		
		OrderPizza op = new OrderPizza();
		op.setPrice(p1.getPrice());
		op.setOrder(o1);
		op.setPizza(p1);
		
		o1.getOrderPizza().add(op);
		session.save(o1);		
				
		session.getTransaction().commit();
		session.close();
    }
}
