package phase2;

import java.sql.Statement;

public class Reservations {
	public Reservations(){}
	
	//returns a string that lists suggestions based on what user reserved, see number 11) in project outline
	public String getSuggestedReservations(int id, Statement stmt){
		return null;
	}
	
	//makes a reservation given a userid and temp housing id
	public void makeReservation(int userID, int housingID, Statement stmt){
		
	}
	
	//returns a string that displays all reservations for the given user id
	public String displayReservation(int userID, Statement stmt){
		return null;
	}
	
	//checks that the user has a reservation with the given ids
	public boolean checkForReservation(int userID, int reservationID, Statement stmt){
		boolean result = false;
		return result;
	}
	
	//records a stay with the following reservationID information
	public void recordStay(int userID, int reservationID, Statement stmt){
		
	}
}
