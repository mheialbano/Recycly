package model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import design.DatabaseItem;
import utility.Display;

@Entity
@Table(name = "materials")
public class Materials extends DatabaseItem{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer materialID;
	private String name;
	public Integer getMaterialID() { return materialID; }
	public void setMaterialID(Integer materialID) { this.materialID = materialID; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Override
	public String toString() {
		return Display.getJSON(this);
	}
}
