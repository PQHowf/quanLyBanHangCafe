package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		try {
			// Đăng ký driver MySQL
			DriverManager.registerDriver(new Driver());

			// Các thông số
			String url = "jdbc:mySQL://localhost:3306/csdl";
			String username = "root";
			String password = "";

			// Tạo connect
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet ketNoiCSDL(String query) throws Exception {
		Connection cn = JDBCUtil.getConnection();
		Statement st = cn.createStatement();
		return st.executeQuery(query);
	}
}
