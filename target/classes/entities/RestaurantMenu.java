package entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


public class RestaurantMenu {

	int id;
	
	//@Basic
//	@Type( type = BaseMenuType.class )
	BaseMenu common;
	Menu indvidual;
	
	public RestaurantMenu(BaseMenu common,Menu ind) {
		this.common = common;
		indvidual = ind;
	}
	public BaseMenu getcommon()
	{
		return common;
	}
	public Menu getindv()
	{
		return indvidual;
	}
}

