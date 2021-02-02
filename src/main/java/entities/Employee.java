package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import enums.EmployeeRole;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeNum;
	private String id;
	private String password;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private EmployeeRole role;

	public Employee()
	{
		super();
	}
	
	public Employee(String id, String password, String firstName, String lastName, EmployeeRole role) {
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public Employee(String id)
	{
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public EmployeeRole getRole() {
		return role;
	}
	public void setRole(EmployeeRole role) {
		this.role = role;
	}

}
