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

import com.kh.spring19.dto.SubjectDto;
import com.kh.spring19.repo.SubjectRepo;

@CrossOrigin
@RestController
@RequestMapping("/subject")
public class SubjectRestController {
	
	@Autowired
	private SubjectRepo subjectRepo;
	
	@GetMapping("/")
	public List<SubjectDto> list() {
		return subjectRepo.selectList();
	}
	
	@PostMapping("/")
	public void insert(@ParameterObject @RequestBody SubjectDto subjectDto) {
		subjectRepo.insert(subjectDto);
	}
	
	@PutMapping("/")
	public void edit(@ParameterObject @RequestBody SubjectDto subjectDto) throws NoHandlerFoundException {
		boolean result = subjectRepo.update(subjectDto);
		if(result == false) 
			throw new NoHandlerFoundException(null, null, null);
	}
	
	@DeleteMapping("/{no}")
	public void delete(@PathVariable int no) throws NoHandlerFoundException {
		boolean result = subjectRepo.delete(no);
		if(result == false)
			throw new NoHandlerFoundException(null, null, null);
	}
	
}
