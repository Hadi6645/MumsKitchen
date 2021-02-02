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
    
    public DiningType getType() {
		return type;
	}

	public void setType(DiningType type) {
		this.type = type;
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
    
    public List<table> getTables() {
		return tables;
	}

	public void setTables(List<table> tables) {
		this.tables = tables;
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