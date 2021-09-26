package com.example.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Person {
	
	public String fname;
	public String lname;
	public String email;
	public String password;
	
	public Person() {
		
	}

	public Person(String fname, String lname, String email, String password) {
		
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
	}
	
public void makePasswordSecure() {
		
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(password.getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

public Map<String, Object> toMap() {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("fname", fname);
	map.put("lname", lname);
	map.put("email", email);
	map.put("password", password);
	return map;
}

@Override
public String toString() {
	return "Person [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + "]";
}
	
	

}
