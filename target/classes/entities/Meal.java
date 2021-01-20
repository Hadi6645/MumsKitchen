package entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import clientServer.App;
import clientServer.ChatClientCLI;
import clientServer.Server;

@Entity
public class Meal extends Food{
	@Column
    @ElementCollection(targetClass=Ingredients.class)
	//@OneToMany(cascade = CascadeType.ALL)
	List<Ingredients>  ingredient; //basic
	@Column
    @ElementCollection(targetClass=Ingredients.class)
	//@OneToMany(cascade = CascadeType.ALL)
	List<Ingredients>  ingredients; //optional

public Meal(String name, String description,double price,List<Ingredients>  ingredient,List<Ingredients>  ingredients)
{
	super(name,description,price);
	this.ingredient = ingredient;
	this.ingredients = ingredients;
}
public void setNewIng(List<Ingredients> newIng)
{
	ingredient=newIng;
}
public void setNewOpIng(List<Ingredients> newopIng)
{
	ingredients=newopIng;
}
public List<Ingredients> getBaseIng()
{
	return ingredient;
}
public List<Ingredients> getOptIng()
{
	return ingredients;
}
@Override
public boolean update() throws Exception
{
	super.update();
	session = Server.getSession();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	List<Ingredients> newIngsadd =new ArrayList<Ingredients>();
	List<Ingredients> newOptIngsadd =new ArrayList<Ingredients>();
	List<Ingredients> oldIngs,oldopt,temp,temp2;
	String newIng = null,optIng = null,deleteIng = null;
	boolean flag1=false,flag2=false,flag3=false,flag4=false;
	double price;
	Meal meal ;
	meal = (Meal)session.get(Food.class, this.id);
	oldIngs = meal.getBaseIng();
	oldopt=meal.getOptIng();
	App.printIngredients();
	System.out.print("\n");
	System.out.print("Base Ingredients add:\n");
	System.out.print("type ingredients id's: or press enter to skip");
	
	newIng = reader.readLine();
	if(!(newIng.length() == 0))
	{
		newIngsadd.add(App.getIngredient(Integer.parseInt(newIng)) );
		flag1 = true;
		newIng = reader.readLine();
	}
	while(!(newIng.length() == 0))
	{
		newIngsadd.add(App.getIngredient(Integer.parseInt(newIng)) );
		newIng = reader.readLine();
	}
	oldIngs.addAll(newIngsadd);
	
	System.out.print("Optional Ingredients add:\n");
	System.out.print("type ingredients id's: or press enter to skip");
	optIng = reader.readLine();
	if(!(optIng.length() == 0))
	{
		newOptIngsadd.add(App.getIngredient(Integer.parseInt(optIng)) );
		flag2 = true;
		optIng = reader.readLine();
	}
	while(!(optIng.length() == 0))
	{
		newOptIngsadd.add(App.getIngredient(Integer.parseInt(optIng)) );
		optIng = reader.readLine();
	}
	oldopt.addAll(newOptIngsadd);
	
	temp = meal.getBaseIng();
	System.out.print("Base Ingredients delete:\n");
	System.out.print("type ingredients id's: or press enter to skip ");
	
	deleteIng = reader.readLine();
	if(!(deleteIng.length() == 0))
	{
		temp.remove(App.getIngredient(Integer.parseInt(deleteIng)) );
		flag3 = true;
		deleteIng = reader.readLine();
	}
	while(!(deleteIng.length() == 0))
	{
		temp.remove(App.getIngredient(Integer.parseInt(deleteIng)) );
		deleteIng = reader.readLine();
	}

	
	temp2=meal.getOptIng();
	System.out.print("Optional Ingredients delete:\n");
	System.out.print("type ingredients id's: or press enter to skip ");
	deleteIng = reader.readLine();
	if(!(deleteIng.length() == 0))
	{
		temp2.remove(App.getIngredient(Integer.parseInt(deleteIng)) );
		flag4 = true;
		deleteIng = reader.readLine();
	}
	while(!(deleteIng.length() == 0))
	{
		temp2.remove(App.getIngredient(Integer.parseInt(deleteIng)) );
		deleteIng = reader.readLine();
	}
	
	if( Server.authchange(meal))
	{
		if(flag1)
			meal.setNewIng(oldIngs);
		if(flag2)
			meal.setNewOpIng(oldopt);
		if(flag3)
			meal.setNewIng(temp);
		if(flag4)
			meal.setNewOpIng(temp2);
		session.saveOrUpdate(meal);
		System.out.print("Changes made succesfully.");
		return true;
	}
	else {
		System.out.print("No authentication, changes were not done. or nothing was changed.");
		return false;
	}
	
}
@Override
public void print()
{	
	super.print();
	System.out.print("Base Ingredients: \n");
	for(Ingredients ingredient : ingredient)
	{
		System.out.print(ingredient.getName()+" ");
	}
	System.out.print("\n Optional Ingredients: \n");
	for(Ingredients ingredient : ingredients)
	{
		System.out.print(ingredient.getName()+"	");
	}
	System.out.print("\n");
}

}