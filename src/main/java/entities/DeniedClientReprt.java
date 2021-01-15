package entities;

import java.time.LocalDateTime;
import java.util.List;

public class DeniedClientReprt extends Report {

	public DeniedClientReprt(int id, int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers) {
		super(id, RestaurantId, Time, Numbers);
		// TODO Auto-generated constructor stub
	}
	
	public DeniedClientReprt(int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers) {
		super( RestaurantId, Time, Numbers);
		// TODO Auto-generated constructor stub
	}

}
