package entities;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.ComplaintStatus;
import enums.ComplaintType;

@Entity
@Table(name = "complaint")
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Customer Customer;
	private Restaurant Restaurant;
	private String Description;
	private LocalDateTime Time;
	private int FoodOrderId;
	private int ReservationId;
	private ComplaintType Type;
	private float PaidBackMoney;
	private int TransactionId;
	private ComplaintStatus Status;
	
	public Complaint()
	{
		
	}
	public Complaint(int id, Customer Customer, Restaurant Restaurant, String Description, LocalDateTime Time, ComplaintType Type, ComplaintStatus Status)
	{
		this.id =  id;
		this.Customer =  Customer;
		this.Restaurant =  Restaurant;
		this.Description = Description;
		this.Time = Time;
		this.Type = Type;
		this.Status = Status;
	}
	
	
	@SuppressWarnings("null")
	public List<String> getAllComplaintType()
	{
		List<String> enumlist = null;
		 enumlist.add(Type.DELIVERY_NOT_RECIEVED.toString());
		 enumlist.add(Type.DELAY_IN_DELIVERY.toString());
		 enumlist.add(Type.FOOD_ORDER_NOT_CORRECT.toString());
		 enumlist.add(Type.PAYMENT_FEE_NOT_CORRECT.toString());
		 return enumlist;
	}
	
	public ComplaintType getType() {
		return Type;
	}
	public void setType(ComplaintType type) {
		Type = type;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDescription()
	{
		return Description;
	}
	public void addFoodOrderId(int id)
	{
		FoodOrderId = id;
	}
	
	public void addReservationId(int id)
	{
		ReservationId = id;
	}
	
	public void setPaidBackMoney(float amount)
	{
		PaidBackMoney =  amount;
	}
	
	public void addTransactionId(int id)
	{
		TransactionId = id;
	}
	
	public void setStatus(ComplaintStatus status)
	{
		Status = status;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Customer getClient()
	{
		return Customer;
	}
	
	public Restaurant getRestaurant()
	{
		return Restaurant;
	}
	
	
	
	public LocalDateTime getTime()
	{
		return Time;
	}
	
	public int getFoodOrderId()
	{
		return FoodOrderId;
	}
	
	public int getReservationId()
	{
		return ReservationId;
	}
	
	public ComplaintType getComplaintType()
	{
		return Type;
	}
	
	public float getPaidBackMoney()
	{
		return PaidBackMoney;
	}
	
	public int getTransactionId()
	{
		return TransactionId;
	}
	
	public ComplaintStatus getComplaintStatus()
	{
		return Status;
	}
	
	
	
	
	
	
	
	
	

}
