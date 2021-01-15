package entities;

import java.time.LocalDate;

public class NumberByDate {

	private LocalDate Date;
	private int Number;
	
	public NumberByDate(LocalDate Date, int Number)
	{
		this.Date = Date;
		this.Number = Number;
	}
	
	public LocalDate getDate()
	{
		return Date;
	}
	
	public int  getNumber()
	{
		return Number;
	}
	
}
