package project.medical.DAO;

import project.medical.core.*;
import java.io.FileInputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class EventDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
		
	public EventDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
	}
	
	//  Get all Events 
	public  List<Event> getAllEvent() throws Exception {
		List<Event> listAllEvent = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from Event");
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				listAllEvent.add(tempEvent);
			}

			return listAllEvent;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	// Adding an Event  
	public void addEvent(Event newEvent) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into Event"
				+ " (name, date, description)"
				+ " values (?, ?, ?) " ;
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDate = formatter.format(newEvent.getDate());
		
		
		myStmt.setString(1, newEvent.getName());
		myStmt.setString(2, stringDate);
		myStmt.setString(3, newEvent.getDescription());
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one Event in table -> object Event 
	private Event convertRowToEvent(ResultSet myRs) throws SQLException {
		
		String name = myRs.getString("name");
		String StringDate = myRs.getString("date");
		String description = myRs.getString("description");
		Date dateinDate = null;
		try {
			dateinDate = formatter.parse(StringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	    Event tempEvent = new Event(name, dateinDate,description);
	    
	  
		return tempEvent;
	}

	// Delete an event according to event'name
	public void deleteEvent(String name) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			String sql  = "delete from Event where  name = ? ";
			
			myStmt  = myCon.prepareStatement(sql);
			
			myStmt.setString(1, name);
			
			myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
		
	}
	
	// Close connection
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
