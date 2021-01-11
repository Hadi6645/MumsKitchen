package entities;

import javax.persistence.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

import clientServer.App;
import clientServer.ChatClientCLI;
import clientServer.Server;

@Entity
@Table(name = "table")
public class table {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int Capacity;
	boolean isReserved;
	
	public table(int Capacity)
	{
		this.Capacity = Capacity;
	}
	
	public int getCapacity()
	{
		return Capacity;
	}
	
	public void Reserve()
	{
		isReserved = true;
	}
	public void unReserve()
	{
		isReserved = false;
	}
	
	public boolean isReserved()
	{
		return isReserved;
	}
	
	

}
