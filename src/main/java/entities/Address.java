package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String City;
	private String Street;
	private int HouseNumber;
	
	public Address(String City, String Street, int HouseNumber )
	{
	this.City = City;
	this.Street = Street;
	this.HouseNumber = HouseNumber;
	}
	
	public String getCity()
	{
		return City;
	}
	
	public String getStreet()
	{
		return Street;
	}
	
	public int getHouseNumber()
	{
		return HouseNumber;
	}
	
	

}
