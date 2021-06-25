package project.medical.DAO;

import project.medical.core.*;

import java.io.FileInputStream;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;



public class HistoryMedicalDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private String today;
	private String tomorrow;

	private KidDAO kidDAO;
	private MomDAO momDAO;
	public HistoryMedicalDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);

		
		kidDAO = new KidDAO();
		momDAO = new MomDAO();
		Calendar calendar = Calendar.getInstance();
	    Date todaytemp = calendar.getTime();
	    calendar.add(Calendar.DAY_OF_YEAR, 1);
	    Date tomorrowtemp = calendar.getTime();
		this.today = formatter.format(todaytemp);
		this.tomorrow = formatter.format(tomorrowtemp);
		
	}
	
	//  Get all HistoryMedicals of "a person " according to person ID 
	public  List<HistoryMedical> getHistoryMedicalByName(String theIDPerson) throws Exception {
		List<HistoryMedical> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			//theIDPerson += "%";
			myStmt = myCon.prepareStatement("select * from medicalhistory where personID = ? ");
			myStmt.setString(1, theIDPerson);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				HistoryMedical tempHistoryMedical = convertRowToHistoryMedical(myRs);
				list.add(tempHistoryMedical);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Adding a HistoryMedical object to table according to person ID 
	public void addHistoryMedical(HistoryMedical newHistoryMedical, String thePersonID) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into medicalhistory "
				+ " (personID, dateOfInjection, typeOfVaccine, IDVaccine, address, interaction, imageHist, nextAppointment)"
				+ "  values (?, ? ,?, ?, ?, ? ,?, ?)"; 
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDateInjection = formatter.format(newHistoryMedical.getDateOfInjection());
		String stringDateNextAppoint = formatter.format(newHistoryMedical.getNextAppointment());

		myStmt.setString(1, thePersonID );
		myStmt.setString(2, stringDateInjection);
		myStmt.setString(3, newHistoryMedical.getTypeOfVaccine());
		myStmt.setInt(4, newHistoryMedical.getIDVaccine());
		myStmt.setString(5, newHistoryMedical.getAddress());
		myStmt.setString(6, newHistoryMedical.getInteraction());
		myStmt.setString(7, newHistoryMedical.getImageHist());
		myStmt.setString(8, stringDateNextAppoint);

		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	// Converting one HistoryMedical in table -> object HistoryMedical 
	private HistoryMedical convertRowToHistoryMedical(ResultSet myRs) throws SQLException {
		
		String stringDateInjection = myRs.getString("dateOfInjection");
		String stringDateNextAppoint =  myRs.getString("nextAppointment");
		Date dateInjectionInDate = null, nextAppoinmentInDate = null;
		try {
			dateInjectionInDate = formatter.parse(stringDateInjection);
			nextAppoinmentInDate = formatter.parse(stringDateNextAppoint);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String interaction = myRs.getString("interaction");
		String typeOfVaccine = myRs.getString("typeOfVaccine");
		int iDVaccine = myRs.getInt("IDVaccine");
		String address = myRs.getString("address");
		String imageHist = myRs.getString("imageHist");
		
	    HistoryMedical tempHistoryMedical = new HistoryMedical(dateInjectionInDate, typeOfVaccine, iDVaccine, address, interaction, imageHist, nextAppoinmentInDate);
	    
		return tempHistoryMedical;
	}
	// GETTING PERSON TODAY APPOINMENT
	public List<Person> getPersonToday() throws Exception {
		List<Person> todayPeople = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from medicalhistory ");
			
			while (myRs.next()) {
				String cur_ID = myRs.getString("personID");

				
				String tempStringDate = myRs.getString("nextAppointment");
				if (tempStringDate.equals(today)) {
					
					Person eKid =  kidDAO.getKidByID(cur_ID);
					Person eMom =  momDAO.getMomByID(cur_ID);
					
					
					if (eMom != null) {
						todayPeople.add(eMom);
					} else if ( eKid != null) {
						todayPeople.add(eKid);
					}
					
				}
				
			}
			return todayPeople;		
		}
		finally {
			close(myStmt, myRs);
		}
		
		
	}
	
	// GETTING PERSON TOMMOROW APPOINMENT
	public List<Person> getPersonTomorrow() throws Exception {
		List<Person> tomPeople = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from medicalhistory ");
			
			while (myRs.next()) {
				String cur_ID = myRs.getString("personID");
				String tempStringDate = myRs.getString("nextAppointment");
				if (tempStringDate.equals(tomorrow)) {
					Person eKid = kidDAO.getKidByID(cur_ID);
					Person eMom =  momDAO.getMomByID(cur_ID);
					
					if (eMom != null) {
						tomPeople.add(eMom);
					} else if ( eKid != null) {
						tomPeople.add(eKid);
					}
					
				}
				
			}

			return tomPeople;		
		}
		finally {
			close(myStmt, myRs);
		}
		
		
	}

	
	// Delete history according to id
	public void deleteHist(String id) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			String sql  = "delete from medicalhistory where  personID = ? ";
			
			myStmt  = myCon.prepareStatement(sql);
			
			myStmt.setString(1, id);
			
			myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}
	
	private static void close(Connection myCon, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		} 
		
		if (myCon != null) {
			myCon.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	

}