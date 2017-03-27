package phase2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistics {

	public Statistics(){}
	
	//returns as a string, a list of the most popular TH
	public String mostPopularTH(int numberOfResults, Statement stmt){
		return null;
	}
	
	//returns as a string, a list of the most expensive TH
	public String mostExpensiveTH(int numberOfResults, Statement stmt){
		return null;
	}
	
	//returns as a string, a list of the most highly rated PH
	public String mostHighlyRatedPH(int numberOfResults, Statement stmt){
		return null;
	}
	
	public String mostUsefulUsers(int numberOfResults, Statement stmt){
		String result = "";
		String sql = "SELECT login, AVG(rating) "
				+ "FROM Rates "
				+ "GROUP BY(login) "
				+ "ORDER BY(AVG(rating))DESC "
				+ "LIMIT " + numberOfResults;
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				result += rs.getString("login") + "\t" + rs.getDouble("AVG(rating)");
				result += "\n";
			}
		}		 	
		catch(SQLException e)
	 	{
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
	 	}
		
		return result;
	}
	
	public String mostTrustedUsers(int numberOfResults, Statement stmt){
		String result = "";
		String sql = "SELECT login2, count(isTrusted) "
				+ "FROM Trust "
				+ "GROUP BY(login2) "
				+ "ORDER BY(count(isTrusted))DESC "
				+ "LIMIT " + numberOfResults;
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				result += rs.getString("login2") + "\t" + rs.getInt("count(isTrusted)");
				result += "\n";
			}
		}		 	
		catch(SQLException e)
	 	{
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
	 	}
		
		return result;
	}
}
