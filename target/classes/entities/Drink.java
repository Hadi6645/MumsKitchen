package entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import clientServer.ChatClientCLI;
import clientServer.Server;
import enums.Temperature;

@Entity
public class Drink extends Food{
	boolean isSparkling;
	double alcPercent;
	@Enumerated(EnumType.STRING)
	Temperature mytemp;
	
	public Drink() {
		super();
	}
	 
	public Drink(String name,String description,double price,boolean isSparkling,double alcPercent,Temperature temp) {
		super(name,description,price);
		this.isSparkling = isSparkling;
		this.alcPercent = alcPercent;
		this.mytemp = temp;
	}
	public double getalcPercent()
	{
		return alcPercent;
	}
	public boolean getSparke()
	{
		return isSparkling;
	}
	public void setSparkle(boolean bool)
	{
		isSparkling = bool;
	}
	public void setalcPercent(double percent)
	{
		alcPercent = percent;
	}
	@Override
	public boolean update() throws Exception
	{
		super.update();
		session = Server.getSession();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Drink drink ;
		drink = (Drink)session.get(Food.class, this.id);
		String input;
		double newPercent;
		boolean flag = false;
		System.out.print("alcohol percent change, type new percent or skip:\n");
		input = reader.readLine();
		if (!(input.length() == 0))
		{
			newPercent = Double.valueOf(input);
			flag = true;
		}
		else newPercent = drink.getalcPercent();
		
		if(flag == true && Server.authchange(drink))
		{
			drink.setalcPercent(newPercent);
			session.saveOrUpdate(drink);
			System.out.print("Changes made succesfully.");
			return true;
		}
		
		else {
			if(flag == false)
			{
				System.out.print("No Changes were done.");
				return false;
			}
			System.out.print("No authentication, changes were not done.");
			return false;
			}
		
	}
	@Override
	public void print()
	{
		super.print();
		if(isSparkling)
			System.out.print("Sparkling\n");
		System.out.print("The temperature is: ");
		if(mytemp == Temperature.HOT ) System.out.print("HOT");
		else System.out.print("COLD");
		if(alcPercent>0)
		{
			System.out.print("Alcohol Percent: ");
			System.out.print(alcPercent);
			System.out.print("\n");
		}
	
		
	}
}
