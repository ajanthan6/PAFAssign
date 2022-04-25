package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/Powerunit", "root", "root");
			// For testing
			System.out.print("---Successfully connected---");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
