package control;

import java.util.List;

import entities.Complaint;
import entities.ComplaintsReport;
import entities.Employee;
import entities.FoodOrderReport;
import entities.Report;
import entities.Reservation;
import entities.Restaurant;
import entities.UpdateMenuRequest;
import entities.table;

public class DataService {
	
	
private static DataService DataService;
	
	private DataService() {
		
	}
	public static DataService getDataService()
	{
		if (DataService == null) {
			DataService = new DataService();
		}
		return DataService;
	}
	
	
	public boolean checReservationTables(Reservation res)
	{
		Reservation data= res;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.CHECK_UNRESERVED_TABLES, data);
		// send instruction to the server and get a response	
		List<table> response = (List<table>)Connector.connectToServer(sInstruction);
		return response.size() > 0;
	}

	public int sendReservation(Reservation reserve)   // reservation complete 
	{
		Reservation data = reserve;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.MAKE_RESERVATION, data);
		// send instruction to the server and get a response	
		int response = (int)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	public List<List<table>> CanfirmReservation(Reservation res)  
	{
		Reservation data = res;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_RESERVATION_POSSIBILITIES, data);
		// send instruction to the server and get a response	
		List<List<table>> response = (List<List<table>>)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	
	public float[] CancelFoodOrder(int FoodOrderID)   // return money back and order price 
	{
		int data = FoodOrderID;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.CANCEL_FOOD_ORDER, data);
		// send instruction to the server and get a response	
		float[] response = (float[])Connector.connectToServer(sInstruction);
		return response;
	}
	
	public int CancelReservation(int ReservationID)   // return penalty(10nis per person) (0 all is good)
	{
		int data = ReservationID;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.CANCEL_RESERVATION, data);
		// send instruction to the server and get a response	
		int response = (int)Connector.connectToServer(sInstruction);
		return response;
	}
	
	public boolean AddComplaint(Complaint complaint)  
	{
		Complaint data = complaint;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.ADD_COMPLAINT, data);
		// send instruction to the server and get a response	
		boolean response = (boolean)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	//********//
	
	public List<Complaint> getActiveComplaint()   /// return list of active complaint to the employee
	{
		
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_ACTIVE_COMPLAINTS, null);
		// send instruction to the server and get a response	
		List<Complaint> response = (List<Complaint>)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	public boolean UpdateComplaint(Complaint complaint)  ///employee updates complaint status
	{
		Complaint data = complaint;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.UPDATE_COMPLAINT, data);
		// send instruction to the server and get a response	
		boolean response = (boolean)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	public boolean Update_Menu_Request(UpdateMenuRequest updatemenu)  ///employee updates request status
	{
		UpdateMenuRequest data = updatemenu;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.UPDATE_MENU_REQUEST, data);
		// send instruction to the server and get a response	
		boolean response = (boolean)Connector.connectToServer(sInstruction);
		return response;
	}
	
	public boolean Log_out(Employee employee)  ///log out
	{
		Employee data = employee;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.UPDATE_MENU_REQUEST, data);
		// send instruction to the server and get a response	
		boolean response = (boolean)Connector.connectToServer(sInstruction);
		return response;
	}
	/*
	//reports
	public DeniedClientReport getDeniedReport(ReportRequest request )  //1
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_DENIED_CUSTOMERS_REPORT, data);
		// send instruction to the server and get a response	
		DeniedClientReport response = (DeniedClientReport)Connector.connectToServer(sInstruction);
		return response;
	}
	
	public List<DeniedClientReport> getAllDeniedReport(ReportRequest request )  //2
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_ALL_DENIED_CUSTOMERS_REPORTS, data);
		// send instruction to the server and get a response	
		List<DeniedClientReport> response = (List<DeniedClientReport>)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	public FoodOrderReport getDeniedReport(ReportRequest request )  //3
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_FOOD_ORDERS_REPORT, data);
		// send instruction to the server and get a response	
		FoodOrderReport response = (FoodOrderReport)Connector.connectToServer(sInstruction);
		return response;
	}
	
	public List<FoodOrderReport> getAllDeniedReport(ReportRequest request )  //4
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_ALL_FOOD_ORDERS_REPORT, data);
		// send instruction to the server and get a response	
		List<FoodOrderReport> response = (List<FoodOrderReport>)Connector.connectToServer(sInstruction);
		return response;
	}
	

	public ComplaintsReport getComplaintReport(ReportRequest request )  //5
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_COMPLAINTS_REPORT, data);
		// send instruction to the server and get a response	
		ComplaintsReport response = (ComplaintsReport)Connector.connectToServer(sInstruction);
		return response;
	}
	
	public List<ComplaintsReport> getAllComplaintReport(ReportRequest request )  //6
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_ALL_COMPLAINTS_REPORT, data);
		// send instruction to the server and get a response	
		List<ComplaintsReport> response = (List<ComplaintsReport>)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	public List<Report> getComplaintReport(ReportRequest request )  //7
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_RESTAURANT_REPORTS, data);
		// send instruction to the server and get a response	
		List<Report> response = (List<Report>)Connector.connectToServer(sInstruction);
		return response;
	}
	
	public List<List<Report>> getAllComplaintReport(ReportRequest request )  //8
	{
		Employee data = request;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_ALL_RESTAURANTS_REPORTS, data);
		// send instruction to the server and get a response	
		List<List<Report>> response = (List<List<Report>>)Connector.connectToServer(sInstruction);
		return response;
	}
	
	
	public Restaurant getAllComplaintReport(String employeeID)  //9
	{
		String data = employeeID;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.GET_RESTAURANT_BY_EMPLOYEE, data);
		// send instruction to the server and get a response	
		Restaurant response = (Restaurant)Connector.connectToServer(sInstruction);
		return response;
	}*/
}
