package jp.co.atlas_is.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com:5432/DB", 
					"user", 
					"pass");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return con;
	}
}
