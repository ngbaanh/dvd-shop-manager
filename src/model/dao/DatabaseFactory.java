/**
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author NguyenBaAnh
 *
 */
public class DatabaseFactory {
	static Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	/**
	 * 
	 */
	public DatabaseFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String address = "jdbc:mysql://localhost:3306/se23?useUnicode=true&characterEncoding=utf-8";
			//String address = "jdbc:mysql://127.6.73.2:3306/se23?useUnicode=true&characterEncoding=utf-8";
			connection = DriverManager.getConnection(address,"root","");
			//connection = DriverManager.getConnection(address,"adminMwGeTFy","3smXm8ppTMM5");
			//String url = "jdbc:mysql://127.6.73.2:3306/se23?useUnicode=true&characterEncoding=utf-8";
			//connection = DriverManager.getConnection(url,"adminMwGeTFy","3smXm8ppTMM5");
		} catch (Exception e) {
			System.err.println("[Database constructor] Loi: " + e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	/**
	 * Đóng kết nối và statement
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (preparedStatement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

}
