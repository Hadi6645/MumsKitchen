package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
public class RestaurantMenu implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@Basic
//	@Type( type = BaseMenuType.class )
	@OneToOne
	BaseMenu common;
	@OneToOne
	Menu indvidual;
	
	public RestaurantMenu() {
		
	}
	
	public RestaurantMenu(BaseMenu common,Menu ind) {
		this.common = common;
		this.indvidual = ind;
	}
	public BaseMenu getcommon()
	{
		return common;
	}
	public Menu getindv()
	{
		return indvidual;
	}
	

	/*public List<Drink> get_drinks(){
		List <Drink> drinks = new ArrayList<>();
		drinks = common.getdrinks();
		for(int i=0; i<indvidual.getdrinks().size(); i++) {
			drinks.add(indvidual.getdrinks().get(i));
		}
		return drinks;
	}
	
	public List<Meal> get_meals(){
		List <Meal> Meals = new ArrayList<>();
		Meals = common.getmeals();
		for(int i=0; i<indvidual.getmeals().size(); i++) {
			Meals.add(indvidual.getmeals().get(i));
		}
		return Meals;
	}
	
	public List<Dessert> get_dessert(){
		List <Dessert> Desserts = new ArrayList<>();
		Desserts = common.getdesserts();
		for(int i=0; i<indvidual.getdesserts().size(); i++) {
			Desserts.add(indvidual.getdesserts().get(i));
		}
		return Desserts;
	}*/
}

