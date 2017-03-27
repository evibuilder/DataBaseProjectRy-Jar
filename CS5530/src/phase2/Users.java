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
		String sql="INSERT into Feedback(login1, login2, isTrusted) "
				+ "VALUES ("+userDoingRating+",'"+userGettingRated+"','"+rating+"')";
		
	}
	
	//checks whether the user is an admin
	public boolean isAdmin(String username, Statement stmt){
		boolean result = false;
		
		return result;
	}
	
	//check whether a user exists
	public boolean checkUserExists(String username, Statement stmt){
		boolean result = false;
		String sql="SELECT login from Users where login = '"+username+"'";
		
		ResultSet rs = null;
		
	 	try{
	 		
		 	rs = stmt.executeQuery(sql);
		 	rs.next();
		 	
	   		if(rs.getString("login").equals(username)){
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
		boolean result = true;
		
		String sql = "SELECT login FROM Users WHERE login = '"+username+"'";
		
		ResultSet rs = null;
		
	 	try{
	 		
		 	rs = stmt.executeQuery(sql);

		 	while(rs.next()){
		 		if(rs.getString("login").equals(username)){
		 			result = false;
		 		}
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
	
	//calculate two degrees of separation
	public String calculateDegreeOfSeparation(String firstUserLogin, String secondUserLogin, Statement stmt){
		//getting the separation degree of login 1 and login 2
		String result = null;
		String sql1 = "select count(*) "
						+ "from (select * "
							+"from Favorites "
							+"where login = '"+firstUserLogin+"')As f1, "
							+"(select * "
						+   "from Favorites "
						+ "where login = '"+secondUserLogin+"')As f2 "
				         + "where f1.hid = f2.hid";
		ResultSet rs = null;
		try{
	 		
   		 	rs = stmt.executeQuery(sql1);
   		 	rs.next();
   		 	
   	   		String sep = rs.getString("count(*)");
   		 	

		     rs.close();
		     int isep = Integer.parseInt(sep);
		     if(isep >= 1)
		     {
		    	 result = "1";
		     }
		     else
		     {
		    	 String sql2 = "select count(*) "
							+ "from (select * "
								+"from Favorites "
								+"where login = '"+firstUserLogin+"')As f1, "
								+"(select * "
							+   "from Favorites "
							+ "where login != '"+firstUserLogin+"')As f2 "
					         + "where f1.hid = f2.hid ";
		    	 ResultSet rs1 = null;
		    	 try{
				 		
		    		 	
		    		 	rs1 = stmt.executeQuery(sql2);
		    		 	rs1.next();
		    		 	
		    		 	String sep2 = rs1.getString("count(*)");
		    		 	int isep2 = Integer.parseInt(sep2);
		    		 	String a;
		    		 	if(isep2 >= 1)
		    		 	{
		    		 		a = "1";
		    		 	}
		    		 	else
		    		 	{
		    		 		a = "0";
		    		 	}
		    		 	

		 		     rs1.close();
		 		    String sql3 = "select count(*) "
							+ "from (select * "
								+"from Favorites "
								+"where login = '"+secondUserLogin+"')As f1, "
								+"(select * "
							+   "from Favorites "
							+ "where login != '"+secondUserLogin+"')As f2 "
					         + "where f1.hid = f2.hid" ;
		 		    ResultSet rs2 = null;
		 		   try{
				 		
		 	   		 	rs2 = stmt.executeQuery(sql3);
		 	   		 	rs2.next();
		 	   		 	String sep3 = rs2.getString("count(*)");
		 	   	   		 	

		 			     rs2.close();
		 			     int isep3 = Integer.parseInt(sep3);
		 			     String b;
		 			     if(isep3 >= 1)
		 			     {
		 			    	 b = "1";
		 			     }
		 			     else
		 			     {
		 			    	 b = "0";
		 			     }
		 			     if(a.equals("1") && b.equals("1"))
		 			     {
		 			    	 result = "2";
		 			     }
		 			     else
		 			     {
		 			    	 result = "0";
		 			     }
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
		     }
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
