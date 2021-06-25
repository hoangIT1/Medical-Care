package project.medical.ui;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;

import project.medical.core.HistoryMedical;


class VaccinationTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  static final int OBJECT_COL = -1;
	private static final int DATEOFINJECTION_COL = 0;
	private static final int TYPEVACCINE_COL = 1;
	private static final int IDVACCINE_COL = 2;
	private static final int ADDRESS_COL = 3;
	private static final int INTERACTION_COL = 4;
	private static final int NEXTAPPOINTMENT_COL = 5;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


	private String[] columnNames = {"DateOfInjection","TypeOfVaccine","IDVaccine","Address","Interaction", "NextAppointment"}; 
	private List<HistoryMedical> HistoryMedicals;

	public VaccinationTableModel(List<HistoryMedical> theHistoryMedicals) {
		HistoryMedicals = theHistoryMedicals;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return HistoryMedicals.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		HistoryMedical tempHistoryMedical = HistoryMedicals.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempHistoryMedical;
		case DATEOFINJECTION_COL:
			String String1 = formatter.format(tempHistoryMedical.getDateOfInjection());
			return String1;
		case TYPEVACCINE_COL:
			return tempHistoryMedical.getTypeOfVaccine();
		case IDVACCINE_COL:
			return tempHistoryMedical.getIDVaccine();
		case ADDRESS_COL:
			return tempHistoryMedical.getAddress();
		case INTERACTION_COL:
			return tempHistoryMedical.getInteraction();
		case NEXTAPPOINTMENT_COL:
			String String2 = formatter.format(tempHistoryMedical.getNextAppointment());
			return String2;
		default:
			return tempHistoryMedical;
		
		}
	}
	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}


