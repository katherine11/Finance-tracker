package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection instance = null;
	
	private static final String DB_SCHEMA = "finance_track_test?autoReconnect=true&useSSL=false";
	private static final String DB_PORT = "3306"; 
	private static final String DB_HOST = "localhost"; // "192.168.8.22"
	private static final String DB_PASSWORD = "Gangster50!"; // "ittstudent-123"
	private static final String DB_USERNAME = "root"; // "ittstudent"

	private Connection connection;

	private DBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST 
													+ ":" + DB_PORT + "/" 
													+ DB_SCHEMA, DB_USERNAME, DB_PASSWORD);	 
	}
	
	public static DBConnection getInstance () {
		if (instance == null) {
			try {
				instance = new DBConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
