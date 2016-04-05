/**
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import net.ucanaccess.jdbc.UcanaccessDriver;

/**
 * @author NguyenBaAnh
 *
 */
public class DatabaseFactory {
	Connection connection = null;
	Statement statement = null;
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

}
