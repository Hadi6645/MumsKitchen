package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "ingredients")
public class Ingredients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ingredients_id;
	String name;
	
	@ManyToMany(mappedBy = "baseIngredients",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Meal.class
	)
	List<Food> baseFoodList;
	
	@ManyToMany(mappedBy = "optinalIngredients",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Meal.class
	)
	List<Food> optionalFoodList;
	
	public List<Food> getBaseFoodList() {
		return baseFoodList;
	}

	public void setBaseFoodList(List<Food> baseFoodList) {
		this.baseFoodList = baseFoodList;
	}

	public List<Food> getOptionalFoodList() {
		return optionalFoodList;
	}

	public void setOptionalFoodList(List<Food> optionalFoodList) {
		this.optionalFoodList = optionalFoodList;
	}

	public Ingredients() {
		super();
	}
	
	public Ingredients(String name)
	{
		super();
		this.name = name;
		baseFoodList = new ArrayList<Food>(); 
		optionalFoodList = new ArrayList<Food>();
	}
	public int getId()
	{
		return ingredients_id;
	}
	public String getName()
	{
		return name;
	}
	public void print()
	{
		System.out.print("ID: "+ingredients_id);
		System.out.print("\n");
		System.out.print("Ingredient: "+name);
		System.out.print("\n");
	}
}
