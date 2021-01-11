package entities;

//import java.sql.Date;
import java.time.LocalTime;

public class OpeningHours {
	
	private LocalTime[][] OpenHours = new LocalTime[7][2] ;
	public OpeningHours()
	{
		
	}
	
	public void setOpeningHours(int day, LocalTime open, LocalTime close)
	{
	 OpenHours[day][1] = open;
	 OpenHours[day][2] = close;
	}
	
	public LocalTime[][] getOpeningHours()
	{
		return OpenHours;
	}

}
