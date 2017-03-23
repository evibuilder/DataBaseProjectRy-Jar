package phase2;

import java.sql.*;

public class Login {
	
	public Login()
	{}
	
	//test a password against a current username, if the username doesn't exist or if the password doesn't match return false
	public boolean login(String username, String password){
		boolean result = false;
		
		return result;
	}
	
	//try to add a new user to the system
	public boolean registerNewUser(String username, String password, String name, String address, String phoneNumber){
		boolean result = false;
		
		return result; 
	}
	
	public boolean checkForUsernameUniqueness(String username){
		boolean result = true;
		
		return result;
	}
}
