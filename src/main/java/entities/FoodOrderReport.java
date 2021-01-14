package entities;

import java.time.LocalDateTime;
import java.util.List;

public class FoodOrderReport extends Report {

	private  float TotalPrice;
	public FoodOrderReport(int id, int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers, float TotalPrice) {
		super(id, RestaurantId, Time, Numbers);
		this.TotalPrice =  TotalPrice;
	}
	
	public FoodOrderReport( int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers, float TotalPrice) {
		super( RestaurantId, Time, Numbers);
		this.TotalPrice =  TotalPrice;
	}
	
	public float getTotalPrice()
	{
		return TotalPrice;
	}
	

}
