package phase2;

public class User {

	public User(){}
	
	
	// returns a string containing names and ids of all users
	public String showAllUsers(){
		return null;
	}
	
	//rates the user from the given user with the rating or trusted or not-trusted
	public void rateUser(int idOfUserDoingRating, int idOfUserGettingRated, String rating){
		
	}
	
	//returns a user id from the given user name
	public int userIdFromName(String name){
		return -1;
	}
	
	//checks whether the user is an admin
	public boolean isAdmin(String username){
		boolean result = false;
		
		return result;
	}
	
	//test a password against a current username, if the username doesn't exist or if the password doesn't match return false
	public boolean login(String username, String password){
		boolean result = true;
		
		return result;
	}
	
	//try to add a new user to the system
	public boolean registerNewUser(String username, String password, String firstName, String lastName, String address, String phoneNumber){
		boolean result = false;
		
		return result; 
	}
	
	public boolean checkForUsernameUniqueness(String username){
		boolean result = false;
		
		return result;
	}
	
	//calculate two degrees of separation
	public String calculateDegreeOfSeparation(int firstUserId, int secondUserId){
		
		return null;
	}
}
