package javaoraDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaoraDBConn {
	private Connection con;
	
	public Connection getConnection() {
		return con;
	}
	
	public JavaoraDBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr","hr");
	}
}
