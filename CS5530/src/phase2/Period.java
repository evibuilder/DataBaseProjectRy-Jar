package phase2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Period {

	public Period(){}
	
	//returns a list of the available periods for the given house
	public String showAvailablePeriods(int housingID, Statement stmt){
		return null;
	}
	
	//checks that an id exists in the period table
	public boolean checkPeriodExists(int periodID, Statement stmt){
		boolean result = false;
		return result;
	}
	
	public void displayPeriod(int hid, String username, Statement stmt){
		String sql="SELECT pid FROM Reserve WHERE login = '"+username+"' AND hid = "+hid+"";
		List<Integer> periods = new ArrayList<Integer>();
		
		ResultSet rs = null;
		
		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				periods.add(rs.getInt("pid"));
			}
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		
		for(Integer pid : periods){
			printIndividualPeriod(pid, stmt);
		}
	}
	
	private void printIndividualPeriod(int pid, Statement stmt){
		String sql = "SELECT * FROM Period WHERE pid = "+pid+"";
		
		ResultSet rs = null;
		
		try{
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				System.out.println(rs.getString("pid") + "  " + rs.getDate("from").toString() + "  " + rs.getDate("to").toString());
			}
		}catch(SQLException e){
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
	}
}
