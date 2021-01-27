package control;

import entities.Restaurant;

public class Cache {
	
	int restaurantId; 
	Restaurant restaurant;
	
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
}
