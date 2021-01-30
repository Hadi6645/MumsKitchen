package entities;

import java.time.LocalDateTime;
import java.util.List;

public class ComplaintsReport extends Report {

	public ComplaintsReport() {
		super();
	}
	public ComplaintsReport(int id, int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers) {
		super(id, RestaurantId, Time, Numbers);
		// TODO Auto-generated constructor stub
	}
	
	public ComplaintsReport(int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers) {
		super(RestaurantId, Time, Numbers);
		// TODO Auto-generated constructor stub
	}

}
