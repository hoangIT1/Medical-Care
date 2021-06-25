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


public class WeightHeightDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public WeightHeightDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
	}
	
	//  Get all WeightHeights of "a person " according to person ID 
	public  List<WeightHeight> getWeightHeightByID(String theIDPerson) throws Exception {
		List<WeightHeight> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			theIDPerson += "%";
			myStmt = myCon.prepareStatement("select * from weightheight where personID = ? ");
			myStmt.setString(1, theIDPerson);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				WeightHeight tempWeightHeight = convertRowToWeightHeight(myRs);
				list.add(tempWeightHeight);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	// Adding a WeightHeight object to table according to person ID 
	public void addWeightHeight(WeightHeight newWeightHeight, String thePersonID) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into WeightHeight"
				+ "(personID, date, weight, height)"
				+ " values (?, ? ,?, ?) " ;
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDate = formatter.format(newWeightHeight.getDate());
		
		myStmt.setString(1, thePersonID );
		myStmt.setString(2, stringDate);
		myStmt.setInt(3, newWeightHeight.getWeight());
		myStmt.setInt(4, newWeightHeight.getHeight());
		
			
		
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one WeightHeight in table -> object WeightHeight (with out ID)
	private WeightHeight convertRowToWeightHeight(ResultSet myRs) throws SQLException {
		
		String StringDate = myRs.getString("date");
		
		Date dateinDate = null;
		try {
			dateinDate = formatter.parse(StringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int weight = myRs.getInt("weight");
		int height = myRs.getInt("height");
		
	    WeightHeight tempWeightHeight = new WeightHeight(height, weight, dateinDate);
	    
		return tempWeightHeight;
	}
	
	// Get BMI according personID
	public  double getBmiByID(String theIDPerson) throws Exception {
		List<WeightHeight> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = myCon.prepareStatement("select * from weightheight where personID = ? ");
			myStmt.setString(1, theIDPerson);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				WeightHeight tempWeightHeight = convertRowToWeightHeight(myRs);
				list.add(tempWeightHeight);
			}
			
			WeightHeight latestWH = list.get(list.size()-1); 
			double height = latestWH.getHeight();
			double weight = latestWH.getWeight();
			height = height/100;
			double bmi = weight/ (height * height);
			return bmi;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Delete WeightHeight history according to personID
		public void deleteWH(String id) throws SQLException {
			PreparedStatement myStmt = null;
			try {
				String sql  = "delete from weightheight where personID = ? ";
				
				myStmt  = myCon.prepareStatement(sql);
				
				myStmt.setString(1, id);
				
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
