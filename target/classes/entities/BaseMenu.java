package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class BaseMenu extends Menu{
	public BaseMenu(List<Meal> meals,List<Drink> drinks,List<Dessert> desserts)
	{
		super(meals,drinks,desserts);
	}
}
