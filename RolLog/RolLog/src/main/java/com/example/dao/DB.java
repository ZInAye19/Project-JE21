package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.model.Person;

public class DB {
	
	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/rollog", "root", "root");
			System.out.println("Connection Created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertPerson (Person person) {
		
		String sql = "insert into Person values(?, ?, ?, ?)";
		try {
			preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1, person.fname);
			preparedStatement.setString(2, person.lname);
			preparedStatement.setString(3, person.email);
			preparedStatement.setString(4, person.password);
			int rows = preparedStatement.executeUpdate();
			if(rows>0) {
				System.out.println(person.fname+" inserted");
			}else {
				System.out.println("Insert Failed :(");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean loginPerson(Person person) throws SQLException {
		System.out.println(person.email);
		
		boolean flag;
		String sql = "select fname from Person where email = ?";
		preparedStatement = con.prepareStatement(sql);
		
		preparedStatement.setString(1, person.email);
		rs = preparedStatement.executeQuery();
		flag = false;
		  if(rs.next()) {
			  flag = true;
		  }
				return flag;
		
	}
	
}
