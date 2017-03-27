package phase2;

import java.sql.Statement;

public class Feedback {

	public Feedback(){}
	
	
	// records the score and comments for the given TH from the given user
	public void recordFeedback(String username, int idOfTH, String date, int score, String comments, Statement stmt){
		String sql="INSERT into Feedback(score, text, fbdate, hid, login) "
				+ "VALUES ("+score+",'"+comments+"','"+date+"',"+idOfTH+",'"+username+"')";
	}
	
	// rates the feedback from the given user, with a rating of 0,1, or 2
	public void assessFeedback(String username, int feedbackID, int rating, Statement stmt){
		String sql="INSERT into Rates(login, fid, rating) "
				+ "VALUES ('"+username+"',"+feedbackID+","+rating+")";
	}
	
	//returns a list of the feedback for a given TH
	public String showFeedbackForTH(int idOfTH, Statement stmt){
		String sql = "SELECT * "
				+ "FROM Feedback "
				+ "WHERE hid = " + idOfTH;
		return null;
	}
}
