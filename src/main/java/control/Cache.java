package control;

import java.util.List;

import entities.Food;
import entities.Menu;
import entities.Restaurant;

public class Cache {
	
	int restaurantId; 
	Restaurant restaurant;
	List<Restaurant> cachedRestaurants;
	Menu cachedMenu;
	List<Food> cachedFood;
	private static Cache cache;
	
	private Cache() {
		
	}
	public static Cache getCache()
	{
		if (cache == null) {
			cache = new Cache();
		}
		return cache;
	}
	public int getRestId() {
		return restaurantId;
	}
	public void setRestId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void requestRestaurants() { 
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_RESTAURANTS_LIST,null);
		Object response ;
		// send instruction to the server and get a response	
		response = Connector.connectToServer(sInstruction);
		cache.setCachedRestaurants((List<Restaurant>)response);
	}
	public void requestMenu(int id) { 
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_MENU,id);
		Object response ;
		// send instruction to the server and get a response	
		response = Connector.connectToServer(sInstruction);
		cache.setCachedFood((List<Food>)response);
	}
	public List<Food> getCachedFood() {
		return cachedFood;
	}
	public void setCachedFood(List<Food> cachedFood) {
		this.cachedFood = cachedFood;
	}
	public List<Restaurant> getCachedRestaurants() {
		return cachedRestaurants;
	}
	public void setCachedRestaurants(List<Restaurant> cachedRestaurants) {
		this.cachedRestaurants = cachedRestaurants;
	}
	public Menu getCachedMenu() {
		return cachedMenu;
	}
	public void setCachedMenu(Menu menu) {
		this.cachedMenu = menu;
	}
}
