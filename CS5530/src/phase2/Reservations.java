package phase2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Reservations {
	public Reservations(){}
	
	//returns a string that lists suggestions based on what user reserved, see number 11) in project outline
	public String getSuggestedReservations(int idOfTH, Statement stmt){
		String result = "";
		String sql = "SELECT hid "
				   + "FROM Reserve r1 "
				   + "WHERE EXISTS   (SELECT * " 
						     	   + "FROM Reserve r2 "
								   + "WHERE r1.login = r2.login AND r2.hid = "+idOfTH+")";
		
		Set<Integer> hids = new TreeSet<Integer>();
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int value = rs.getInt(1);
				if(value != idOfTH){
					hids.add(value);
				}
			}
		}
		catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		
		for(Integer hid : hids){
			result += suggestedResHelper(hid, stmt);
			result += "\n";
		}
		
		return result;
	}
	
	private String suggestedResHelper(int hid, Statement stmt){
		String result = "";
		
		String sql = "SELECT * FROM Housing WHERE hid = "+hid+"";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
			rs.next();
			result += rs.getInt("hid") + "\t" + rs.getString("name") + "\t" +
					rs.getString("address") + "\t" + rs.getString("category") +
					"\t" + rs.getString("URL");
		}
		catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		
		return result;
	}
		
	//makes a reservation given a userid and temp housing id
	public String makeReservation(String username, int housingID, int periodID, Statement stmt){
		int cost = getPriceForReservation(housingID, periodID, stmt);
		
		String result = "";
		int affectedRows = 0;
		
		String sql = "INSERT INTO Reserve (login, hid, pid, cost) "
				+ "VALUES ('"+username+"',"+housingID+","+periodID+","+cost+")";
		
		try{
			affectedRows = stmt.executeUpdate(sql);
			
			if(affectedRows == 0){
				result = "There was an error processing the request:";
			}else{
				result = "Reservation was recorded:";
			}
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		return result;
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
		
		String sql = "SELECT * FROM Reserve WHERE login = '"+username+"' AND hid = "+housingID+" AND pid = "+periodID+"";
		
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
	
	private int getPriceForReservation(int hid, int pid, Statement stmt){
		int price = 0;
		String sql = "SELECT price FROM Available WHERE hid ="+hid+" AND pid = "+pid+"";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			rs.next();
			price = rs.getInt("price");
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}finally{
	 		try{
   		 		if (rs!=null && !rs.isClosed())
   		 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("cannot close resultset");
		 		}
		}
		
		return price;
	}
	
	
	private int getCostOfStay(String username, int hid, int pid, Statement stmt){
		int cost = 0;
		String sql = "SELECT cost FROM Reserve WHERE login = '"+username+"' AND hid ="+hid+" AND pid = "+pid+"";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			rs.next();
			cost = rs.getInt("cost");
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}finally{
	 		try{
   		 		if (rs!=null && !rs.isClosed())
   		 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("cannot close resultset");
		 		}
		}
		
		return cost;
	}
	
	//records a stay with the following reservationID information
	public String recordStay(String username, int hid, int pid, Statement stmt){
		
		int cost = getCostOfStay(username, hid, pid, stmt);
		
		String result = "";
		int affectedRows = 0;
		
		String sql = "INSERT INTO Visit (login, hid, pid, cost) "
				+ "VALUES ('"+username+"',"+hid+","+pid+","+cost+")";
		
		try{
			affectedRows = stmt.executeUpdate(sql);
			
			if(affectedRows == 0){
				result = "There was an error processing the request:";
			}else{
				result = "Stay was recorded:";
			}
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		return result;
	}
	
	public String removeReservation(String username, int hid, int pid, Statement stmt){
		String result = "";
		String sql = "DELETE FROM Reserve WHERE login ='"+username+"' AND hid = "+hid+" AND pid = "+pid+"";
		
		int rowsAffected = 0;
		
		try{
			rowsAffected = stmt.executeUpdate(sql);
			
			if(rowsAffected == 0){
				result = "There was an error processing the request:";
			}else{
				result = "Reservation was completed:";
			}
			
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		return result;
	}
}




















