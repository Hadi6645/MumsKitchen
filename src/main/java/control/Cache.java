package control;

import java.util.List;

import entities.Restaurant;

public class Cache {
	
	int restaurantId; 
	Restaurant restaurant;
	List<Restaurant> cachedRestaurants;
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
	
	public Object requestRestaurants() { 
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_RESTAURANTS_LIST,null);
		Object response ;
		// send instruction to the server and get a response	
		response = Connector.connectToServer(sInstruction);
		return  response;
	}
	public List<Restaurant> getCachedRestaurants() {
		return cachedRestaurants;
	}
	public void setCachedRestaurants(List<Restaurant> cachedRestaurants) {
		this.cachedRestaurants = cachedRestaurants;
	}
}
