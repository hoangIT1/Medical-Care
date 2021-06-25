package project.medical.core;

import java.util.Date;

public class Mom extends Person{


	public Mom() {
		super();
	}

	public Mom(String iD, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum,
			HistoryMedical[] histories, WeightHeight[] fetalWH ) {
		super(iD, lastName, firstName, dateOfBirth, address, email, phoneNum,  histories, fetalWH);

	}

	public Mom(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum) {
		super(id, lastName, firstName, dateOfBirth, address, email, phoneNum);
	}
	

}