package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.model.connections.DBConnection;
import com.example.model.exceptions.UserException;

@Component
public class UserDAO {

	private static final String INSERT_USER_SQL = "insert into users values (null, ?, ?, md5(?))";
	private static final String SELECT_USER_SQL = "select user_id, email from users where username = ? and password = md5(?)";
	private static final String DELETE_USER_SQL = "delete from users where username = ?";

	public int registerUser(User user) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new UserException("User registration failed!");
		}

	}

	public User loginUser(User user) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_USER_SQL);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());

			ResultSet rs = ps.executeQuery();
			if (!rs.next()){
				throw new UserException("User login failed!");
			}
			return new User(rs.getInt(1), user.getUsername(), rs.getString(2), "**********");

		} catch (SQLException e) {
			throw new UserException("Someting went wrong!");
		}

	}
	
	public boolean deleteUser(User user) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_USER_SQL);

			ps.setString(1, user.getUsername());
			int deletedRows = ps.executeUpdate(); 
			if (deletedRows == 0){
				throw new UserException("No such user!");
			}
			return true;

		} catch (SQLException e) {
			throw new UserException("Someting went wrong!");
		}

	}

}
