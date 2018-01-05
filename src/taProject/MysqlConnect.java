package taProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

public class MysqlConnect {
	// init database constants
	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost/Comp491?autoReconnect=true&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	private static final String MAX_POOL = "250";

	// init connection object
	private Connection connection;
	// init properties object
	private Properties properties;

	// create properties
	private Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return properties;
	}

	// connect database
	public Connection connect() {
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	// disconnect database
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String[]> getDataFromTableRankings() {

		ArrayList<String[]> res = new ArrayList<String[]>();

		String sql = "SELECT * FROM rankings";

		ResultSet resultSet;

		try {
			PreparedStatement statement = (PreparedStatement) this.connect().prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String[] cur = new String[4];
				cur[0] = resultSet.getString(1);
				cur[1] = resultSet.getString(2);
				cur[2] = resultSet.getString(3);
				cur[3] = resultSet.getString(4);
				res.add(cur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}

		
		return res;
	}
	
	public ArrayList<String[]> getDataFromTableTeachers() {

		ArrayList<String[]> res2 = new ArrayList<String[]>();

		String sql = "SELECT * FROM teachers";

		ResultSet resultSet2;

		try {
			PreparedStatement statement = (PreparedStatement) this.connect().prepareStatement(sql);
			resultSet2 = statement.executeQuery();

			while (resultSet2.next()) {
				String[] cur = new String[4];
				cur[0] = resultSet2.getString(1);
				cur[1] = resultSet2.getString(2);
				cur[2] = resultSet2.getString(3);
				cur[3] = resultSet2.getString(4);
				res2.add(cur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}

		
		return res2;
	}
	
	public ArrayList<String[]> getDataFromTableStudents() {

		ArrayList<String[]> res3 = new ArrayList<String[]>();

		String sql = "SELECT * FROM students";

		ResultSet resultSet3;

		try {
			PreparedStatement statement = (PreparedStatement) this.connect().prepareStatement(sql);
			resultSet3 = statement.executeQuery();

			while (resultSet3.next()) {
				String[] cur = new String[4];
				cur[0] = resultSet3.getString(1);
				cur[1] = resultSet3.getString(2);
				cur[2] = resultSet3.getString(3);
				cur[3] = resultSet3.getString(4);
				res3.add(cur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}

		
		return res3;
	}
	
	public ArrayList<String[]> getDataFromTableGivenCourses() {

		ArrayList<String[]> res4 = new ArrayList<String[]>();

		String sql = "SELECT * FROM givenCourses";

		ResultSet resultSet4;

		try {
			PreparedStatement statement = (PreparedStatement) this.connect().prepareStatement(sql);
			resultSet4 = statement.executeQuery();

			while (resultSet4.next()) {
				String[] cur = new String[3];
				cur[0] = resultSet4.getString(1);
				cur[1] = resultSet4.getString(2);
				cur[2] = resultSet4.getString(3);
				res4.add(cur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		
		return res4;
	}

	public ArrayList<String[]> getDataFromTableAskTa() {

		ArrayList<String[]> res5 = new ArrayList<String[]>();

		String sql = "SELECT * FROM askTA";

		ResultSet resultSet5;

		try {
			PreparedStatement statement = (PreparedStatement) this.connect().prepareStatement(sql);
			resultSet5 = statement.executeQuery();

			while (resultSet5.next()) {
				String[] cur = new String[2];
				cur[0] = resultSet5.getString(1);
				cur[1] = resultSet5.getString(2);
				res5.add(cur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return res5;
	}
}
