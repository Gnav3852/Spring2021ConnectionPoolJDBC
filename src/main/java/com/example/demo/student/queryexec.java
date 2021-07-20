package com.example.demo.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import java.lang.String;

@Component
public class queryexec {
//	private String channel;


	public static void getStudentsData(String emailendswith) {
		
	  	Connection connect = postgressjdbc.getConnection();
	  	Statement stmt = null;	
	  	ArrayList<Student> returnListStudent =new ArrayList<Student>();	
        ResultSet rs;
        
		try {
			connect.setAutoCommit(false);
			stmt = connect.createStatement();
			rs = stmt.executeQuery( "select id,name,email,dob from Student_sql where email like '%"+ emailendswith + "';" );
		      while ( rs.next() ) {
		    	  Student newrec = new Student();
		    	  newrec.setId(rs.getLong("id"));
		    	  newrec.setName(rs.getString("name"));
		    	  newrec.setEmail(rs.getString("email"));
		    	  newrec.setDob(rs.getDate("dob").toLocalDate());
		    	  returnListStudent.add(newrec);
		        }
		        rs.close();
		        stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(returnListStudent);
//		return returnListStudent;
	}
        
}
