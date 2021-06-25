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


public class KidDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public KidDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
	}
	
	// Get all Kids from table into a list
	public  List<Kid> getAllKid() throws Exception {
		
		List<Kid> listAllKid = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from kid");
			
			while (myRs.next()) {
				Kid tempKid = convertRowToKid(myRs);
				listAllKid.add(tempKid);
			}

			return listAllKid;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Get all Kids by name from table into a list 
	public  List<Kid> getKidByName(String name) throws Exception {
		List<Kid> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			name += "%";
			myStmt = myCon.prepareStatement("select * from Kid where firstName like ? or lastName like ? ");
			myStmt.setString(1, name);
			myStmt.setString(2, name);			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Kid tempKid = convertRowToKid(myRs);
				list.add(tempKid);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	//  Get all Kids by ID from table into a list "Person"
	public  Person getKidByID(String id) throws Exception {
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Person thisperson = null;

		try {
			
			myStmt = myCon.prepareStatement("select * from Kid where kidID = ?");
			myStmt.setString(1, id);		
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				Kid tempKid = convertRowToKid(myRs);
				thisperson = tempKid;
			}
			
			
			
			return thisperson;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Adding a Kid object to table
	public void addKid(Kid newKid) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into kid"
				+ "(kidID, lastName, firstName, dateOfBirth, address, email, phoneNum, gender, parentName)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?) " ;
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDate = formatter.format(newKid.getDateOfBirth());
		
		myStmt.setString(1, newKid.getID());
		myStmt.setString(2, newKid.getLastName());
		myStmt.setString(3, newKid.getFirstName());
		myStmt.setString(4, stringDate);
		myStmt.setString(5, newKid.getAddress());
		myStmt.setString(6, newKid.getEmail());
		myStmt.setString(7, newKid.getPhoneNum());
		myStmt.setString(8, newKid.getGender());
		myStmt.setString(9, newKid.getParentName());
		
		
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one Kid in table -> object Kid
	private Kid convertRowToKid(ResultSet myRs) throws SQLException, ParseException {
		String id = myRs.getString("kidID");
		String lastName = myRs.getString("lastName");
		String firstName = myRs.getString("firstName");
		String dateOfBirth = myRs.getString("dateOfBirth");
		String email = myRs.getString("email");
		String address = myRs.getString("address"); 
		String gender = myRs.getString("gender");
		String phoneNum = myRs.getString("phoneNum");
		String parentName = myRs.getString("parentName");
		Date tempDate = formatter.parse(dateOfBirth);
	    
		Kid tempKid = new Kid(id, lastName, firstName, tempDate, address, email, phoneNum, gender, parentName);
		
		return tempKid;
	}
	// Updating kid 
	public void updateKid(Kid temp) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			String sql  = "update kid "
					+ " set lastName = ?, firstName = ?, dateOfBirth=?, address= ?,email=?, phoneNum=?,"
					+ " gender = ?, parentName = ? "
					+ " where kidID = ? " ;
			
			myStmt  = myCon.prepareStatement(sql);
			
			
			String stringDate = formatter.format(temp.getDateOfBirth());
			
			myStmt.setString(1, temp.getLastName());
			myStmt.setString(2, temp.getFirstName());
			myStmt.setString(3, stringDate);
			myStmt.setString(4, temp.getAddress());
			myStmt.setString(5, temp.getEmail());
			myStmt.setString(6, temp.getPhoneNum());
			myStmt.setString(7, temp.getGender());
			myStmt.setString(8, temp.getParentName());
			myStmt.setString(9, temp.getID());			
			
			myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
		
	}
	// Delete a kid according to kidID
	public void deleteKid(String kidID) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			String sql  = "delete from kid where  kidID = ? ";
			
			myStmt  = myCon.prepareStatement(sql);
			
			myStmt.setString(1, kidID);
			
			myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
		
	}
	
	
	// Closing connection
	private static void close(Connection myCon, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
		} 
		
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}



}
