package entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enums. DiningType;


@Entity
@Table(name = "dinningspace")
public class DiningSpace implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dine_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
    private DiningType type;
    private int Capacity;
    
    @ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = table.class)
    @JoinTable(
		name="dinningspace_tables",
		joinColumns = @JoinColumn(name = "id"),
		inverseJoinColumns = @JoinColumn(name = "tables_id"))
    private List<table> tablesList;
    
    private boolean isSmokingAllowed;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rest")
    private Restaurant rest;
    
    public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	public DiningSpace() {
		super();
		tablesList = new ArrayList<table>();
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
    	tablesList = new ArrayList<table>();
    }
    
    public void  addTable(table table)
    {
    	tablesList.add(table);
    	table.getDiningSpaces().add(this);
    }
    
    
    public int getId() {
		return id;
	}

	

	public int getCapacity()
    {
    	return Capacity;
    }
    
    public List<table> getTables() {
		return tablesList;
	}

	public void setTables(List<table> tables) {
		this.tablesList = tables;
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