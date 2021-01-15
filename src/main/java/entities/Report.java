package entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "report")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private int RestaurantId;
	private LocalDateTime Time;
	private List<NumberByDate> Numbers;
	
	public Report(int id, int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers)
	{
	this.id = id;
	this.RestaurantId = RestaurantId;
	this.Time = Time;
	this.Numbers = Numbers;
	
	}
	
	public Report(int RestaurantId, LocalDateTime Time, List<NumberByDate> Numbers)
	{
	this.RestaurantId = RestaurantId;
	this.Time = Time;
	this.Numbers = Numbers;
	}
	
	public int getRestaurant()
	{
		return RestaurantId;
	}
	
	public LocalDateTime getTime()
	{
		return Time;
	}
	
	public List<NumberByDate> getNumbers()
	{
		return Numbers;
	}
	
}
