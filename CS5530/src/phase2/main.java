package phase2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		System.out.println("5. exit:");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connector con = null;
		String choice;
		String username = "";
		String sql = null;
		int currentUserId;
		int c = 0;
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

					User user = new User();
					if (user.login(username, password) == false) {
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

					User user = new User();
					// check for uniqueness of username
					while (user.checkForUsernameUniqueness(username) == false) {

						System.out.println(
								"The username " + username + " is already taken, please choose another username:");
						username = null;
						while ((username = in.readLine()) == null && username.length() == 0);
					}
					System.out.println(username + " is unique");

					String password;
					String name;
					String address;
					String phoneNumber;
					
					System.out.println("Please choose a password:");
					while ((password = in.readLine()) == null && password.length() == 0);
					System.out.println("Please enter your full name:");
					while ((name = in.readLine()) == null && name.length() == 0);
					System.out.println("Please enter your address:");
					while ((address = in.readLine()) == null && address.length() == 0);
					System.out.println("Please enter your phone number:");
					while ((phoneNumber = in.readLine()) == null && phoneNumber.length() == 0);

					if (user.registerNewUser(username, password, name, address, phoneNumber) == true) {
						System.out.println(username + " was successfully added to the system");
					} else {
						System.out
								.println("There was an error adding " + username + " to the system, please try again");
						username = password = name = address = phoneNumber = null;
						c = 0;
					}
				}
			}

			// reset choice
			c = 0;
			
			//get current user id
			User user = new User();
			currentUserId = user.userIdFromName(username);

			// main menu
			while (true) {
				displayMainMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0);
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {

					continue;
				}
				if (c < 1 | c > 5)
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
							System.out.println("reserve TH");
						} 
						else if (c == 2) // record new PH
						{
							System.out.println("record new PH");
						} 
						else if (c == 3) // update existing PH
						{
							System.out.println("update PH");
						} 
						else if (c == 4) // record stay
						{
							System.out.println("record stay");
						} 
						else if (c == 5) // make favorite
						{
							String nameOfTH;
							
							System.out.println("Please enter the name of your new favorite temporary housing:");
							while((nameOfTH = in.readLine()) == null && nameOfTH.length() == 0);
							
							TemporaryHousing th = new TemporaryHousing();
							th.makeFavorite(username, nameOfTH);
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
							
							TemporaryHousing th = new TemporaryHousing();
							
							if(th.checkTempHouseExists(nameOfTH) == false){
								System.out.println("No temporary housing with that name could be found");
								c = 0;
							}
							else
							{
								int score = -1;
								String comments = "";
								
								System.out.println("Please enter a score between 0 and 10 (0= terrible, 10 = excellent):");
								String line;
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
								
								Feedback feedback = new Feedback();
								feedback.recordFeedback(username, nameOfTH, date, score, comments);
							}
						} 
						else if (c == 2) // assess feedback
						{
							String nameOfTH;
							System.out.println("Which temporary housing would you like to assess feedback on?:");
							while((nameOfTH = in.readLine()) == null && nameOfTH.length() == 0);	
							
							TemporaryHousing th = new TemporaryHousing();
							
							if(th.checkTempHouseExists(nameOfTH) == false){
								System.out.println("No temporary housing with that name could be found");
								c = 0;
							}
							else
							{
								Feedback feedback = new Feedback();
								
								System.out.println(feedback.showFeedbackForTH(nameOfTH));
								System.out.println("Using the feedback ID, which feedback would you like to assess?:");
								int feedbackID;
								String line;
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
								
								feedback.assessFeedback(username, feedbackID, rating);
							}
						} 
						else if (c == 3) // rate user
						{
							String nameUserToBeRated;
							System.out.println("Which user would you like to rate?:");
							while((nameUserToBeRated = in.readLine()) == null && nameUserToBeRated.length() == 0);
							
							int idUserToBeRated = -1;
							idUserToBeRated = user.userIdFromName(nameUserToBeRated);
							
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

								user.rateUser(currentUserId, idUserToBeRated, rating);
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
					System.out.println(user.calculateDegreeOfSeparation(firstUserId, secondUserId));
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

							Statistics stat = new Statistics();
							System.out.println(stat.mostPopularTH(numResults));
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

							
							Statistics stat = new Statistics();
							System.out.println(stat.mostExpensiveTH(numResults));
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

							
							Statistics stat = new Statistics();
							System.out.println(stat.mostHighlyRatedPH(numResults));
						}
					}
					
					// reset value and go back to main menu
					c = 0;
				} 
				else 
				{
					//TODO: show the summary of TH reservations and confirm
					//TODO: then perform queries
					System.out.println("EoM");
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
