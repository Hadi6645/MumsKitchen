package control;

import java.time.Month;

public class ReportRequest {

	int restaurantId;
	Month month;
	public ReportRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public ReportRequest(Month month) {
		super();
		this.month = month;
	}
	
	public ReportRequest(int restaurantId, Month month) {
		super();
		this.restaurantId = restaurantId;
		this.month = month;
	}

	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		this.month = month;
	}

}
