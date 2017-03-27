package phase2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Feedback {

	public Feedback(){}
	
	
	// records the score and comments for the given TH from the given user
	public String recordFeedback(String username, int idOfTH, String date, int score, String comments, Statement stmt){
		String result = "";
		int rowsChanged = 0;
		
		String sql="INSERT into Feedback(score, text, fbdate, hid, login) "
				+ "VALUES ("+score+",'"+comments+"','"+date+"',"+idOfTH+",'"+username+"')";
		
		try{
   		 	rowsChanged = stmt.executeUpdate(sql);
   		 	
   		 	if(rowsChanged > 0){
   		 		result = "Feedback was recorded:";
   		 	}else{
   		 		result = "There was an error processing the request:";
   		 	}
   		 	
		 	}
		 	catch(SQLException e)
		 	{
				System.err.println("cannot execute the query");
				System.err.println("error: " + e.getMessage());
		 	}
		return result;
	}
	
	// rates the feedback from the given user, with a rating of 0,1, or 2
	public String assessFeedback(String username, int feedbackID, int rating, Statement stmt){
		String sql="INSERT into Rates(login, fid, rating) "
				+ "VALUES ('"+username+"',"+feedbackID+","+rating+")";
		
		String result = "";
		
		int rowsChanged;
		try{
   		 	rowsChanged = stmt.executeUpdate(sql);
   		 	
   		 	if(rowsChanged > 0){
   		 		result = "Your assessment was recorded:";
   		 	}else{
   		 		result = "There was an error processing the request:";
   		 	}
   		 	
		 	}
		 	catch(SQLException e)
		 	{
				System.err.println("cannot execute the query");
				System.err.println("error: " + e.getMessage());
		 	}
		
		return result;
	}
	
	//returns a list of the feedback for a given TH
	public String showFeedbackForTH(int idOfTH, Statement stmt){
		
		String result = "";
		String sql = "SELECT * "
				+ "FROM Feedback "
				+ "WHERE hid = " + idOfTH;
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				result += rs.getInt("fid") + "\t" + rs.getInt("score") + "\t" + rs.getString("text") + "\t\t" + rs.getDate("fbdate").toString() + "\t" 
				+ rs.getString("login");
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
	
	//rates the user from the given user with the rating or trusted or not-trusted
	public String rateUser(String userDoingRating, String userGettingRated, int rating, Statement stmt){
		String result = "";
		String sql="INSERT into Trust(login1, login2, isTrusted) "
				+ "VALUES ('"+userDoingRating+"','"+userGettingRated+"',"+rating+")";
		
		int rowsChanged;
		try{
   		 	rowsChanged = stmt.executeUpdate(sql);
   		 	
   		 	if(rowsChanged > 0){
   		 		result = "Your rating was recorded:";
   		 	}else{
   		 		result = "There was an error processing the request:";
   		 	}
   		 	
		}
		catch(SQLException e)
		{
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
		}
		
		return result;
	}
	
	public boolean duplicateFeedback(String username, int hid, Statement stmt){
		boolean result = false;
		
		String sql = "select * from Feedback where login = '"+username+"' AND hid = "+hid+"";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			if(rs.next()) result = true;
		}		 	
		catch(SQLException e)
	 	{
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
	 	}
		
		
		return result;
	}
	
	public boolean assessingOwnFeedback(String username, int fid, Statement stmt){
		boolean result = false;

		String sql = "select login from Feedback where fid = "+fid+"";
		
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);

			rs.next();
			if(rs.getString("login").equals(username)){
				result = true;
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








