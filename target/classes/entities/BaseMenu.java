package entities;

import javax.persistence.Entity;

@Entity
public class BaseMenu extends Menu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BaseMenu() {
		super();
		restaurant = null;
	}
}
