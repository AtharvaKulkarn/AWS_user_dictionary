package com.dendi.ask.repository;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dendi.ask.model.User;

public interface UserRepository {

	ArrayList<User> getAllUserDetails() throws SQLException;

	String addUser(User user) throws SQLException;
	
	String updateUser(User user) throws SQLException;
	
	String deleteUser(String userName) throws SQLException;
}
