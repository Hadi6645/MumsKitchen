package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "creditcard")
public class CreditCard implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String CardNumber;
	private String CVV;
	private LocalDate ExpirationDate;
	
	public CreditCard() {
		
	}
	
	public CreditCard(int id, String CardNumber, String CVV,LocalDate ExpirationDate)
	{
		this.id = id;
		this.CardNumber = CardNumber;
		this.CVV = CVV;
		this.ExpirationDate =  ExpirationDate;
	}
	
	public CreditCard(String CardNumber, String CVV,LocalDate ExpirationDate)
	{
		this.CardNumber = CardNumber;
		this.CVV = CVV;
		this.ExpirationDate =  ExpirationDate;
	}
	
	public String getCardNumber()
	{
		return CardNumber;
	}
	
	public String getCVV()
	{
		return CVV;
	}
	
	public LocalDate getExpirationDate()
	{
		return ExpirationDate;
	}

}
