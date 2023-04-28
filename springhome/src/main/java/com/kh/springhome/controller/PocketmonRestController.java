package com.kh.springhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springhome.dao.PocketmonDao;
import com.kh.springhome.dto.PocketmonDto;
import com.kh.springhome.repo.PocketmonRepository;

//이 컨트롤러의 목표는 XHR 통신에 대해서 처리 후 JSON을 반환하는 것
//(Q) Spring에서는 JSON을 어떻게 만드나?
//(A) 안만들어도 된다. Jackson-Databind 라이브러리가 자동 변환을 처리한다

//CORS를 허용해야 한다면 @CrossOrigin을 추가한다
@CrossOrigin(origins = {"*"})//전부다 허용(공공데이터 API 만드는 경우)
//@CrossOrigin(origins = {"http://127.0.0.1:5500"})//특정 출처만 허용
@RestController
@RequestMapping("/rest/pocketmon")
public class PocketmonRestController {

//	@Autowired
//	private PocketmonDao pocketmonDao;
	
	@Autowired
	private PocketmonRepository pocketmonRepo;
	
	@GetMapping("/")
	public List<PocketmonDto> list() {
		return pocketmonRepo.selectList();
	}
	
	@GetMapping("/{no}")
	public String find(@PathVariable int no) {
		PocketmonDto dto = pocketmonRepo.selectOne(no);
		if(dto == null) {//없다 → 사용 가능
			return "NNNNY";
		}
		else {//있다 → 사용 불가
			return "NNNNN";
		}
	}
	
	@PostMapping("/")
	public void insert(@ModelAttribute PocketmonDto pocketmonDto) {
		pocketmonRepo.insert(pocketmonDto);
	}
	
	//포켓몬 정보 수정
	//- 번호(no)로 이름(name)과 속성(type)을 변경
	//- PocketmonDto가 필요
	
	@PutMapping("/")//모든 정보를 바꿀 때
	//@PatchMapping("/")//일부 정보를 바꿀 때
	public void edit(@ModelAttribute PocketmonDto pocketmonDto) {
		pocketmonRepo.update(pocketmonDto);
	}
	
	@DeleteMapping("/{no}")
	public void delete(@PathVariable int no) {
		pocketmonRepo.delete(no);
	}
	
}









