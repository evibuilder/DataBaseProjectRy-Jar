package phase2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reservations {
	public Reservations(){}
	
	//returns a string that lists suggestions based on what user reserved, see number 11) in project outline
	public String getSuggestedReservations(int idOfTH, Statement stmt){
		return null;
	}
	
	//makes a reservation given a userid and temp housing id
	public void makeReservation(String username, int housingID, int periodID, Statement stmt){
		
	}
	
	//returns a string that displays all reservations for the given user id
	public void displayReservations(String username, Statement stmt){
		List<Integer> housingIDs = new ArrayList<Integer>();
		
		String sql = "SELECT hid FROM Reserve WHERE login = '"+username+"'";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();

			while(rs.next()){
				housingIDs.add(rs.getInt("hid"));
			}
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		
		Housing housing = new Housing();
		
		for(Integer id : housingIDs){
			System.out.println(housing.displayPHinformation(id, stmt));
		}
	}
	
	//checks that the user has a reservation with the given housing id
	public boolean checkForReservation(String username, int housingID, int periodID, Statement stmt){
		boolean result = false;
		
		String sql = "SELECT * FROM Reserve WHERE login = '"+username+"', hid = "+housingID+", pid = "+periodID+"";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			if(rs.next()) result = true;
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		
		return result;
	}
	
	//records a stay with the following reservationID information
	public void recordStay(String username, int hid, int pid, Statement stmt){
		
	}
}
