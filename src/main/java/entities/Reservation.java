package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enums.DiningType;
import enums.Status;

@Entity
@Table(name = "reservation")
public class Reservation implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Customer Customer;
	private int GuestsNumber;
	@OneToOne
	private Restaurant Restaurant;
	@OneToOne
	private DiningSpace Space;
	private LocalDateTime Time;
	@OneToOne
	private CreditCard CreditCard;
	
	private Status Status;
	private List<HealthReportSignature> GuestsSignatures;

	@Column
    @ElementCollection(targetClass=table.class)
	private List<table> tables;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		Customer = customer;
	}

	public void setGuestsNumber(int guestsNumber) {
		GuestsNumber = guestsNumber;
	}

	public void setRestaurant(Restaurant restaurant) {
		Restaurant = restaurant;
	}

	public void setSpace(DiningSpace space) {
		Space = space;
	}

	public void setTime(LocalDateTime time) {
		Time = time;
	}

	public void setCreditCard(CreditCard creditCard) {
		CreditCard = creditCard;
	}

	
	
	public List<table> getTables() {
		return tables;
	}

	public void setTables(List<table> tables) {
		this.tables = tables;
	}

	public Reservation() {
		super();
	}
	
	public Reservation(int GuestsNumber, DiningType Space, LocalDateTime Time)
	{
		this.GuestsNumber = GuestsNumber;
		this.Space = new DiningSpace();
		this.Space.setType(Space);/***********************************************************/
		this.Time = Time;
		GuestsSignatures = new ArrayList<HealthReportSignature>();/***********************************/
	}
	
	public Reservation(int id, Customer Customer, int GuestsNumber,DiningSpace Space, List<table> tables, LocalDateTime Time,  CreditCard CreditCard, Status Status, List<HealthReportSignature> GuestsSignatures , Restaurant Restaurant)
	{
		 this.id  = id;
		this.Customer = Customer;
		this.GuestsNumber = GuestsNumber;
		this.Restaurant = Restaurant;
		this.Space = Space;
		this.Time = Time;
		this.CreditCard = CreditCard;
		this.Status = Status;
		this.GuestsSignatures = GuestsSignatures;
		this.tables  = tables;
	}
	
	public Reservation(Customer Customer, int GuestsNumber,DiningSpace Space, LocalDateTime Time, CreditCard CreditCard, List<HealthReportSignature> GuestsSignatures, Restaurant Restaurant)
	{
		this.Customer = Customer;
		this.GuestsNumber = GuestsNumber;
		this.Restaurant = Restaurant;
		this.Space = Space;
		this.Time = Time;
		this.CreditCard = CreditCard;
		this.GuestsSignatures = GuestsSignatures;
	}
	
	public int getId()
	{
		return id;
	}
	public Customer getCustomer()
	{
		return Customer;
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
	 
	 
	 public void setStatus(Status status)
	 {
		Status = status; 
	 }
	 
	 public List<HealthReportSignature> getGuestsSignatures() {
			return GuestsSignatures;
		}

		public void addtoGuestsSignatures(HealthReportSignature guestsSignatures) {
			GuestsSignatures.add(guestsSignatures);
		}
}