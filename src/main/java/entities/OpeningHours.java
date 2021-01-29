package entities;

//import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpeningHours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
