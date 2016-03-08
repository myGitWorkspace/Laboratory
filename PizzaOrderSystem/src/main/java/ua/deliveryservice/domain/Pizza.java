package ua.deliveryservice.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="pizza")
@NamedQueries({
@NamedQuery(name="Pizza.findAll",
    query="SELECT p FROM Pizza p")          
})
public class Pizza {
	
	public enum PizzaType {
		CHEESE, SEA, FRESH;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected Integer id;
	
	@Column(name="name", length=50, nullable=false)
	protected String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type", length=50, nullable=false)
	protected PizzaType type;
	
	@Column(name="price", nullable=false)
	protected Double price;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="orderPizzaId.pizza")
	private List<OrderPizza> orderPizza = new ArrayList<>();
	
	public Pizza() {
		
	}
	
	public Pizza(String name, PizzaType type, Double price) {		
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	public Pizza(Integer id, String name, PizzaType type, Double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}

	public Double getPrice() {		
		DecimalFormat df = new DecimalFormat("#.##"); 
		return Double.valueOf(df.format(this.price));		
		//return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", type=" + type
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((orderPizza == null) ? 0 : orderPizza.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderPizza == null) {
			if (other.orderPizza != null)
				return false;
		} else if (!orderPizza.equals(other.orderPizza))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	

}
