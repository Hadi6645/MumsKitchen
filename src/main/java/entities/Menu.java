package entities;

import java.util.List;
import entities.Drink;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column
    @ElementCollection(targetClass=Meal.class)
	List<Meal> meals;
	
	@Column
    @ElementCollection(targetClass=Meal.class)
	List<Drink> drinks;
	@Column
    @ElementCollection(targetClass=Dessert.class)
	List<Dessert> desserts;

public Menu(List<Meal> meals,List<Drink> drinks,List<Dessert> desserts) {
	this.meals = meals;
	this.drinks = drinks;
	this.desserts = desserts;
}

public List<Meal> getmeals()
{
	return meals;
}
public List<Drink> getdrinks()
{
	return drinks;
}
public List<Dessert> getdesserts()
{
	return desserts;
}
}