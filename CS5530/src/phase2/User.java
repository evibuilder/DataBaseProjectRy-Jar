package phase2;

public class User {

	public User(){}
	
	//returns a string containing a list of users with thier ID's, with the given name
	//return empty string if no users were found
	public String showUsersWithName(String name){
		return "";
	}
	
	//rates the user from the given user with the rating or trusted or not-trusted
	public void rateUser(String nameUserDoingRating, int idOfUserGettingRated, String rating){
		
	}
	
	//test a password against a current username, if the username doesn't exist or if the password doesn't match return false
	public boolean login(String username, String password){
		boolean result = true;
		
		return result;
	}
	
	//try to add a new user to the system
	public boolean registerNewUser(String username, String password, String name, String address, String phoneNumber){
		boolean result = false;
		
		return result; 
	}
	
	public boolean checkForUsernameUniqueness(String username){
		boolean result = false;
		
		return result;
	}
}
