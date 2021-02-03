package entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.Server;
import enums.FoodOrder_Status;

@Entity
@Table(name = "foodorder")
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private List<Food> Order;
	private float TotalPrice;
	@OneToOne(mappedBy="foodOrder",cascade = {CascadeType.ALL})
	private Customer Customer;
	private float ShippingFee;
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="foodorder_id")
	private Transaction Payment;
	private LocalDateTime Time; 
	@Enumerated(EnumType.STRING)
	private FoodOrder_Status Status;
	
	private static Session session;
	
	public FoodOrder() {
		super();
	}
	
	public FoodOrder(int id, List<Food> order, float totalPrice, Customer Customer, float shippingFee, Transaction payment, LocalDateTime time, FoodOrder_Status status) {
		this.id = id;
		Order = order;
		TotalPrice = totalPrice;
		this.Customer = Customer;
		ShippingFee = shippingFee;
		Payment = payment;
		Time = time; 
		Status = status;
	}
	
	public FoodOrder(List<Food> order, float totalPrice, Customer Customer, float shippingFee, Transaction payment) {
		Order = order;
		TotalPrice = totalPrice;
		this.Customer = Customer;
		ShippingFee = shippingFee;
		Payment = payment;
	}
	public void setAllRelations(Customer customer, Transaction transaction)
	{
		session = Server.getSession();
		this.setCustomer(customer);
		customer.setFoodOrder(this);
		session.save(customer);
		this.setPayment(transaction);
		transaction.setFoodOrder(this);
		session.save(transaction);
		session.save(this);
	}
	
	public Customer getCustomer() {
		return Customer;
	}

	public void setCustomer(Customer customer) {
		Customer = customer;
	}

	public void setOrder(List<Food> order) {
		Order = order;
	}

	public void setPayment(Transaction payment) {
		Payment = payment;
	}

	public int getID() {
		return id;
	}
	public List<Food> getOrder() {
		return Order;
	}
	public float getTotalPrice() {
		return TotalPrice;
	}
	public Customer getClient() {
		return Customer;
	}
	public float getShippingFee() {
		return ShippingFee;
	}
	public Transaction getPayment() {
		return Payment;
	}
	public LocalDateTime getTime() {
		return Time;
	}
	public FoodOrder_Status getStatus() {
		return Status;
	} 
	public void setStatus(FoodOrder_Status status) {
		Status = status;
	}
}
