package phase2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
		String cname;
		String dname;
		String username;
		String password;
		String name;
		String address;
		String phoneNumber;
		String sql = null;
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
					System.out.println("Username:");
					while ((username = in.readLine()) == null && username.length() == 0);
					System.out.println("Password:");
					while ((password = in.readLine()) == null && password.length() == 0);

					Login log = new Login();
					if (log.login(username, password) == false) {
						System.out.println("The username or password is incorrect");
						c = 0;
					}else{
						System.out.println("Login successful");
					}
				} 
				else if (c == 2) // register
				{
					System.out.println("Please choose a username:");
					while ((username = in.readLine()) == null && username.length() == 0)
						;

					Login log = new Login();
					// check for uniqueness of username
					while (log.checkForUsernameUniqueness(username) == false) {

						System.out.println(
								"The username " + username + " is already taken, please choose another username:");
						username = null;
						while ((username = in.readLine()) == null && username.length() == 0);
					}
					System.out.println(username + " is unique");

					System.out.println("Please choose a password:");
					while ((password = in.readLine()) == null && password.length() == 0);
					System.out.println("Please enter your full name:");
					while ((name = in.readLine()) == null && name.length() == 0);
					System.out.println("Please enter your address:");
					while ((address = in.readLine()) == null && address.length() == 0);
					System.out.println("Please enter your phone number:");
					while ((phoneNumber = in.readLine()) == null && phoneNumber.length() == 0);

					if (log.registerNewUser(username, password, name, address, phoneNumber) == true) {
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
							System.out.println("make fav");
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
							System.out.println("record feedback");
						} 
						else if (c == 2) // assess feedback
						{
							System.out.println("assess feedback");
						} 
						else if (c == 3) // rate user
						{
							System.out.println("rate user");
						}
					}

					// reset value and go back to main menu
					c = 0;
				} 
				else if (c == 3) // two degrees of separation
				{
					String user1;
					String user2;
					System.out.println("Please enter the first username:");
					while((user1 = in.readLine()) == null && user1.length() == 0);
					System.out.println("Please enter the second username:");
					while((user2 = in.readLine()) == null && user2.length() == 0);
					
					//perform calculation
				} 
				else if (c == 4) // statistics
				{
					c = 0;
					
					while(c != 4){
						displayStatisticsMenu();
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						try {
							c = Integer.parseInt(choice);
						} catch (Exception e) {

							continue;
						}
						if (c < 1 | c > 4)
							continue;
						
						if (c == 1) // most popular TH
						{
							System.out.println("most popular TH");
						} 
						else if (c == 2) // most expensive TH
						{
							System.out.println("most expensive TH");
						} 
						else if (c == 3) // most highly rated PH
						{
							System.out.println("most highly rated PH");
						}
					}
					
					// reset value and go back to main menu
					c = 0;
				} 
				else 
				{
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
