package phase2;

/*
import java.sql.*;

public class Connector {
	public Connection con;
	public Statement stmt;
	public Connector() throws Exception {
		try{
		 	String userName = "5530u35";
	   		String password = "m0ru323l";
	        	String url = "jdbc:mysql://georgia.eng.utah.edu/5530db35";
		        Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        		con = DriverManager.getConnection (url, userName, password);

			//DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        	//stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt = con.createStatement();
			//stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch(Exception e) {
			System.err.println("Unable to open mysql jdbc connection. The error is as follows,\n");
            		System.err.println(e.getMessage());
			throw(e);
		}
	}
	
	public void closeConnection() throws Exception{
		con.close();
	}
}
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch; // YOU'LL NEED jsch-0.1.54.jar
import com.jcraft.jsch.Session;

public class Connector
{
public Statement stmt;

public Connection conn = null;
public Session session = null;

public Connector() throws Exception
{
String sshHost = "georgia.eng.utah.edu";
String sshUser = "5530u35";
String sshPassword = "m0ru323l";

int lport = 5656;
String rhost = "localhost";
int rport = 3306;

// ?allowMultiQueryies=true this allows for multiple queries to be sent in one string // YOU MAY NOT WANT THIS FUNCTIONALITY
String url = "jdbc:mysql://localhost:" + lport + "/5530db35?allowMultiQueries=true";
String dbUser = "5530u35";
String dbPassword = "m0ru323l";
String driverName = "com.mysql.jdbc.Driver";

try
{
// Set StrictHostKeyChecking property to no to avoid UnknownHostKey
// issue
java.util.Properties config = new java.util.Properties();
config.put("StrictHostKeyChecking", "no");
JSch jsch = new JSch();
session = jsch.getSession(sshUser, sshHost, 22);
session.setPassword(sshPassword);
session.setConfig(config);
session.connect();
System.out.println("Connected");
/*
int assigned_port = session.setPortForwardingL(lport, rhost, rport);
System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);
System.out.println("Port Forwarded");
*/
// mysql database connectivity
Class.forName(driverName).newInstance();
conn = DriverManager.getConnection(url, dbUser, dbPassword);
stmt = conn.createStatement();

System.out.println("Database connection established");
System.out.println("DONE");
} catch (SQLException sql)
{
sql.printStackTrace();
throw sql;
}
}

public void closeConnection() throws Exception
{
conn.close();
session.disconnect();
}
}