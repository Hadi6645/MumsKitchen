package entities;

import enums.EmployeeRole;

public class Employee {
	private String id;
	private String password;
	private String firstName;
	private String lastName;
	private EmployeeRole role;

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(String id, String password, String firstName, String lastName, EmployeeRole role) {
		super();
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
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
