package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;



@Entity
@Table(name = "restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Name;
	private Address Address ;
	private String Telephone;
	private List<Employee> Staff;
	private OpeningHours Hours;
	private List<DiningSpace> Spaces;
	private RestaurantMenu Menu;
	
	public Restaurant(int id, String Name, Address Address, String Telephone, List<Employee> Staff, OpeningHours Hours, List<DiningSpace> Spaces, RestaurantMenu Menu)
	{
		this.id =  id;
	    this.Name = Name;
		this.Address = Address ;
		this.Telephone = Telephone;
		this.Staff = Staff;
		this.Hours = Hours;
		this.Spaces = Spaces;
	    this.Menu = Menu;
	}
	
	public Restaurant(String Name, Address Address, String Telephone, List<Employee> Staff, OpeningHours Hours, List<DiningSpace> Spaces, RestaurantMenu Menu)
	{
	    this.Name = Name;
		this.Address = Address ;
		this.Telephone = Telephone;
		this.Staff = Staff;
		this.Hours = Hours;
		this.Spaces = Spaces;
	    this.Menu = Menu;
	}

	
	public String getName()
	{
		return Name;
	}
	
	public Address getAddress()
	{
		return Address;
	}
	
	public String getTelephone()
	{
		return Telephone;
	}
	
	public List<Employee> getStaff()
	{
		return Staff;
	}
	
	public OpeningHours getOpeningHours()
	{
		return Hours;
	}
	
	public List<DiningSpace> getSpaces()
	{
		return Spaces;
	}
	
	public RestaurantMenu getMenu()
	{
		return Menu;
	}
	 public void addEmployee(Employee employee)
	 {
		 Staff.add(employee);
	 }
	 
	 public int getId()
	 {
		 return id;
	 }
}
