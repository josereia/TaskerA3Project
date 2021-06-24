package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	// private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tasker";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getErrorCode() + ". " + e.getMessage());
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro: " + e.getErrorCode() + ". " + e.getMessage());
			}
		}
	}

	public static void closeConnection(Connection conn, PreparedStatement stmt) {
		closeConnection(conn);
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro: " + e.getErrorCode() + ". " + e.getMessage());
			}
		}
	}

	public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
		closeConnection(conn, stmt);
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro: " + e.getErrorCode() + ". " + e.getMessage());
			}
		}
	}
}
