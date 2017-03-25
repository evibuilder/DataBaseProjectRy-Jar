package phase2;

import java.sql.Statement;

public class Reservations {
	public Reservations(){}
	
	//returns a string that lists suggestions based on what user reserved, see number 11) in project outline
	public String getSuggestedReservations(int idOfTH, Statement stmt){
		return null;
	}
	
	//makes a reservation given a userid and temp housing id
	public void makeReservation(String username, int housingID, Statement stmt){
		
	}
	
	//returns a string that displays all reservations for the given user id
	public String displayReservation(String username, Statement stmt){
		return null;
	}
	
	//checks that the user has a reservation with the given ids
	public boolean checkForReservation(String username, int reservationID, Statement stmt){
		boolean result = false;
		return result;
	}
	
	//records a stay with the following reservationID information
	public void recordStay(String username, int reservationID, Statement stmt){
		
	}
}
