package com.kh.spring12.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring12.dto.MemberDto;
import com.kh.spring12.dao.AttachmentDao;
import com.kh.spring12.dao.MemberProfileDao;
import com.kh.spring12.dto.AttachmentDto;
import com.kh.spring12.dto.MemberProfileDto;

@Service
public class MemberService {
	
	@Autowired
	private AttachmentDao attachmentDao; 
	
	@Autowired 
	private MemberProfileDao memberProfileDao;
	
	// 변경이 불가능하도록 지정
	private final File dir = new File("D:/upload"); 
	
	@PostConstruct // 최초 1번만 실행되는 메소드 
	public void init() {
		dir.mkdirs();
	}
	
	public void join(MemberDto memberDto, MultipartFile attach) throws IllegalStateException, IOException
	{
		if(!attach.isEmpty()) // 만약 사진이 있으면
		{
			// 파일번호 생성 
			int attachNo = attachmentDao.sequence();

			// 타겟 파일 생성 (업로드 번호를 이름으로 바꾸서ㅓ 하기) 
			File target = new File(dir,String.valueOf(attachNo)); 
			attach.transferTo(target); 
			
			// DB 저장
			attachmentDao.insert(AttachmentDto.builder()
					.attachmentNo(attachNo)
					.attachmentName(attach.getOriginalFilename())
					.attachmentType(attach.getContentType())
					.attachmentSize(attach.getSize())
					.build());
			// 맴버 프로필과 첨부파일 정보를 연결 
			memberProfileDao.insert(MemberProfileDto.builder()
						.memberId(memberDto.getMemberId())
						.attachmentNo(attachNo)
						.build());
															
		}
	}
	
	
	
}
