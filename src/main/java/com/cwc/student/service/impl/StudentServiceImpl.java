package com.cwc.student.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.student.model.Student;
import com.cwc.student.repository.StudentRepository;
import com.cwc.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private KieContainer kieContainer;

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		KieSession kieSession = kieContainer.newKieSession();
		students.forEach(kieSession::insert);
		kieSession.fireAllRules();
		kieSession.dispose();
		return students.stream().sorted((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks()))
				.collect(Collectors.toList());
	}
}
