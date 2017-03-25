package phase2;

import java.io.BufferedReader;


import javafx.util.*;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.sql.*;
import java.io.*;

import com.mysql.jdbc.Statement;

public class main {

	public static void displayLoginMenu() {
		System.out.println("        Welcome to the Uotel System     ");
		System.out.println("1. Login:");
		System.out.println("2. Register:");
		System.out.println("please enter your choice:");
	}

	public static void displayMainMenu() {
		System.out.println("        Main Menu    ");
		System.out.println("1. Temporary Housing:");
		System.out.println("2. Feedback:");
		System.out.println("3. Two degrees of separation:");
		System.out.println("4. Statistics:");
		System.out.println("5. Administration:");
		System.out.println("6. exit:");
		System.out.println("please enter your choice:");
	}

	public static void displayTemporaryHousingMenu() {
		System.out.println("        Temporary Housing Menu     ");
		System.out.println("1. Reserve TH:");
		System.out.println("2. Record new permanent housing:");
		System.out.println("3. Update existing permanent housing:");
		System.out.println("4. Record stay:");
		System.out.println("5. Make favorite");
		System.out.println("6. Browse temporary housing:");
		System.out.println("7. Back to main menu:");
		System.out.println("please enter your choice:");
	}

	public static void displayFeedbackMenu() {
		System.out.println("        Feedback Menu    ");
		System.out.println("1. Record feedback:");
		System.out.println("2. Assess existing feedback:");
		System.out.println("3. Rate user:");
		System.out.println("4. Back to main menu:");
		System.out.println("please enter your choice:");
	}

	public static void displayStatisticsMenu() {
		System.out.println("        Statistics Menu    ");
		System.out.println("1. Most popular temporary housing:");
		System.out.println("2. Most expensive temporary housing:");
		System.out.println("3. Most highly rated permanent housing:");
		System.out.println("4. Back to main menu:");
		System.out.println("please enter your choice:");
	}
	
	public static String displayReservations(){
		return null;
	}
	
	public static String displayStays(){
		return null;
	}

