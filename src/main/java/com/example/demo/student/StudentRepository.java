package com.example.demo.student;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
	
	public static final JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);
	
	
//	@Query("select id,name,email,dob from Student where email = ?1")
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmailSQL(String email);
	
	
	
	default ArrayList<Student> getStudentsDataPool(String emailendswith) {

		
	  	Connection connect = postgressjdbc.getConnection();
	  	Statement stmt = null;	
	  	ArrayList<Student> returnListStudent =new ArrayList<Student>();	
        ResultSet rs;
        
		try {
			connect.setAutoCommit(false);
			stmt = connect.createStatement();
			rs = (ResultSet) jdbcTemplate.query( "select id,name,email,dob from Student_sql where email like '%"+ emailendswith + "';", 
					(rs2,rowRum)-> new Student(rs2.getLong("id"),rs2.getString("name"),rs2.getString("email"), rs2.getDate("dob").toLocalDate()));
//		      while ( rs.next() ) {
//		    	  Student newrec = new Student();
//		    	  newrec.setId(rs.getLong("id"));
//		    	  newrec.setName(rs.getString("name"));
//		    	  newrec.setEmail(rs.getString("email"));
//		    	  newrec.setDob(rs.getDate("dob").toLocalDate());
//		    	  returnListStudent.add(newrec);
//		        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(returnListStudent);
		return returnListStudent;
	}

}
