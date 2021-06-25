package project.medical.core;

import java.util.Date;

public class Kid extends Person{
	private String parentName;
	private String gender;
	

	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	public Kid() {
		super();
		this.gender = null;
	}
	public Kid(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum, String parentName,
			HistoryMedical[] histories, String gender, WeightHeight[] kidWH) {
		super(id, lastName, firstName, dateOfBirth, address, email, phoneNum, histories, kidWH);
		this.gender = gender;		
		this.parentName = parentName;
	}
	public Kid(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum,
			String gender, String parentName) {
		super(id, lastName, firstName, dateOfBirth, address, email, phoneNum);
		this.gender = gender;
		this.parentName = parentName;

	}

    
    


}