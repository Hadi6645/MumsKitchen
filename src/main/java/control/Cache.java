package control;

import java.util.List;

import entities.Dessert;
import entities.DiningSpace;
import entities.Drink;
import entities.Food;
import entities.FoodOrder;
import entities.Meal;
import entities.Menu;
import entities.Reservation;
import entities.Restaurant;

public class Cache {
	Meal current_meal;///////////////////////////////
	Drink current_drink;
	Dessert current_dessert;
	Food current_food;
	public Food getCurrent_food() {
		return current_food;
	}
	public void setCurrent_food(Food current_food) {
		this.current_food = current_food;
	}
	FoodOrder foodorder;/////////////////////////////
	int restaurantId;
	
	public Meal getCurrent_meal() {
		return current_meal;
	}
	public void setCurrent_meal(Meal current_meal) {
		this.current_meal = current_meal;
	}
	public Drink getCurrent_drink() {
		return current_drink;
	}
	public void setCurrent_drink(Drink current_drink) {
		this.current_drink = current_drink;
	}
	public Dessert getCurrent_dessert() {
		return current_dessert;
	}
	public void setCurrent_dessert(Dessert current_desert) {
		this.current_dessert = current_desert;
	}
	Restaurant restaurant;
	Reservation reservation;
	
	public FoodOrder getFoodorder() {
		return foodorder;
	}
	public void setFoodorder(FoodOrder foodorder) {
		this.foodorder = foodorder;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	List<Restaurant> cachedRestaurants;
	Menu cachedMenu;
	List<Food> cachedFood;
	DiningSpace cachedDining;
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
	
	public void requestDiningSpace(int id) {  /////////////////
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_DINING_SPACE,id);
		Object response ;
		// send instruction to the server and get a response	
		response = Connector.connectToServer(sInstruction);
		cache.setCachedDining((DiningSpace)response);
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
	
	public DiningSpace getCachedDining() {
		return cachedDining;
	}
	public void setCachedDining(DiningSpace cachedDining) {
		this.cachedDining = cachedDining;
	}
}
