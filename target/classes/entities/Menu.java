package entities;

import entities.Drink;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.Server;

@Entity
@Table(name = "menu")
public class Menu implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int menu_id;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Food.class)
			@JoinTable(name="menu_food",
			joinColumns = @JoinColumn(name = "menu_id"),
			inverseJoinColumns = @JoinColumn(name = "food_id"))
	private List<Food> allfood;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="menu_id")
	private Restaurant restaurant;
	
	protected static Session session;


	public Menu() {
		// TODO Auto-generated constructor stub
		 allfood = new ArrayList<Food>();
	}
	public Menu(List<Food> foodList) {
		// TODO Auto-generated constructor stub
		 allfood = foodList;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restMenu) {
		this.restaurant = restMenu;
	}
	public void addfood(Food food) {
		session= Server.getSession();
		allfood.add(food);
		food.addToMenus(this);
		session.save(food);
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	
	public List<Food> getallfood()
	{
		return allfood;
	}
	public void setallfood(List<Food> allfood)
	{
		session= Server.getSession();
		for(Food food : allfood)
		{
			this.allfood.add(food);
			food.addToMenus(this);
			session.save(food);
		}
	}
}