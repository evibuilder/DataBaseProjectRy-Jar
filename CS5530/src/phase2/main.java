package phase2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import cs5530.Course;

public class main {

	public static void displayLoginMenu(){
		System.out.println("        Welcome to the Uotel System     ");
		System.out.println("1. Login:");
		System.out.println("2. Register:");
   	 	System.out.println("please enter your choice:");
	}
	
	public static void displayMainMenu()
	{
    	 System.out.println("1. search a course by cname and dname:");
    	 System.out.println("2. enter your own query:");
    	 System.out.println("3. exit:");
    	 System.out.println("please enter your choice:");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connector con=null;
		String choice;
        String cname;
        String dname;
        String username;
        String password;
        String name;
        String address;
        String phoneNumber;
        String sql=null;
        int c=0;
         try
		 {
			//remember to replace the password
			 	 con= new Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             
	             //login
	             while(c != 1 || c != 2){
	            	 displayLoginMenu();
	            	 while ((choice = in.readLine()) == null && choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 
	            		 continue;
	            	 }
	            	 if (c<1 | c>2)
	            		 continue;
	            	 
	            	 if(c == 1){//login
	            		 System.out.println("Username:");
	            		 while((username = in.readLine()) == null && username.length() == 0);
	            		 System.out.println("Password:");
	            		 while((password = in.readLine()) == null && password.length() == 0);
	            		 
	            		 Login log = new Login();
	            		 if(log.login(username, password) == false){
	            			 System.out.println("The username or password is incorrect");
	            			 c = 0;
	            		 }
	            	 }
	            	 else if(c == 2){//register
	            		 System.out.println("Please choose a username:");
	            		 while((username = in.readLine()) == null && username.length() == 0);
	            		 
	            		 Login log = new Login();
	            		 //check for uniqueness of username
	            		 while(log.checkForUsernameUniqueness(username) == false){

	            			 System.out.println("The username " + username + " is already taken, please choose another username:");
	            			 username = null;
		            		 while((username = in.readLine()) == null && username.length() == 0);
	            		 }
	            		 System.out.println(username + " is unique");
	            		 
	            		 System.out.println("Please choose a password:");
	            		 while((password = in.readLine()) == null && password.length() == 0);
	            		 System.out.println("Please enter your full name:");
	            		 while((name = in.readLine()) == null && name.length() == 0);
	            		 System.out.println("Please enter your address:");
	            		 while((address = in.readLine()) == null && address.length() == 0);
	            		 System.out.println("Please enter your phone number:");
	            		 while((phoneNumber = in.readLine()) == null && phoneNumber.length() == 0);
	            		 
	            		 if(log.registerNewUser(username, password, name, address, phoneNumber) == true){
	            			 System.out.println(username + " was successfully added to the system");
	            		 }else{
	            			 System.out.println("There was an error adding " + username + " to the system, please try again");
	            			 username = password = name = address = phoneNumber = null;
	            			 c = 0;
	            		 }
	            	 }
	             }
	             
	             //reset choice
	             c = 0;

	             //main menu
	             while(true)
	             {
	            	 displayLoginMenu();
	            	 while ((choice = in.readLine()) == null && choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 
	            		 continue;
	            	 }
	            	 if (c<1 | c>3)
	            		 continue;
	            	 if (c==1)
	            	 {
	            		 System.out.println("please enter a cname:");
	            		 while ((cname = in.readLine()) == null && cname.length() == 0);
	            		 System.out.println("please enter a dname:");
	            		 while ((dname = in.readLine()) == null && dname.length() == 0);
	            		 Course course=new Course();
	            		 System.out.println(course.getCourse(cname, dname, con.stmt));
	            	 }
	            	 else if (c==2)
	            	 {	 
	            		 System.out.println("please enter your query below:");
	            		 while ((sql = in.readLine()) == null && sql.length() == 0)
	            			 System.out.println(sql);
	            		 ResultSet rs=con.stmt.executeQuery(sql);
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		 int numCols = rsmd.getColumnCount();
	            		 while (rs.next())
	            		 {
	            			 //System.out.print("cname:");
	            			 for (int i=1; i<=numCols;i++)
	            				 System.out.print(rs.getString(i)+"  ");
	            			 System.out.println("");
	            		 }
	            		 System.out.println(" ");
	            		 rs.close();
	            	 }
	            	 else
	            	 {   
	            		 System.out.println("EoM");
	            		 con.stmt.close(); 
	        
	            		 break;
	            	 }
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Either connection error or query execution error!");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}

}
