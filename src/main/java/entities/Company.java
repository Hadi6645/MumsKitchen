package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	
	@Column
    @ElementCollection(targetClass=Restaurant.class)
	List<Restaurant> Restaurants;
	
	private Employee CEO;
	private Employee Dietitian;
	public Company ()
	{
		
	}
	
	public Company (Employee CEO,Employee Dietitian ) {
		this.CEO = CEO;
		this.Dietitian = Dietitian;
		Restaurants = new ArrayList<Restaurant>();
	}
	
	public Company (List<Restaurant> restaurants) {
		Restaurants = restaurants;
	}
	
	public void AddRestaurant( Restaurant restaurant) {
		Restaurants.add(restaurant);
	}
	
	public Employee getDietitian() {
		return this.Dietitian;
	}
	
	public Employee getCEO() {
		return this.CEO;
	}
	
   public List<Restaurant> getRestaurants(){
		return Restaurants;
	}
}
