package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "client")

	
	
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String FirstName;
	String LastName;
	String Phone;
	String Email;
	Address Address;
	private List<CreditCard> CreditCard;
	
	
	public Client(String FirstName,String LastName, String Phone,
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
	
	public Client(String FirstName,String LastName, String Phone,
			String Email,
			Address Address)
			{
			this.FirstName = FirstName;
			this.LastName =  LastName;
			this.Phone = Phone;
			this.Email = Email;
			this.Address = Address;
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
   
   

}
