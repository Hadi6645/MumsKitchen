package control;import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import clientServer.ChatClientCLI;
import clientServer.Client;
import entities.Employee;
import entities.Reservation;
import enums.EmployeeRole;
import entities.table;
import entities.Reservation;
import java.time.LocalDateTime;

public class Authenticator {
	
	
	
	private static Authenticator authenticator;
	//private static Map<String, String> cachedEmployees;
	private static boolean isLoggedIn;
	
	private Authenticator() {
		// TODO Auto-generated constructor stub
	}

	public static Authenticator getAuthenticator() {
		if (authenticator == null) {
			authenticator = new Authenticator();
			//cachedEmployees = new HashMap<String,String>();
			isLoggedIn = false;
		}
		return authenticator;
	}
	
	public boolean logIn(String id, String password) { 
		// send log-in info to the server
		String[] data = {id, password};
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.CHECK_EMPLOYEE_EXISTS, data);
		boolean response = false;
		// send instruction to the server and get a response	
		response = (boolean)Connector.connectToServer(sInstruction);
		
		if (response) {
			//this.cachedEmployees.putIfAbsent(id, password);
			isLoggedIn = true;
		}
		
		return response;
	}
	
	public void logOut() {
		// remove from the cache
		//this.cachedEmployees.remove(id);
		isLoggedIn = false;
	}
	
	public boolean isEmployeeLoggedIn() {
		return isLoggedIn;
	}
//	public boolean isEmployeeLoggedIn(String id) {
//		if(this.cachedEmployees.get(id) != null) {
//			return true;
//		}
//		return false;
//	}
	
	public boolean requestUpdateMenuPermission(Employee employee) {
		return employee.getRole() == EmployeeRole.DIETITION;
	}
	
	
	public boolean requestReservationPermission(List<table> tableList, int capacity)
	{
		int count = 0;
		//table table = tableList.get(0);
		for(int i = 0; i< tableList.size(); i++)
		{
			count += tableList.get(i).getCapacity();
		}
		if (count < capacity ) return false;
		return true;
	}
}
