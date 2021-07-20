package com.example.demo.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.Persondto;
//import com.example.demo.queryexec;
//import com.example.demo.sdpdto;

@RestController
@RequestMapping(path= "api/v1/students")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	//knows to take the class and input it
	public StudentController(StudentService studentService) {
		this.studentService= studentService;
	}
	
	@GetMapping
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path="/{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id) {
		studentService.deleteStudent(id);
	}
	
	@GetMapping(path = "/{id}")
	public Student getById(@PathVariable("id") Long id) {
		return studentService.getById(id);
	}
	
	@PutMapping(path = "/{id}")
	public void updateStudent(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
		
		studentService.updateStudent(id,name,email);
	}
	
	@PostMapping(path="/getForEmail")
	public void getForEmail(@RequestBody Student email) {
		studentService.getForEmail(email);
	}
	
	
	@Autowired
	private  queryexec queryexeclocal;
	
	
    @PostMapping(value = "/post")
    public void postBody(@RequestBody Student student) {
    	
		studentService.getStudentsDataPool(student.getEmail());
//		queryexec.getStudentsData(student.getEmail());
    	
    }
	
}
