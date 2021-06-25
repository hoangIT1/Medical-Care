package project.medical.ui;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;

import project.medical.core.Kid;



class KidTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  static final int OBJECT_COL = -1;
	private static final int STT_COL = 0;
	private static final int ID_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int FIRST_NAME_COL = 3;
	private static final int DATE_OF_BIRTH_COL = 4;
	private static final int GENDER_COL = 5;
	private static final int ADDRESS_COL = 6;
	private static final int EMAIL_COL = 7;
	private static final int PHONE_COL = 8;
	private static final int PARENTNAME_COL = 9;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


	private String[] columnNames = {"STT", "KidID","LastName","FirstName","DateOfBirth","Gender","Address","Email", "Phone", "ParentName"}; 
	private List<Kid> Kids;

	public KidTableModel(List<Kid> theKids) {
		Kids = theKids;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Kids.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Kid tempKid = Kids.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempKid;
		case STT_COL:
			return row+1;
		case ID_COL:
			return tempKid.getID();
		case LAST_NAME_COL:
			return tempKid.getLastName();
		case FIRST_NAME_COL:
			return tempKid.getFirstName();
		case GENDER_COL:
			return tempKid.getGender();
		case EMAIL_COL:
			return tempKid.getEmail();
		case DATE_OF_BIRTH_COL:
			Date tempDate = tempKid.getDateOfBirth();
			String tempDateInString = formatter.format(tempDate);
			return tempDateInString;
		case ADDRESS_COL:
			return tempKid.getAddress();
		case PHONE_COL:
			return tempKid.getPhoneNum();
		case PARENTNAME_COL:
			return tempKid.getParentName();
		default:
			return tempKid.getID();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}


