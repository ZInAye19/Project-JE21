package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.model.Person;

public class DB {
	Connection connection;
	Statement statement;
	
	PreparedStatement preparedStatement;
	
	public DB() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("[Driver Loaded]");
	}
	
	public void createConnection() throws SQLException {
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost/rollog";
		
		connection = DriverManager.getConnection(url, user, password);
		System.out.println("[Connection Created]");
	}
	
	public void insertPerson (Person person) throws SQLException {
		String sql = "insert into Person values (?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, person.fname);
		preparedStatement.setString(2, person.lname);
		preparedStatement.setString(3, person.email);
		preparedStatement.setString(4, person.password);
		
		int rows = preparedStatement.executeUpdate();
		
		if (rows>0) {
			System.out.println(person.fname + "inserted");
		} else {
			System.err.println("Insert Failed");
		}
	}
	
}
