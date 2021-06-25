package project.medical.core;

import java.util.Date;

public class  Person {
	protected String ID; // khóa chính để tìm "String"
	protected String lastName; 
	protected String firstName;
	protected Date dateOfBirth;
	protected String address;
	protected String phoneNum;
	protected String email;
	protected HistoryMedical [] histories;
	protected WeightHeight [] wH;
	
	
	public WeightHeight[] getwH() {
		return wH;
	}
	public void setwH(WeightHeight[] wH) {
		this.wH = wH;
	}
	public HistoryMedical[] getHistories() {
		return histories;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public void setHistories(HistoryMedical[] histories) {
		this.histories = histories;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Person(String iD, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum,
			HistoryMedical[] histories, WeightHeight [] wH) {
		ID = iD;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.email = email;
		this.phoneNum = phoneNum;
		this.histories = histories;
		this.wH = wH;
	}
	
	public Person() {
		this.ID = null;
		this.lastName = null;
		this.firstName = null;
		this.dateOfBirth = null;
		this.address = null;
		this.email = null;
		this.histories = null;
		this.phoneNum = null;
		this.wH = null;
	}
	public Person(String iD, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum) {
		this.ID = iD;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.email = email;
		this.histories = null;
		this.phoneNum = phoneNum;
	}
	
	
	
	

}