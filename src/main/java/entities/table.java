package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.App;
import clientServer.ChatClientCLI;
import clientServer.Server;

@Entity
@Table(name = "tables")
public class table {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tables_id;
	int Capacity;
	boolean isReserved;
	//private LocalDateTime Time;
	@ManyToMany(mappedBy = "tablesList",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = DiningSpace.class)
	List<DiningSpace> diningSpaces;

	public List<DiningSpace> getDiningSpaces() {
		return diningSpaces;
	}

	public void setDiningSpaces(List<DiningSpace> diningSpaces) {
		this.diningSpaces = diningSpaces;
	}
	
	public table() {
		super();
		diningSpaces = new ArrayList<DiningSpace>();
	}
	
	public table(int Capacity)
	{
		this.Capacity = Capacity;
		diningSpaces = new ArrayList<DiningSpace>();
	}
	
	public int getCapacity()
	{
		return Capacity;
	}
	
	public void setReserve(LocalDateTime time1, LocalDateTime time2)
	{
		isReserved = true;
	}
	public void setUnReserve(LocalDateTime time1, LocalDateTime time2)
	{
		isReserved = false;
	}
	
	public boolean isReserved(LocalDateTime time1, LocalDateTime time2)
	{
		return isReserved;
	}
	
	

}
