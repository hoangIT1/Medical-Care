package project.medical.ui;

import javax.swing.table.AbstractTableModel;
import project.medical.core.Clinic;
import java.util.*;

public class ClinicTableModel extends  AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int CLINIC_NAME_COL = 1;
	private static final int ADDRESS_COL = 2;
	private static final int PHONE_COL = 3;
	private static final int EMAIL_COL = 4;
	private static final int TYPE_COL = 5;

	
	private String[] columnNames = {"No", "Clinic name", "Address", "Phone number", "Email", "Clinic Type"};
	List <Clinic> clinics;
	
	public ClinicTableModel(List <Clinic> clinics) {
		this.clinics = clinics;
	}

	@Override
	public int getRowCount() {
		return clinics.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Clinic tempClinic = clinics.get(row);
		
		switch (col) {
		case OBJECT_COL:
			return tempClinic;
		case ID_COL:
			return tempClinic.getID();
		case CLINIC_NAME_COL:
			return tempClinic.getClinicName();
		case ADDRESS_COL:
			return tempClinic.getAddress();
		case PHONE_COL:
			return tempClinic.getPhoneNum();
		case EMAIL_COL:
			return tempClinic.getEmail();
		case TYPE_COL:
			return tempClinic.getType();
		default:
			return tempClinic.getID();
		}
	
	}
	
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
