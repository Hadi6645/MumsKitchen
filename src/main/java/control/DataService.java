package control;

import java.util.List;

import entities.Reservation;
import entities.table;

public class DataService {
	
	public boolean checReservationTables(Reservation res)
	{
		Reservation data= res;
		ServerInstruction sInstruction = new ServerInstruction(ServerInstructionType.CHECK_UNRESERVED_TABLES, data);
		// send instruction to the server and get a response	
		List<table> response = (List<table>)Connector.connectToServer(sInstruction);
		return response.size() > 0;
	}

}
