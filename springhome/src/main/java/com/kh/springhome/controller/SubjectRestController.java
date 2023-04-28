package com.kh.springhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springhome.dto.SubjectDto;
import com.kh.springhome.repo.SubjectRepository;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/rest/subject")
public class SubjectRestController {
	
//	@Autowired
//	private SubjectDao subjectDao;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping("/")
	public List<SubjectDto> list() {
		return subjectRepo.selectList();
	}
	
//	@GetMapping("/{no}")
	
	@PostMapping("/")
	public void insert(@ModelAttribute SubjectDto subjectDto) {
		subjectRepo.insert(subjectDto);
	}
	
//	@PutMapping("/")
	@DeleteMapping("/{no}")
	public void delete(@PathVariable int no) {
		subjectRepo.delete(no);
	}
	
}








