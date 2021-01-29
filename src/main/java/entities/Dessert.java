package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Dessert extends Meal {
	public Dessert() {
		super();
	}
	public Dessert(String name, String description,double price,List<Ingredients>  ingredient,List<Ingredients>  ingredients)
	{
		super(name,description,price,ingredient,ingredients);
	}
}
