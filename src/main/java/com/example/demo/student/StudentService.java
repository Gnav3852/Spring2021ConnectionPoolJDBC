package com.example.demo.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.sql.DataSource;


@Service
//dependecy injection
//Service and component are the same thing
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Autowired
    public DataSource dataSource;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("TAKWEN");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		boolean exisit = studentRepository.existsById(id);
		if(!exisit) {
			throw new IllegalStateException("NOT DOUND");
		}
		studentRepository.deleteById(id);
	}

	public Student getById(Long id) {
		boolean exisit = studentRepository.existsById(id);
		if(!exisit) {
			throw new IllegalStateException("NOT DOUND");
		}
		return studentRepository.getById(id);
		
	}
	
	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("dasdads"));
		
		if(name !=null) {
			student.setName(name);
		}
		if(email !=null) {
			student.setName(email);
		}
		
	}

	public void getForEmail(Student userInput) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(userInput.getEmail());
//		if(studentByEmail.isPresent()) {
//			throw new IllegalStateException("TAKWEN");
//		}
//		
////		System.out.println(studentByEmail);
	}

	public ResponseEntity<ArrayList<Student>> test(Student student) {
		ArrayList<Student> returnListStudent = studentRepository.getStudentsDataPool(student.getEmail());
        return ResponseEntity.ok(returnListStudent);
		
	}
	
	public void getStudentsDataPool(String emailendswith) {

		
	  	Connection connect = postgressjdbc.getConnection();
	  	Statement stmt = null;	
	  	ArrayList<Student> returnListStudent =new ArrayList<Student>();	
//	  	ArrayList<Student> rs;
        
		try {
			connect.setAutoCommit(false);
			stmt = connect.createStatement();

			returnListStudent =(ArrayList<Student>) jdbcTemplate.query( "select id,name,email,dob from Student_sql where email like '%"+ emailendswith + "';", 
					(rs2,rowRum)-> new Student(rs2.getLong("id"),rs2.getString("name"),rs2.getString("email"), rs2.getDate("dob").toLocalDate()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(returnListStudent);
//		return returnListStudent;
	}





	
}



//rs =(ResultSet) jdbcTemplate.query( "select id,name,email,dob from Student_sql where email like '%"+ emailendswith + "';",
