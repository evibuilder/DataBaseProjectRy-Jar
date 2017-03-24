package phase2;

public class Housing {

	public Housing(){}
	
	//for the given user, make the TH their favorite
	public void makeFavorite(int userID, int idOfTH){
		
	}
	
	//adds a new PH to the system
	public void addNewPH(String name, String address, String url, String yearBuilt){
		
	}
	
	//updates the existing PH with the new information
	public void updateCurrentPH(int id, String name, String address, String url, String yearBuilt){
		
	}
	
	//returns a string containing a list of PHs with the given name
	//return empty string if none are found
	public String showIDsFromGivenName(String phName){
		return "";
	}
	
	//returns a string that displays all columns of the given ph id
	public String displayPHinformation(int id){
		return null;
	}
	
	//returns a string that lists suggestions based on what user reserved, see number 11) in project outline
	public String getSuggestedReservations(int id){
		return null;
	}
	
	//makes a reservation given a userid and temp housing id
	public void makeReservation(int userID, int housingID){
		
	}
	
	//checks whether a TH exists
	public boolean checkHousingExists(int housingID){
		boolean result= false;
		return result;
	}
	
	//returns a string that displays all reservations for the given user id
	public String displayReservation(int userID){
		return null;
	}
	
	//returns a list of the available periods for the given house
	public String showAvailablePeriods(int housingID){
		return null;
	}
	
	//checks that an id exists in the period table
	public boolean checkPeriodExists(int periodID){
		boolean result = false;
		return result;
	}
	
	//checks that the user has a reservation with the given ids
	public boolean checkForReservation(int userID, int reservationID){
		boolean result = false;
		return result;
	}
	
	//records a stay with the following reservationID information
	public void recordStay(int userID, int reservationID){
		
	}
}
