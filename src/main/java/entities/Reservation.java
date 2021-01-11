package entities;

import java.time.LocalDateTime;
import enums.Status;

public class Reservation {
	private int id;
	private Client Client;
	private int GuestsNumber;
	//private Restaurant Restaurant;
	private DinningSpace Space;
	private LocalDateTime Time;
	private CreditCard CreditCard;
	private Status Status;
	private String LinkToHealthReport;
	
	public Reservation(int id, Client Client, int GuestsNumber,DinningSpace Space, LocalDateTime Time, CreditCard CreditCard, Status Status, String LinkToHealthReport)
	{
		 this.id  = id;
		this.Client = Client;
		this.GuestsNumber = GuestsNumber;
		//private Restaurant Restaurant;
		this.Space = Space;
		this.Time = Time;
		this.CreditCard = CreditCard;
		this.Status = Status;
		this.LinkToHealthReport  = LinkToHealthReport;
	}
	
	public Reservation(Client Client, int GuestsNumber,DinningSpace Space, LocalDateTime Time, CreditCard CreditCard, String LinkToHealthReport)
	{
		this.Client = Client;
		this.GuestsNumber = GuestsNumber;
		//private Restaurant Restaurant;
		this.Space = Space;
		this.Time = Time;
		this.CreditCard = CreditCard;
		this.LinkToHealthReport  = LinkToHealthReport;
	}
	
	public int getId()
	{
		return id;
	}
	public Client getClient()
	{
		return Client;
	}
	 public int getGuestsNumber()
	 {
		 return GuestsNumber;
	 }
	  
	/*public  Restaurant getRestaurant()
	{
		return Restaurant;
	}*/
	 
	 public DinningSpace getSpace()
	 {
		 return Space;
	 }
	 
	 public LocalDateTime getTime()
	 {
		 return Time;
	 }
	 
	 public CreditCard getCreditCard()
	 {
		 return CreditCard;
	 }
	 
	 public Status getStatus()
	 {
		 return Status;
	 }
	 
	 public String getLinkToHealthReport()
	 {
		 return LinkToHealthReport;
	 }
	 
	 public void setStatus(Status status)
	 {
		Status = status; 
	 }
}
