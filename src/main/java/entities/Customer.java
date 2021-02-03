package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String FirstName;
	String LastName;
	String Phone;
	String Email;
	@OneToOne
	Address Address;
	@Column
    @ElementCollection(targetClass=CreditCard.class)
	private List<CreditCard> CreditCard;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="customer_id")
	FoodOrder foodOrder;
	public Customer() {
		
	}
	public Customer(String FirstName,String LastName, String Phone,
	String Email,
	Address Address,
	List<CreditCard> CreditCard)
	{
	this.FirstName = FirstName;
	this.LastName =  LastName;
	this.Phone = Phone;
	this.Email = Email;
	this.Address = Address;
	this.CreditCard = CreditCard;
	}
	
	public Customer(String FirstName,String LastName, String Phone,
			String Email,
			Address Address)
			{
			this.FirstName = FirstName;
			this.LastName =  LastName;
			this.Phone = Phone;
			this.Email = Email;
			this.Address = Address;
			}
	
    public FoodOrder getFoodOrder() {
		return foodOrder;
	}
	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}
	public String getFirstName()
{
	return FirstName;
}

   public String getLastName()
{
	return LastName;
}
   public String getPhone()
   {
	   return Phone;  
   }
   
   public String getEmail()
   {
	   return Email;
   }
   
   public Address getAddress()
   {
	   return Address;
   }
   
   public List<CreditCard> getCreditCard()
   {
	   return CreditCard;
   }
   
   public void addCreditCard(CreditCard CreditCard_)
   {
	   CreditCard.add(CreditCard_);
   }
   
  public void removeCreditCard(int index)
  {
	  CreditCard.remove(index);
  }
  
  public int getId()
  {
	  return id;
  }
public void setId(int id) {
	this.id = id;
}
}