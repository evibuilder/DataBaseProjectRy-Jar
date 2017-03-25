package phase2;

import java.sql.Statement;

public class Housing {

	public Housing(){}
	
	//adds a new PH to the system
	public void addNewPH(String name, String address, String url, String yearBuilt, Statement stmt){
		
	}
	
	//updates the existing PH with the new information
	public void updateCurrentPH(int id, String name, String address, String url, String yearBuilt, Statement stmt){
		
	}
	
	//returns a string containing a list of PHs with the given name
	//return empty string if none are found
	public String showIDsFromGivenName(String phName, Statement stmt){
		return "";
	}
	
	//returns a string that displays all columns of the given ph id
	public String displayPHinformation(int id, Statement stmt){
		return null;
	}
	
	//checks whether a TH exists
	public boolean checkHousingExists(int housingID, Statement stmt){
		boolean result= false;
		return result;
	}
}
