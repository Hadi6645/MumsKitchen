package control;import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import clientServer.ChatClientCLI;
import clientServer.Client;
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
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.CHECK_EMPLOYEE_EXISTS, data);
		ChatClientCLI clientCli = new ChatClientCLI(new Client());
		boolean response = false;
		// send instruction to the server and get a response
		CompletableFuture<Object> completableFuture = new CompletableFuture<Object>();
		try {
			clientCli.sendInstructionToServer(sInstruction,completableFuture);
			while (!completableFuture.isDone()) {
			    // waiting
			}
			try {
				response = (boolean)completableFuture.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		if(this.cachedEmployees.get(id) != null) {
			return true;
		}
		return false;
	}
	
	public boolean requestUpdateMenuPermission(Employee employee) {
		return employee.getRole() == EmployeeRole.DIETITION;
	}
}
