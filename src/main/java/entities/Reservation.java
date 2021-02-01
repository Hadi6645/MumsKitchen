package entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.Status;

@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Costumer Costumer;
	private int GuestsNumber;
	private Restaurant Restaurant;
	private DiningSpace Space;
	private LocalDateTime Time;
	private CreditCard CreditCard;
	private Status Status;
	private String LinkToHealthReport;
	private List<table> tables;
	
	public List<table> getTables() {
		return tables;
	}

	public void setTables(List<table> tables) {
		this.tables = tables;
	}

	public Reservation() {
		super();
	}
	
	public Reservation(int id, Costumer Costumer, int GuestsNumber,DiningSpace Space, List<table> tables, LocalDateTime Time,  CreditCard CreditCard, Status Status, String LinkToHealthReport, Restaurant Restaurant)
	{
		 this.id  = id;
		this.Costumer = Costumer;
		this.GuestsNumber = GuestsNumber;
		this.Restaurant = Restaurant;
		this.Space = Space;
		this.Time = Time;
		this.CreditCard = CreditCard;
		this.Status = Status;
		this.LinkToHealthReport  = LinkToHealthReport;
		this.tables  = tables;
	}
	
	public Reservation(Costumer Costumer, int GuestsNumber,DiningSpace Space, LocalDateTime Time, CreditCard CreditCard, String LinkToHealthReport, Restaurant Restaurant)
	{
		this.Costumer = Costumer;
		this.GuestsNumber = GuestsNumber;
		this.Restaurant = Restaurant;
		this.Space = Space;
		this.Time = Time;
		this.CreditCard = CreditCard;
		this.LinkToHealthReport  = LinkToHealthReport;
	}
	
	public int getId()
	{
		return id;
	}
	public Costumer getClient()
	{
		return Costumer;
	}
	 public int getGuestsNumber()
	 {
		 return GuestsNumber;
	 }
	  
	public  Restaurant getRestaurant()
	{
		return Restaurant;
	}
	 
	 public DiningSpace getSpace()
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
