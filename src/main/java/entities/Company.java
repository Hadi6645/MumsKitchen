package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
    @ElementCollection(targetClass=Restaurant.class)
	List<Restaurant> Restaurants;
	
	@OneToOne
	private Employee ceo;
	@OneToOne
	private Employee dietitian;
	
	public Company ()
	{
		
	}
	
	
	public Company (Employee CEO,Employee Dietitian ) {
		this.ceo = CEO;
		this.dietitian = Dietitian;
		Restaurants = new ArrayList<Restaurant>();
	}
	
	public Company (List<Restaurant> restaurants) {
		Restaurants = restaurants;
	}
	
	public void AddRestaurant( Restaurant restaurant) {
		Restaurants.add(restaurant);
	}
	
	public Employee getDietitian() {
		return this.dietitian;
	}
	
	public Employee getCEO() {
		return this.ceo;
	}
	
   public List<Restaurant> getRestaurants(){
		return Restaurants;
	}
   
   public void setCEO(Employee CEO)
   {
	   this.ceo = CEO;
   }
   
   public void setDietitian(Employee dietitian)
   {
	   this.dietitian = dietitian;
   }
}
