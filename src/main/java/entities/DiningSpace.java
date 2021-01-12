package entities;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.Type;


@Entity
@Table(name = "dinningspace")
public class DiningSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private Type type;
    private int Capacity;
    private List<table> tables;
    
    private boolean isSmokingAllowed;
    
    public DiningSpace(Type type, boolean isSmokingAllowed)
    {
    	this.type = type;
    	this.isSmokingAllowed = isSmokingAllowed;
    }
    
    public void  addTable(table table)
    {
    	tables.add(table);
    }
    
    public int getCapacity()
    {
    	return Capacity;
    }
    
    @SuppressWarnings("null")
	public List<table> getNonReservedTables()
    {
    	List<table> NonReservedTables = null;
    	for(int i=0; i<tables.size(); i++)
    	{
    		if(tables.get(i).isReserved == false) NonReservedTables.add(tables.get(i));	
    	}
    	return NonReservedTables;
    	
    }
    
    public int getFreeSpaceCount()
    {
    	int counter= 0;
    	for(int i=0; i<tables.size(); i++)
    	{
    		if(tables.get(i).isReserved == false) counter ++;
    	}
    	return counter;
    }
    
    public boolean isSmoking()
    {
    	return isSmokingAllowed;
    }
    
    public Type getSpaceTpye()
    {
    	return type;
    }

    
    
   
    
	
}
