package com.kh.spring19.rest;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.kh.spring19.dto.StudentDto;
import com.kh.spring19.repo.StudentRepo;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	private StudentRepo studentRepo;
	
	@GetMapping("/")
	public List<StudentDto> list() {
		return studentRepo.selectList();
	}
	
	@PostMapping("/")
	public void insert(@ParameterObject @RequestBody StudentDto studentDto) {
		studentRepo.insert(studentDto);
	}
	
	@PutMapping("/")
	public void edit(@ParameterObject @RequestBody StudentDto studentDto) throws NoHandlerFoundException {
		if(studentRepo.update(studentDto) == false) {
			throw new NoHandlerFoundException(null, null, null);
		}
	}
	
	@DeleteMapping("/{no}")
	public void delete(@PathVariable int no) throws NoHandlerFoundException {
		if(studentRepo.delete(no) == false) {
			throw new NoHandlerFoundException(null, null, null);
		}
	}
	
}
