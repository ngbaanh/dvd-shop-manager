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

import net.ucanaccess.jdbc.UcanaccessDriver;

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
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			// Đường dẫn ở thư mục mã nguồn, để test CSDL
			String address = UcanaccessDriver.URL_PREFIX
					+ "D:\\git\\SE23\\se23\\WebContent\\WEB-INF\\Database.accdb";

			/**
			 * Đường dẫn tự động ở thư mục đã triển khai (trong thư mục DAO)
			 * Object databaseFilePath =
			 * this.getClass().getResource("Database.accdb"); String address =
			 * UcanaccessDriver.URL_PREFIX +
			 * databaseFilePath.toString().substring(6).replaceAll("(%20)", " "
			 * );
			 */
			connection = DriverManager.getConnection(address);
			statement = connection.createStatement();
		} catch (Exception e) {
			System.err.println("[Database constructor] Lỗi: " + e);
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
