package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enums.UpdateMenuRequest_Status;

public class UpdateMenuRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Menu NewMenu;
	private UpdateMenuRequest_Status Status;
	
	public UpdateMenuRequest(Menu newMenu, UpdateMenuRequest_Status status) {
		NewMenu = newMenu;
		Status = status;
	}
	public Menu getMenu() {
		return NewMenu;
	}
	public UpdateMenuRequest_Status getStatus() {
		return Status;
	}
	public void setStatus( UpdateMenuRequest_Status status) {
		Status = status;
	}
}

