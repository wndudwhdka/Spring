package com.kh.spring12.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring12.configuration.CustomFileuploadProperties;
import com.kh.spring12.dao.AttachmentDao;
import com.kh.spring12.dao.PocketmonDao;
import com.kh.spring12.dao.PocketmonImageDao;
import com.kh.spring12.dto.AttachmentDto;
import com.kh.spring12.dto.PocketmonDto;
import com.kh.spring12.dto.PocketmonImageDto;

@Service
public class PocketmonService {
	
	@Autowired
	private PocketmonDao pocketmonDao;
	
	@Autowired
	private PocketmonImageDao pocketmonImageDao;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private CustomFileuploadProperties fileuploadProperties;
	
	private File dir;
	
	@PostConstruct
	public void init() {
		dir = new File(fileuploadProperties.getPath());
		dir.mkdirs();
	}
	
	public void insert(PocketmonDto pocketmonDto, MultipartFile attach) throws IllegalStateException, IOException {
		//1.포켓몬 등록
		pocketmonDao.insert(pocketmonDto);
		
		if(!attach.isEmpty()) {
			//2.첨부파일 저장 및 등록(첨부파일이 있으면)
			int attachmentNo = attachmentDao.sequence();
			
			File target = new File(dir, String.valueOf(attachmentNo));
			attach.transferTo(target);//저장
			
			attachmentDao.insert(AttachmentDto.builder()
						.attachmentNo(attachmentNo)
						.attachmentName(attach.getOriginalFilename())
						.attachmentType(attach.getContentType())
						.attachmentSize(attach.getSize())
					.build());
			
			//3.포켓몬과 첨부파일 정보를 연결(첨부파일이 있으면)
			pocketmonImageDao.insert(PocketmonImageDto.builder()
						.pocketmonNo(pocketmonDto.getNo())
						.attachmentNo(attachmentNo)
					.build());
		}
	}
	
}
