package com.kh.spring12.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring12.configuration.CustomFileuploadProperties;
import com.kh.spring12.dao.AttachmentDao;
import com.kh.spring12.dao.MemberDao;
import com.kh.spring12.dao.MemberProfileDao;
import com.kh.spring12.dto.AttachmentDto;
import com.kh.spring12.dto.MemberDto;
import com.kh.spring12.dto.MemberProfileDto;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberProfileDao memberProfileDao;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private CustomFileuploadProperties fileuploadProperties;
	
	private File dir;
	
	//최초 1번만 실행되는 메소드
	@PostConstruct
	public void init() {
		dir = new File(fileuploadProperties.getPath());
		dir.mkdirs();
	}
	
	public void join(MemberDto memberDto, MultipartFile attach) throws IllegalStateException, IOException {
		//회원 가입
		 memberDao.insert(memberDto);
		 
		 //첨부파일 여부에 따라 프로필 등록
		 if(!attach.isEmpty()) {
			 int attachmentNo = attachmentDao.sequence();
			 
			 File target = new File(dir, String.valueOf(attachmentNo));
			 attach.transferTo(target);
			 
			 attachmentDao.insert(AttachmentDto.builder()
					 	.attachmentNo(attachmentNo)
					 	.attachmentName(attach.getOriginalFilename())
					 	.attachmentType(attach.getContentType())
					 	.attachmentSize(attach.getSize())
					 .build());
			 
			 //연결정보 등록
			 memberProfileDao.insert(MemberProfileDto.builder()
					 	.memberId(memberDto.getMemberId())
					 	.attachmentNo(attachmentNo)
					 .build());
		 }
	}
}