	public static void main(String[] args) {
		// TODO add functionality for numbers 9, 10, 11, 14

		Connector con = null;
		String choice;
		String username = "";
		String sql = null;
		int currentUserId;
		int c = 0;
 
		Set<Tuple> reservationsInCart = new TreeSet<Tuple>(); 
		Set<Integer> staysInCart = new TreeSet<Integer>();
		
		Housing housing = new Housing();
		Statistics stats = new Statistics();
		Users user = new Users();
		Reservations reservations = new Reservations();
		Feedback feedback = new Feedback();
		Keywords keywords = new Keywords();
		Period period = new Period();
		
		try {
			// remember to replace the password
			System.out.println("Attemping to connect to database...");
			con = new Connector();
			System.out.println("Database connection established");

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			// login
			while (c != 1 && c != 2) {
				
				displayLoginMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0)
					;
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {

					continue;
				}
				if (c < 1 | c > 2)
					continue;

				if (c == 1) // login
				{
					String password;
					
					System.out.println("Username:");
					while ((username = in.readLine()) == null && username.length() == 0);
					System.out.println("Password:");
					while ((password = in.readLine()) == null && password.length() == 0);

					if (user.login(username, password, con.stmt) == false) {
						System.out.println("The username or password is incorrect");
						c = 0;
					}else{
						System.out.println("Login successful");
					}
				} 
				else if (c == 2) // register
				{
					System.out.println("Please choose a username:");
					while ((username = in.readLine()) == null && username.length() == 0);

					// check for uniqueness of username
					while (user.checkForUsernameUniqueness(username, con.stmt) == false) {

						System.out.println(
								"The username " + username + " is already taken, please choose another username:");
						username = null;
						while ((username = in.readLine()) == null && username.length() == 0);
					}
					System.out.println(username + " is unique");

					String password;
					String firstName;
					String lastName;
					String address;
					String phoneNumber;
					
					System.out.println("Please choose a password:");
					while ((password = in.readLine()) == null && password.length() == 0);
					System.out.println("Please enter your first name:");
					while ((firstName = in.readLine()) == null && firstName.length() == 0);
					System.out.println("Please enter your last name:");
					while ((lastName = in.readLine()) == null && lastName.length() == 0);
					System.out.println("Please enter your address:");
					while ((address = in.readLine()) == null && address.length() == 0);
					System.out.println("Please enter your phone number:");
					while ((phoneNumber = in.readLine()) == null && phoneNumber.length() == 0);

					if (user.registerNewUser(username, password, firstName, lastName, address, phoneNumber, con.stmt) == true) {
						System.out.println(username + " was successfully added to the system");
					} else {
						System.out
								.println("There was an error adding " + username + " to the system, please try again");
						username = password = firstName = lastName = address = phoneNumber = null;
						c = 0;
					}
				}
			}

			// reset choice
			c = 0;
			
			//get current user id
			currentUserId = user.userIdFromName(username, con.stmt);

			// main menu
			while (true) {
				displayMainMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0);
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {

					continue;
				}
				if (c < 1 | c > 6)
					continue;
				if (c == 1) // temporary housing
				{
					c = 0;
					while (c != 7) {
						displayTemporaryHousingMenu();
						while ((choice = in.readLine()) == null && choice.length() == 0);
						try {
							c = Integer.parseInt(choice);
						} catch (Exception e) {

							continue;
						}
						if (c < 1 | c > 7)
							continue;
						if (c == 1) // reserve TH
						{
							String nameOfTH;
							System.out.println("Which temporary housing would you like to reserve?:");
							while ((nameOfTH = in.readLine()) == null && nameOfTH.length() == 0);
							
							String results = housing.showIDsFromGivenName(nameOfTH, con.stmt);
							
							if(results.equals("")){
								System.out.println("No housing was found with that name");
								c = 0;
							}
							else
							{
								System.out.println("The following temporary housing were found");
								System.out.println(results);
								System.out.println("Please select the ID of the temporary housing you would like to reserve:");
								String line;
								int idOfTH;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									idOfTH = Integer.parseInt(line);
								}catch(Exception e){
									System.out.println(e.getMessage());
									continue;
								}
								
								if(housing.checkHousingExists(idOfTH, con.stmt) == false){
									System.out.println("That was not a valid temporary housing ID:");
									c = 0;
									continue;
								}
								else
								{
									System.out.println("These are the available reservation peroids for this temporary housing:");
									System.out.println(period.showAvailablePeriods(idOfTH, con.stmt));
									System.out.println("Please select the ID of the period for which you would like to reserve:");
									line = null;
									while ((line = in.readLine()) == null && line.length() == 0);
									int periodID;
									try{
										periodID = Integer.parseInt(line);
									}catch(Exception e){
										System.out.println(e.getMessage());
										c = 0;
										continue;
									}
									Tuple newReservation = new Tuple(currentUserId, idOfTH, periodID);
									reservationsInCart.add(newReservation);

									System.out.println("The reservation was added to your cart");
									
									System.out.println("Users that recently reserved " + nameOfTH + " also visited the following temporary housing");
									System.out.println(reservations.getSuggestedReservations(idOfTH, con.stmt));
								}
							}
						} 
						else if (c == 2) // record new PH
						{
							String name;
							String address;
							String url;
							String yearBuilt;
							
							System.out.println("Enter the details for the new permanent housing:");
							System.out.println("Name:");
							while((name = in.readLine()) == null && name.length() == 0);
							
							System.out.println("Address:");
							while((address = in.readLine()) == null && address.length() == 0);
							
							System.out.println("URL:");
							while((url = in.readLine()) == null && url.length() == 0);
							
							System.out.println("Year built:");
							while((yearBuilt = in.readLine()) == null && yearBuilt.length() == 0);
							
							housing.addNewPH(name, address, url, yearBuilt, con.stmt);
							System.out.println(name + " was added to the system:");
						} 
						else if (c == 3) // update existing PH
						{
							String nameOfPH;
							System.out.println("Please enter the name of the permanent housing you would like to update:");

							while((nameOfPH = in.readLine()) == null && nameOfPH.length() == 0);
							
							String results = housing.showIDsFromGivenName(nameOfPH, con.stmt);
							
							if(results.equals("")){
								System.out.println("No housing was found with that name");
								c = 0;
							}
							else
							{
								System.out.println("The following permanent housing were found");
								System.out.println(results);
								System.out.println("Please select the ID of the permanent house you would like to update:");
								String line;
								int idOfPh;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									idOfPh = Integer.parseInt(line);
								}catch(Exception e){
									System.out.println(e.getMessage());
									continue;
								}
								
								if(housing.checkHousingExists(idOfPh, con.stmt)){
								
									System.out.println("\tCurrent information");
									System.out.println(housing.displayPHinformation(idOfPh, con.stmt));
									System.out.println();
									System.out.println("Please enter updated information");
									
									String name;
									String address;
									String url;
									String yearBuilt;
									
									System.out.println("Name:");
									while((name = in.readLine()) == null && name.length() == 0);
									
									System.out.println("Address:");
									while((address = in.readLine()) == null && address.length() == 0);
									
									System.out.println("URL:");
									while((url = in.readLine()) == null && url.length() == 0);
									
									System.out.println("Year built:");
									while((yearBuilt = in.readLine()) == null && yearBuilt.length() == 0);
									
									housing.updateCurrentPH(idOfPh, name, address, url, yearBuilt, con.stmt);
								}
								else{
									System.out.println("That was not a valid ID:");
								}
							}
						}
						else if (c == 4) // record stay
						{
							System.out.println("Here is a list of your reservations:");

							System.out.println(reservations.displayReservation(currentUserId, con.stmt));
							
							System.out.println("Please select the id of the reservation you would like to record a stay:");
							String line;
							while (( line = in.readLine()) == null && line.length() == 0);
							int reservationID;
							try{
								reservationID = Integer.parseInt(line);
							}catch(Exception e){
								System.out.println(e.getMessage());
								c = 0;
								continue;
							}
							
							if(reservations.checkForReservation(currentUserId, reservationID, con.stmt)){
								staysInCart.add(reservationID);
								System.out.println("That stay was added to your cart:");
							}else{
								System.out.println("That was not a valid reservation ID:");
								c = 0;
							}
						} 
						else if (c == 5) // make favorite
						{
							String nameOfTH;
							
							System.out.println("Please enter the name of your new favorite temporary housing:");
							while((nameOfTH = in.readLine()) == null && nameOfTH.length() == 0);
							
							String results = housing.showIDsFromGivenName(nameOfTH, con.stmt);
							
							if(results.equals("")){
								System.out.println("No housing was found with that name");
								c = 0;
							}
							else
							{
								System.out.println("The following permanent housing were found");
								System.out.println(results);
								System.out.println("Please select the ID of the permanent house you would like to update:");
								String line;
								int idOfTh;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									idOfTh = Integer.parseInt(line);
								}catch(Exception e){
									System.out.println(e.getMessage());
									continue;
								}
								
								if(housing.checkHousingExists(idOfTh, con.stmt)){
									user.makeHousingFavorite(currentUserId, idOfTh, con.stmt);
								}
								else{
									System.out.println("That was not a valid ID");
								}

							}
						} 
						else if (c == 6) // browse TH
						{
							System.out.println("browse TH");
						}
					}

					// reset value and go back to main menu
					c = 0;
				} else if (c == 2) // feedback
				{
					c = 0;

					while (c != 4) {
						displayFeedbackMenu();
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						try {
							c = Integer.parseInt(choice);
						} catch (Exception e) {

							continue;
						}
						if (c < 1 | c > 4)
							continue;

						if (c == 1) // record feedback
						{
							String nameOfTH;
							System.out.println("Which temporary housing would you like to record feedback on?:");
							while((nameOfTH = in.readLine()) == null && nameOfTH.length() == 0);
							
							String results = housing.showIDsFromGivenName(nameOfTH, con.stmt);
							
							if(results.equals("")){
								System.out.println("No temporary housing with that name could be found");
								c = 0;
							}
							else
							{
								System.out.println("The following temporary housing were found");
								System.out.println(results);
								System.out.println("Please select the ID of the temporary housing you would like to give feedback on:");
								String line;
								int idOfTH;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									idOfTH = Integer.parseInt(line);
								}catch(Exception e){
									System.out.println(e.getMessage());
									continue;
								}
								
								int score = -1;
								String comments = "";
								
								System.out.println("Please enter a score between 0 and 10 (0= terrible, 10 = excellent):");
								line = null;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									score = Integer.parseInt(line);
								}catch(Exception e){
									continue;
								}

								while(score < 0 || score > 10){
									System.out.println("That was not a valid score");
									System.out.println("Please enter a score between 0 and 10 (0= terrible, 10 = excellent):");
									while((line = in.readLine()) == null && line.length() == 0);
									try{
										score = Integer.parseInt(line);
									}catch(Exception e){
										continue;
									}
								}
								
								System.out.println("Enter any comments (Enter to skip):");
								while((comments = in.readLine()) == null);
								
								String date;
								DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
								Date dateobj = new Date();
								date = df.format(dateobj);
								
								feedback.recordFeedback(username, idOfTH, date, score, comments, con.stmt);
							}
						} 
						else if (c == 2) // assess feedback
						{
							String nameOfTH;
							System.out.println("Which temporary housing would you like to assess feedback on?:");
							while((nameOfTH = in.readLine()) == null && nameOfTH.length() == 0);	
							
							String results = housing.showIDsFromGivenName(nameOfTH, con.stmt);
							
							if(results.equals("")){
								System.out.println("No temporary housing with that name could be found");
								c = 0;
							}
							else
							{
								System.out.println("The following temporary housing were found");
								System.out.println(results);
								System.out.println("Please select the ID of the temporary housing you would like to give feedback on:");
								String line;
								int idOfTH;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									idOfTH = Integer.parseInt(line);
								}catch(Exception e){
									System.out.println(e.getMessage());
									continue;
								}
								
								
								System.out.println(feedback.showFeedbackForTH(idOfTH, con.stmt));
								System.out.println("Using the feedback ID, which feedback would you like to assess?:");
								int feedbackID;
								line = null;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									feedbackID = Integer.parseInt(line);
								}catch(Exception e){
									System.out.println(e.getMessage());
									throw e;
								}
								
								System.out.println("What rating would you like to give this feedback?:");
								System.out.println("(0 = useless, 1 = useful, 2 = very useful)");
								int rating = -1;
								line = null;
								while((line = in.readLine()) == null && line.length() == 0);
								try{
									rating = Integer.parseInt(line);
								} catch (Exception e){
									continue;
								}
								
								while(rating < 0 || rating > 3){
									line = null;
									System.out.println("That was not a valid rating, please try again:");
									System.out.println("What rating would you like to give this feedback?:");
									System.out.println("(0 = useless, 1 = useful, 2 = very useful)");
									while((line = in.readLine()) == null && line.length() == 0);
									try{
										rating = Integer.parseInt(line);
									} catch (Exception e){
										continue;
									}
								}
								
								feedback.assessFeedback(username, feedbackID, rating, con.stmt);
							}
						} 
						else if (c == 3) // rate user
						{
							String nameUserToBeRated;
							System.out.println("Which user would you like to rate?:");
							while((nameUserToBeRated = in.readLine()) == null && nameUserToBeRated.length() == 0);
							
							int idUserToBeRated = -1;
							idUserToBeRated = user.userIdFromName(nameUserToBeRated, con.stmt);
							
							if(idUserToBeRated == -1){
								System.out.println("user " + nameUserToBeRated + " could not be found");
								c = 0;
							}
							else
							{
								int i = 0;
								while(i != 1 && i != 2)
								{
									System.out.println("Please select the rating you would like to give this user:");
									System.out.println("1. Trusted:");
									System.out.println("2. Not-trusted:");
										
									String lineToBeParsed;
									while((lineToBeParsed = in.readLine()) == null && lineToBeParsed.length() == 0);
									try{
											i = Integer.parseInt(lineToBeParsed);
									}catch (Exception e){
											continue;
									}
								}
									
								String rating = "";
								if(i == 1) {rating = "Trusted";}
								else if(i == 2) {rating = "Not-Trusted";}

								user.rateUser(currentUserId, idUserToBeRated, rating, con.stmt);
							}

						}
						
					}

					// reset value and go back to main menu
					c = 0;
				} 
				else if (c == 3) // two degrees of separation
				{
					int firstUserId;
					int secondUserId;
					
					System.out.println("The following are all the users of the system:");
					System.out.println("Please select the ID of the first user:");
					String line;
					while((line =in.readLine())== null && line.length() == 0);
					try{
						firstUserId = Integer.parseInt(line);
					}catch(Exception e){
						System.out.println("Invalid ID");
						c = 0;
						break;
					}
					
					System.out.println("Please select the ID of the second user:");
					line = null;
					while((line = in.readLine()) == null && line.length() == 0);
					try{
						secondUserId = Integer.parseInt(line);
					}catch(Exception e){
						System.out.println("Invalid ID");
						c = 0;
						break;
					}
					
					//perform calculation
					System.out.println(user.calculateDegreeOfSeparation(firstUserId, secondUserId, con.stmt));
				} 
				else if (c == 4) // statistics
				{
					c = 0;
					int numResults;
					while(c != 4){
						displayStatisticsMenu();
						while ((choice = in.readLine()) == null && choice.length() == 0);
						try {
							c = Integer.parseInt(choice);
						} catch (Exception e) {

							continue;
						}
						if (c < 1 | c > 4)
							continue;
						
						if (c == 1) // most popular TH
						{
							System.out.println("How many results would you like to display?:");
							String line;
							while((line = in.readLine()) == null && line.length() == 0);
							try{
								numResults = Integer.parseInt(line);
							}catch (Exception e){
								System.out.println(e.getMessage());
								throw e;
							}

							System.out.println(stats.mostPopularTH(numResults, con.stmt));
						} 
						else if (c == 2) // most expensive TH
						{
							System.out.println("How many results would you like to display?:");
							String line;
							while((line = in.readLine()) == null && line.length() == 0);
							try{
								numResults = Integer.parseInt(line);
							}catch (Exception e){
								System.out.println(e.getMessage());
								throw e;
							}

							
							System.out.println(stats.mostExpensiveTH(numResults, con.stmt));
						} 
						else if (c == 3) // most highly rated PH
						{
							System.out.println("How many results would you like to display?:");
							String line;
							while((line = in.readLine()) == null && line.length() == 0);
							try{
								numResults = Integer.parseInt(line);
							}catch (Exception e){
								System.out.println(e.getMessage());
								throw e;
							}

							
							System.out.println(stats.mostHighlyRatedPH(numResults, con.stmt));
						}
					}
					
					// reset value and go back to main menu
					c = 0;
				} 
				else if(c == 5) //administration
				{
					if(user.isAdmin(username, con.stmt)){
						//TODO: fill out admin rewards
					}
					else
					{
						System.out.println("You do not have admin privileges");
						c = 0;
					}
				}
				else 
				{
					System.out.println("Please review and confirm the following reservations:");
					System.out.println(displayReservations());
					int i = 0;
					
					System.out.println("1. Confirm reservations:");
					System.out.println("2. Cancel reservations:");
					String answer;
					while ((answer = in.readLine()) == null && answer.length() == 0);
					try{
						i = Integer.parseInt(answer);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					
					
					if(i == 1){
						/*for (int item : reservations){
							th.makeReservation(currentUserId, item);
						}*/
						System.out.println("Reservations were recorded:");
					}else{
						System.out.println("Reservations were discarded:");
					}
					
					System.out.println("Please review and confirm the following stays:");
					System.out.println(displayStays());
					System.out.println("1. Confirm stays:");
					System.out.println("2. Cancel stays:");
					answer = null;
					while ((answer = in.readLine()) == null && answer.length() == 0);
					try{
						i = Integer.parseInt(answer);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					
					if(i == 1){
						for (int item : staysInCart){
							reservations.recordStay(currentUserId, item, con.stmt);
						}
						System.out.println("Stays were recorded:");
					}
					else{
						System.out.println("Stays were discarded:");
					}
					
					con.stmt.close();

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Either connection error or query execution error!");
		} finally {
			if (con != null) {
				try {
					con.closeConnection();
					System.out.println("Database connection terminated");
				}

				catch (Exception e) {
					/* ignore close errors */ }
			}
		}
	}

}
