package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tavsagol")
public class TavSagol implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean canOpen;
	int maxPeopleInside;
	int maxPeopleOutside;
	boolean canSendDelivries;
	
	public TavSagol(boolean canOpen, int maxPeopleInside, int maxPeopleOutside, boolean canSendDelivries) {
		super();
		this.canOpen = canOpen;
		this.maxPeopleInside = maxPeopleInside;
		this.maxPeopleOutside = maxPeopleOutside;
		this.canSendDelivries = canSendDelivries;
	}

	public boolean isCanOpen() {
		return canOpen;
	}

	public void setCanOpen(boolean canOpen) {
		this.canOpen = canOpen;
	}

	public int getMaxPeopleInside() {
		return maxPeopleInside;
	}

	public void setMaxPeopleInside(int maxPeopleInside) {
		this.maxPeopleInside = maxPeopleInside;
	}

	public int getMaxPeopleOutside() {
		return maxPeopleOutside;
	}

	public void setMaxPeopleOutside(int maxPeopleOutside) {
		this.maxPeopleOutside = maxPeopleOutside;
	}

	public boolean isCanSendDelivries() {
		return canSendDelivries;
	}

	public void setCanSendDelivries(boolean canSendDelivries) {
		this.canSendDelivries = canSendDelivries;
	}

	public TavSagol() {
		// TODO Auto-generated constructor stub
	}

}
