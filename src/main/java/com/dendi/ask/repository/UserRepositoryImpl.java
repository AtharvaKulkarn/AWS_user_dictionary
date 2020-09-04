package com.dendi.ask.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.dendi.ask.model.User;

@Component
public class UserRepositoryImpl implements UserRepository {

	@Override
	public ArrayList<User> getAllUserDetails() throws SQLException {
		ArrayList<User> userArray = new ArrayList<>();
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://aadksq97ktz6hq.cvhat3mtvofs.us-east-2.rds.amazonaws.com:3306", "DBatharva", "ask1996718");
		try {
			System.out.println("Connection Successful!!!");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ebdb.demoTable");
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				user.setAge(rs.getString(2));
				user.setSalary(rs.getString(3));
				userArray.add(user);
			}
			return userArray;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public String addUser(User user) throws SQLException {

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://aadksq97ktz6hq.cvhat3mtvofs.us-east-2.rds.amazonaws.com:3306", "DBatharva", "ask1996718");
		try {
			System.out.println("Connection Successful!!!");
			Statement stmt = conn.createStatement();
			String str = "INSERT INTO ebdb.demoTable (Name, Age, Salary) VALUES " + "(" + "'" + user.getName() + "'"
					+ "," + "'" + user.getAge() + "'" + "," + "'" + user.getSalary() + "'" + ")";
			stmt.executeUpdate(str);

			return "User added succsessfully!";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while executing query.";
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public String updateUser(User user) throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://aadksq97ktz6hq.cvhat3mtvofs.us-east-2.rds.amazonaws.com:3306", "DBatharva", "ask1996718");
		try {
			System.out.println("Connection Successful!!!");
			Statement stmt = conn.createStatement();
			String str = "UPDATE ebdb.demoTable SET Name=" +"'"+ user.getName()+"'" + "," + "Age=" +"'"+ user.getAge() +"'"+ ","
					+ "Salary=" +"'"+ user.getSalary()+ "' WHERE NAME=" +"'"+ user.getName()+"'";
			
			stmt.executeUpdate(str);

			return "User updated succsessfully!";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while executing query.";
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public String deleteUser(String userName) throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://aadksq97ktz6hq.cvhat3mtvofs.us-east-2.rds.amazonaws.com:3306", "DBatharva", "ask1996718");
		try {
			System.out.println("Connection Successful!!!");
			Statement stmt = conn.createStatement();
			String str = "DELETE FROM ebdb.demoTable WHERE Name="+"'"+userName+"'";
			
			stmt.executeUpdate(str);

			return "User deleted succsessfully!";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while executing query.";
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}


}
