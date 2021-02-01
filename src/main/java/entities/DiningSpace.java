package entities;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enums. DiningType;


@Entity
@Table(name = "dinningspace")
public class DiningSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private DiningType type;
    private int Capacity;
    
    @Column
    @ElementCollection(targetClass=table.class)
    private List<table> tables;
    
    private boolean isSmokingAllowed;
    
    public DiningSpace() {
		super();
	}
    
    public DiningSpace( DiningType type, boolean isSmokingAllowed)
    {
    	this.type = type;
    	this.isSmokingAllowed = isSmokingAllowed;
    }
    
    public void  addTable(table table)
    {
    	tables.add(table);
    }
    
    
    public int getId() {
		return id;
	}

	

	public int getCapacity()
    {
    	return Capacity;
    }
    
   
	public List<table> getNonReservedTables(LocalDateTime time1, LocalDateTime time2)
    {
    	List<table> NonReservedTables= Collections.emptyList();

    	for(int i=0; i<tables.size(); i++)
    	{
    		if(tables.get(i).isReserved(time1, time2) == false) NonReservedTables.add(tables.get(i));	
    	}
    	return NonReservedTables;
    	
    }
    
    public List<table> getTables() {
		return tables;
	}

	public void setTables(List<table> tables) {
		this.tables = tables;
	}

	public int getFreeSpaceCount(LocalDateTime time1, LocalDateTime time2)
    {
    	int counter= 0;
    	for(int i=0; i<tables.size(); i++)
    	{
    		if(tables.get(i).isReserved(time1, time2) == false) counter += tables.get(i).getCapacity();
    	}
    	return counter;
    }
    
    public boolean isSmoking()
    {
    	return isSmokingAllowed;
    }
    
    public DiningType getSpaceTpye()
    {
    	return type;
    }

    
    
   
    
	
}
