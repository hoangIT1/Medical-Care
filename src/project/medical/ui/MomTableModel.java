package project.medical.ui;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;

import project.medical.core.Mom;


class MomTableModel extends AbstractTableModel {
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
	private static final int ADDRESS_COL = 5;
	private static final int EMAIL_COL = 6;
	private static final int PHONE_COL = 7;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


	private String[] columnNames = {"STT","MomID","LastName","FirstName","DateOfBirth","Address","Email", "Phone"}; 
	private List<Mom> Moms;

	public MomTableModel(List<Mom> theMoms) {
		Moms = theMoms;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Moms.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Mom tempMom = Moms.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempMom;
		case STT_COL:
			return row + 1 ;
		case ID_COL:
			return tempMom.getID();
		case LAST_NAME_COL:
			return tempMom.getLastName();
		case FIRST_NAME_COL:
			return tempMom.getFirstName();
		case EMAIL_COL:
			return tempMom.getEmail();
		case DATE_OF_BIRTH_COL:
			Date tempDate = tempMom.getDateOfBirth();
			String tempDateInString = formatter.format(tempDate);
			return tempDateInString;
		case ADDRESS_COL:
			return tempMom.getAddress();
		case PHONE_COL:
			return tempMom.getPhoneNum();
		default:
			return tempMom.getID();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}


