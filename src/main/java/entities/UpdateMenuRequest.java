package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.Server;
import enums.UpdateMenuRequest_Status;
import enums.UpdateMenuRequest_Type;

@Entity
@Table(name = "updatemenurequest")
public class UpdateMenuRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Food food;
	private int restaurantId;
	private UpdateMenuRequest_Type type;
	private UpdateMenuRequest_Status Status;
	
	private static Session session;
	public UpdateMenuRequest() {
		super();
	}
	
	
	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public UpdateMenuRequest_Type getType() {
		return type;
	}
	public void setType(UpdateMenuRequest_Type type) {
		this.type = type;
	}
	public UpdateMenuRequest_Status getStatus() {
		return Status;
	}
	public void setStatus(UpdateMenuRequest_Status status) {
		Status = status;
	}
	public UpdateMenuRequest(Food food, UpdateMenuRequest_Type type, UpdateMenuRequest_Status status, int restaurantId) {
		super();
		this.food = food;
		this.type = type;
		Status = status;
		this.restaurantId = restaurantId;
	}
	
}

