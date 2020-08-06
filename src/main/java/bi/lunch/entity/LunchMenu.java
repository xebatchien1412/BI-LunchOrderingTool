package bi.lunch.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lunch_menu database table.
 * 
 */
@Entity
@Table(name="lunch_menu")
@NamedQuery(name="LunchMenu.findAll", query="SELECT l FROM LunchMenu l")
public class LunchMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	private int price;

	public LunchMenu() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}