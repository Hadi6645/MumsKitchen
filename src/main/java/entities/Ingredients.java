package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "ingredients")
public class Ingredients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	
	public Ingredients() {
		super();
	}
	
	public Ingredients(String name)
	{
		super();
		this.name = name;
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public void print()
	{
		System.out.print("ID: "+id);
		System.out.print("\n");
		System.out.print("Ingredient: "+name);
		System.out.print("\n");
	}
}
