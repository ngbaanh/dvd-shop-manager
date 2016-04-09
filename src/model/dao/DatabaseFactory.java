/**
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author NguyenBaAnh
 *
 */
public class DatabaseFactory {
	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	/**
	 * 
	 */
	public DatabaseFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String address = "jdbc:mysql://localhost:3306/se23?useUnicode=true&characterEncoding=utf-8";
			connection = DriverManager.getConnection(address,"root","");
			statement = connection.createStatement();
		} catch (Exception e) {
			System.err.println("[Database constructor] Loi: " + e);
		}
	}
	
	/**
	 * Truy vấn cập nhật
	 * 
	 * @param query
	 *            các thao tác liên quan đến tạo mới, cập nhật, xóa bản ghi
	 * @return số dòng thao tác hoặc 0 nếu thất bại
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException {
		return statement.executeUpdate(query);
	}

	/**
	 * Truy vấn chọn và trích dữ liệu
	 * 
	 * @param query
	 *            các thao tác liên quan đến chọn dữ liệu
	 * @return Một đối tượng ResultSet
	 * @throws SQLException
	 */
	public ResultSet execute(String query) throws SQLException {
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
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
