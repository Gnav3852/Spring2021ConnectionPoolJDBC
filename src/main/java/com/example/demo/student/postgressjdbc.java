package com.example.demo.student;

import java.sql.Connection;
import java.sql.DriverManager;

public class postgressjdbc {

	
	   public static Connection getConnection() {
	      Connection connect = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         connect = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/student",
	            "postgres", "");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      
	     return connect;
	   }
	}

