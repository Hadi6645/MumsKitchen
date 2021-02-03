package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.Server;

@Entity
@Table(name = "food")
public abstract class Food implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "food_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int food_id;
	
	String name;
	String description;
	String picture;
	double price;

	@ManyToMany(mappedBy = "allfood",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Menu.class
	)
	private List<Menu> menus;
	
	protected static Session session;

public Food(String name,String description,double price)
{
	this.name = name;
	this.description = description;
	this.price = price;
	menus = new ArrayList<Menu>();
}
public Food() {
	// TODO Auto-generated constructor stub
}

public List<Menu> getMenus() {
	return menus;
}
public void setMenus(List<Menu> menus) {
	this.menus = menus;
}
public void addToMenus(Menu menu)
{
	session = Server.getSession();
	menus.add(menu);
	session.save(this);
}
public int getID()
{
	return food_id;
}
public String getName()
{
	return name;
}
public String getDesc()
{
	return description;
}
public double getPrice()
{
	return price;
}
public void setName(String newName)
{
	name=newName;
}
public void setDesc(String newDesc)
{
	description=newDesc;
}
public void setPrice(double newPrice)
{
	price=newPrice;
}
/*public void print()
{
	System.out.print("\n");
	System.out.print("Product: "+(id-1));
	System.out.print("\n");
	System.out.print(name);
	System.out.print("\n");
	System.out.print("Description: ");
	System.out.print(description);
	System.out.print("\n");
	System.out.print("Price: ");
	System.out.print(price);
	System.out.print("\n");
}
public boolean update() throws Exception
{
	System.out.print("Product "+id+" update:\n");
	session = Server.getSession();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String newName,newDesc,newPrice;
	boolean flag = false;
	double price;
	Food food ;
	food = (Food)session.get(Food.class, this.id);
	System.out.print("If you don't want to change a certain field, leave it empty.:\n");
	System.out.print("Name Change:\n");
	newName = reader.readLine();
	if (!(newName.length() == 0))
	{
		flag = true;
	}
	else newName = food.getName();
		
	System.out.print("Description Change:\n");
	newDesc = reader.readLine();
	if (!(newDesc.length() == 0))
	{
		flag = true;
	}
	else newDesc = food.getDesc();
	
	System.out.print("Price Change:\n");
	newPrice = reader.readLine();
	if (!(newPrice.length() == 0))
	{
		price = Double.valueOf(newPrice);
		flag = true;
	}
	else price = food.getPrice();
	
	if(flag == true && Server.authchange(food))
	{
		food.setName(newName);
		food.setDesc(newDesc);
		food.setPrice(price);
		session.saveOrUpdate(food);
		System.out.print("Changes made succesfully.");
		return true;
	}
	
	else {
		if(flag == false)
		{
			System.out.print("No Changes were done.");
			return false;
		}
		System.out.print("No authentication, changes were not done.");
		return false;
		}
	}*/
}
