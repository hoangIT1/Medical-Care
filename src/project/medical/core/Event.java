package project.medical.core;
import java.util.Date;
public class Event {
	private String name;
	private Date date;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Event(String name, Date date, String description) {
		this.name = name;
		this.date = date;
		this.description = description;
	}
	public Event() {
		
	}
	
}