package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.Server;

import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "restaurant")
public class Restaurant implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Name;
	@OneToOne
	private Address Address ;
	private String Telephone;
	@Column
    @ElementCollection(targetClass=Employee.class)
	private List<Employee> Staff;
	@OneToOne
	private OpeningHours Hours;
	@OneToMany(mappedBy = "rest")
	private List<DiningSpace> Spaces;
	
	@OneToOne(mappedBy="restaurant",cascade = {CascadeType.ALL})
	private Menu restMenu;
	
	private static Session session;
	
	public Restaurant() {
		
	}
	
	public Restaurant(int id, String Name, Address Address, String Telephone, List<Employee> Staff, OpeningHours Hours, List<DiningSpace> Spaces, Menu Menu)
	{
		this.id =  id;
	    this.Name = Name;
		this.Address = Address ;
		this.Telephone = Telephone;
		this.Staff = Staff;
		this.Hours = Hours;
		this.Spaces = Spaces;
	    this.restMenu = Menu;
	}
	
	public Restaurant(String Name, Address Address, String Telephone, List<Employee> Staff, OpeningHours Hours, List<DiningSpace> Spaces, Menu Menu)
	{
	    this.Name = Name;
		this.Address = Address ;
		this.Telephone = Telephone;
		this.Staff = Staff;
		this.Hours = Hours;
		this.Spaces = Spaces;
	    this.restMenu = Menu;
	}

	
	public Menu getRestMenu() {
		return restMenu;
	}

	public void setRestMenu(Menu resMenu) {
		session = Server.getSession();
		this.restMenu = resMenu;
		restMenu.setRestaurant(this);
		session.save(restMenu);
		session.save(this);
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
	
	public Menu getMenu()
	{
		return restMenu;
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
