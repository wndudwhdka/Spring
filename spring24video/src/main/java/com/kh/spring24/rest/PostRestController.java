package com.kh.spring24.rest;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring24.dto.PostDto;
import com.kh.spring24.dto.PostWithNickDto;
import com.kh.spring24.repo.PostRepo;
import com.kh.spring24.repo.PostWithNickRepo;
import com.kh.spring24.vo.SearchVO;

@CrossOrigin
@RestController
@RequestMapping("/rest/post")
public class PostRestController {
    
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PostWithNickRepo postWithNickRepo;

    // 통합게시물 등록
    @PostMapping("/")
    public void insert(PostDto postDto, HttpSession session){
        // 통합 게시물 시퀀스 발행
		
		 Long postNo = postRepo.sequence();
		 
		 // # 통합 게시물 생성 
		 // 1. 통합게시물 번호 설정
		 postDto.setPostNo(postNo); // 세션에 있는거 확인할 때는 이거
		 // postDto.setMemberId((String)session.getAttribute("memberId"));
		 // 2. 통합게시물 작성자 설정 
		 postDto.setMemberId(postDto.getMemberId());
		 // 3. 통합게시물 글 종류 설정(fix!!)
		 postDto.setPostType(postDto.getPostType()); // postDto.setPostTime(new
		 //Date(System.currentTimeMillis())); // 현재 시간으로 설정 
		 // 4. 통합게시물 글 내용 설정
		 postDto.setPostContent(postDto.getPostContent());
		 
		 // 5. 통합게시물 등록 
		 postRepo.insert(postDto);
		

        // # 해당 게시물 생성
        
    }
    @PostMapping("/test")
    public void test(PostDto postDto) {
    	System.out.println(postDto);
    }

    // 통합게시물 목록조회, 해당 DTO로 전달
//    @GetMapping("/")
//    public List<PostWithNickDto> selectList(@ModelAttribute SearchVO searchVO){
//        return postWithNickRepo.selectList(searchVO);
//    }
//    // 통합게시물 상세조회
//    @GetMapping("/{postNo}")
//    public PostWithNickDto selectOne(@PathVariable Long postNo){
//        return postWithNickRepo.selectOne(postNo);
//    }
//
//    // 통합게시물 수정
    @PutMapping("/")
    public boolean update(@ModelAttribute PostDto postDto){
        return postRepo.update(postDto);
    }

    // 통합게시물 삭제
    @DeleteMapping("/{postNo}")
    public boolean delete(@RequestParam Long postNo){
        return postRepo.delete(postNo);
    }

}
