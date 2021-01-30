package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class BaseMenu extends Menu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BaseMenu() {
		super();
	}
	public BaseMenu(List<Meal> meals,List<Drink> drinks,List<Dessert> desserts)
	{
		super(meals,drinks,desserts);
	}
}
