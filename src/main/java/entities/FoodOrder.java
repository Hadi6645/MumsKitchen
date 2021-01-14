package entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.FoodOrder_Status;

@Entity
@Table(name = "foodorder")
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Menu Order;
	private float TotalPrice;
	private Client Client;
	private float ShippingFee;
	private Transaction Payment;
	private LocalDateTime Time; 
	private FoodOrder_Status Status;
	
	public FoodOrder(int id, Menu order, float totalPrice, Client client, float shippingFee, Transaction payment, LocalDateTime time, FoodOrder_Status status) {
		this.id = id;
		Order = order;
		TotalPrice = totalPrice;
		Client = client;
		ShippingFee = shippingFee;
		Payment = payment;
		Time = time; 
		Status = status;
	}
	
	public FoodOrder(Menu order, float totalPrice, Client client, float shippingFee, Transaction payment) {
		Order = order;
		TotalPrice = totalPrice;
		Client = client;
		ShippingFee = shippingFee;
		Payment = payment;
	}
	
	public int getID() {
		return id;
	}
	public Menu getOrder() {
		return Order;
	}
	public float getTotalPrice() {
		return TotalPrice;
	}
	public Client getClient() {
		return Client;
	}
	public float getShippingFee() {
		return ShippingFee;
	}
	public Transaction getPayment() {
		return Payment;
	}
	public LocalDateTime getTime() {
		return Time;
	}
	public FoodOrder_Status getStatus() {
		return Status;
	} 
	public void setStatus(FoodOrder_Status status) {
		Status = status;
	}
}
