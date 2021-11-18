package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.DB;
import com.example.model.Person;

/**
 * Servlet implementation class SignInController
 */
@WebServlet({ "/SignInController", "/signin" })
public class SignInController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person person = new Person();
		person.fname = request.getParameter("fname");
		person.lname = request.getParameter("lname");
		person.email = request.getParameter("email");
		person.password = request.getParameter("pass");
		
		DB db = new DB();
		db.createConnection();
		db.insertPerson(person);
		boolean login;
		
		try {
			login = db.loginPerson(person);
			if (login) {
				RequestDispatcher view = request.getRequestDispatcher("index.html");
				view.forward(request, response);
				System.out.println("WELCOME " + person.fname.toUpperCase());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
//		System.out.println("Hello");
		
	}

}
