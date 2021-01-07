package control;import java.util.Map;

import entities.Employee;
import enums.EmployeeRole;

public class Authenticator {
	
	private static Authenticator authenticator;
	private Map<String, String> cachedEmployees;

	private Authenticator() {
		// TODO Auto-generated constructor stub
	}

	public static Authenticator getAuthenticator() {
		if (authenticator == null) {
			authenticator = new Authenticator();
		}
		return authenticator;
	}
	
	public boolean logIn(String id, String password) { //TODO continue
		// send log-in info to the server
		String[] data = {id, password};
		ServerInstruction instruction = new ServerInstruction(ServerInstructionType.CHECK_EMPLOYEE_EXISTS, data);
		// send instruction to the server and get a response
		boolean response = false;
		if (response) {
			this.cachedEmployees.putIfAbsent(id, password);
		}
		return response;
	}
	
	public void logOut(String id) {
		// remove from the cache
		this.cachedEmployees.remove(id);
	}
	
	public boolean isEmployeeLoggedIn(String id) {
		// send log-in info to the server
		if(this.cachedEmployees.get(id) != null) {
			return true;
		}
		return false;
	}
	
	public boolean requestUpdateMenuPermission(String id) { // TODO continue
		if (this.cachedEmployees.get(id) == null) {
			return false;
		}
		// send log-in info to the server
		String data = id;
		ServerInstruction instruction = new ServerInstruction(ServerInstructionType.CHECK_EMPLOYEE_IS_DIETITION, data);
		// send instruction to the server and get a response
		boolean response = false;
		return response;
	}
	
	public boolean requestUpdateMenuPermission(Employee employee) {
		return employee.getRole() == EmployeeRole.DIETITION;
	}
}
