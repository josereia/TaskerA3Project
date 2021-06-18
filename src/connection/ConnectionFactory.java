package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	//private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tasker";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() {
		try {
			//Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (SQLException e) {
			throw new RuntimeException("Erro na conexão: ", e);
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection conn, PreparedStatement stmt) {
		closeConnection(conn);
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
		closeConnection(conn, stmt);
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
