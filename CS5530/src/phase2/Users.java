package phase2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users {

	public Users(){}
	
	
	// returns a string containing names and ids of all users
	public String showAllUsers(Statement stmt){
		return null;
	}
	
	//rates the user from the given user with the rating or trusted or not-trusted
	public void rateUser(String userDoingRating, String userGettingRated, String rating, Statement stmt){
		
	}
	
	//checks whether the user is an admin
	public boolean isAdmin(String username, Statement stmt){
		boolean result = false;
		
		return result;
	}
	
	public boolean isValidUsername(String username, Statement stmt){
		boolean result = false;
		
		return result;
	}
	
	//test a password against a current username, if the username doesn't exist or if the password doesn't match return false
	public boolean login(String username, String password, Statement stmt){
		boolean result = false;
		
		String sql="SELECT password from Users where login = '"+username+"'";

		ResultSet rs = null;
		
		 	try{
		 		
   		 	rs = stmt.executeQuery(sql);
   		 	rs.next();
   		 	
   	   		if(rs.getString("password").equals(password)){
   	   			result = true;
   	   		}
   		 	

		     rs.close();
		 	}
		 	catch(SQLException e)
		 	{
				System.err.println("cannot execute the query");
				System.err.println("error: " + e.getMessage());
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
		int rowsChanged = 0;
		
		String sql="INSERT into Users (login, password, firstname, lastname, address, phonenumber, admin)"
				+ "VALUES ('"+username+"','"+password+"','"+firstName+"','"+lastName+"','"+address+"','"+phoneNumber+"', 0)";
		
		 	try{
   		 	rowsChanged = stmt.executeUpdate(sql);
   		 	
   		 	if(rowsChanged > 0){
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
	
	public boolean checkForUsernameUniqueness(String username, Statement stmt ){
		boolean result = false;
		
		return result;
	}
	
	//calculate two degrees of separation
	public String calculateDegreeOfSeparation(String firstUserLogin, String secondUserLogin, Statement stmt){
		
		return null;
	}
	
	//for the given user, make the TH their favorite
	public String makeHousingFavorite(String userLogin, int idOfTH, Statement stmt){
		String result = "";
		int rowsChanged = 0;
		
		String sqlDate = LocalDate.getSQLDate();
		
		String sql = "UPDATE Favorites "
				+ "SET hid = "+idOfTH+", fvDate = '"+sqlDate+"' WHERE login = '"+userLogin+"'";
		
		try{
			rowsChanged = stmt.executeUpdate(sql);
			if(rowsChanged == 0){
				result = "There was an error updating the system";
			}else{
				result = "New Favorite was successfully updated";
			}
		}	
		catch(SQLException e)
	 	{
			System.err.println("cannot execute the query");
			System.err.println("error: " + e.getMessage());
			System.err.println(sqlDate);
	 	}
		
		return result;
	}

}
