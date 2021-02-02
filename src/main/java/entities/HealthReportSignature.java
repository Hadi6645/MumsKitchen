package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "healthReportSignature")
public class HealthReportSignature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String guestId;
	
	public HealthReportSignature() {
		// TODO Auto-generated constructor stub
	}
	public HealthReportSignature(String name, String guestId) {
		super();
		this.name = name;
		this.guestId = guestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

}