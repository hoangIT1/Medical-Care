package project.medical.core;
import java.util.Date;

public class WeightHeight {
	private int height; 
	private int weight;
	private Date date;
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public WeightHeight(int height, int weight, Date date) {
		this.height = height;
		this.weight = weight;
		this.date = date;
	}
	
	public WeightHeight() {
		this.height = 0;
		this.weight = 0;
		this.date = null;
	
	}

}