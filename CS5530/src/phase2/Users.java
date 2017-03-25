package phase2;

import java.sql.ResultSet;
import java.sql.Statement;

public class Users {

	public Users(){}
	
	
	// returns a string containing names and ids of all users
	public String showAllUsers(Statement stmt){
		return null;
	}
	
	//rates the user from the given user with the rating or trusted or not-trusted
	public void rateUser(int idOfUserDoingRating, int idOfUserGettingRated, String rating, Statement stmt){
		
	}
	
	//returns a user id from the given user name
	public int userIdFromName(String name, Statement stmt){
		return -1;
	}
	
	//checks whether the user is an admin
	public boolean isAdmin(String username, Statement stmt){
		boolean result = false;
		
		return result;
	}
	
	//test a password against a current username, if the username doesn't exist or if the password doesn't match return false
	public boolean login(String username, String password, Statement stmt){
		boolean result = false;
		
		String sql="select password from Users where login='jshaw'";
		String output="";
		ResultSet rs=null;

		 	try{
   		 	rs=stmt.executeQuery(sql);
   		 	
   		 	if(rs.wasNull()){
   		 		result = false;
   		 	}else{
   		 		if(rs.getString(0).equals(password)){
   		 			result = true;
   		 		}
   		 	}
		     
		     rs.close();
		 	}
		 	catch(Exception e)
		 	{
		 		System.out.println("cannot execute the query");
		 	}
		 	finally
		 	{
		 		try{
   		 		if (rs!=null && !rs.isClosed())
   		 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("cannot close resultset");
		 		}
		 	}
		 	
		return result;
	}
	
	//try to add a new user to the system
	public boolean registerNewUser(String username, String password, String firstName, String lastName, String address, String phoneNumber, Statement stmt){
		boolean result = false;
		
		return result; 
	}
	
	public boolean checkForUsernameUniqueness(String username, Statement stmt){
		boolean result = false;
		
		return result;
	}
	
	//calculate two degrees of separation
	public String calculateDegreeOfSeparation(int firstUserId, int secondUserId, Statement stmt){
		
		return null;
	}
	
	//for the given user, make the TH their favorite
	public void makeHousingFavorite(int userID, int idOfTH, Statement stmt){
		
	}

}
