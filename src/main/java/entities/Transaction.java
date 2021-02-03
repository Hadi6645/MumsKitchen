package entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")

	
	

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float amount;
	private LocalDateTime time;
	@OneToOne(mappedBy="Payment",cascade = {CascadeType.ALL})
	private FoodOrder foodOrder;

	private int ClientId;
	private int CreditCardId;
	
	public Transaction() {
		super();
	}
	
	public Transaction(int id,float amount, LocalDateTime time, int ClientId, int CreditCardId)
	{
		this.id  = id;
		this.amount = amount;
		this.time = time;
		this.ClientId = ClientId;
		this.CreditCardId  = CreditCardId;
	}
	
	public Transaction(float amount, LocalDateTime time, int ClientId, int CreditCardId)
	{
		
		this.amount = amount;
		this.time = time;
		this.ClientId = ClientId;
		this.CreditCardId  = CreditCardId;
	}
	
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	public void setId(int id) {
		this.id = id;
	}
	public float getAmount()
	{
		return amount;
	}
	
	public LocalDateTime getTime()
	{
		return time;
	}
	
	public int getClientId()
	{
		return ClientId;
	}
	
	public int getCreditCardId()
	{
		return CreditCardId;
	}
	

}
