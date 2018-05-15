package model;

import static javax.persistence.GenerationType.IDENTITY;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import design.DatabaseItem;
import utility.Display;

@Entity
@Table(name = "directions")
public class Directions extends DatabaseItem{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer directionsID;
	private String description;
	private int stepNumber;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private RecycleItem recycle;
	
	public Directions() {}
	
	public static class DirectionsBuilder{
		private String description;
		private int stepNumber;
		public DirectionsBuilder description(String description) {
			this.description = description;
			return this;
		}
		public DirectionsBuilder stepNumber(int stepNumber) {
			this.stepNumber = stepNumber;
			return this;
		}
		public Directions build() {
			return new Directions(this);
		}
	}
	private Directions(DirectionsBuilder builder) {
		this.description = builder.description;
		this.stepNumber = builder.stepNumber;
	}
	
	public Integer getDirectionsID() { return directionsID; }
	public void setDirectionsID(Integer directionsID) { this.directionsID = directionsID; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public int getStepNumber() { return stepNumber; }
	public void setStepNumber(int stepNumber) { this.stepNumber = stepNumber; }
	public RecycleItem getRecipe() { return recycle; }
	public void setRecipe(RecycleItem recycle) { this.recycle = recycle; }
	
	@Override
	public String toString() {
		return Display.getJSON(this);
	}
}
