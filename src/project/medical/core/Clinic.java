package project.medical.core;

public class Clinic {
	protected String ID;
	protected String clinicName;
	protected String address;
	protected String phoneNum;
	protected String email;
	protected String type;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param iD
	 * @param clinicName
	 * @param address
	 * @param phoneNum
	 * @param email
	 * @param type
	 */
	public Clinic(String iD, String clinicName, String address, String phoneNum, String email, String type) {
		
		this.ID = iD;
		this.clinicName = clinicName;
		this.address = address;
		this.phoneNum = phoneNum;
		this.email = email;
		this.type = type;
	}
	
	public Clinic() {
		this.ID = null;
		this.clinicName = null;
		this.address = null;
		this.phoneNum = null;
		this.email = null;
		this.type = null;
	}
}
