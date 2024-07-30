package com.cwc.student.service;

import java.util.List;

import org.kie.api.runtime.KieSession;

import com.cwc.student.model.Student;

public interface StudentService {
	
	
	 Student saveStudent(Student student);
	 List<Student> getAllStudents();
	
}
