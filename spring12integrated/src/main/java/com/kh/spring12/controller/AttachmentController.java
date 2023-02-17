package com.kh.spring12.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kh.spring12.dao.AttachmentDao;
import com.kh.spring12.dto.AttachmentDto;

//첨부파일의 다운로드르 처리하는 컨트롤러
@RestController // @Controller와 @ResponseBody를 합친 형태
@RequestMapping("/attachment")
public class AttachmentController {
	
	private final File dir = new File("D:/upload");
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> download(
			@RequestParam int attachmentNo) throws IOException
	{
		//DB 조회  
		// 헤더부분
		AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
		if(attachmentDto==null) // 없으면 404
		{
			return ResponseEntity.notFound().build();
		}
		
		// 파일 찾기
		// 바디부분
		File dir = new File("D:/upload"); 
		File target = new File(dir,String.valueOf(attachmentNo)); 
		
		//보낼 데이터 생성
		byte[] data = FileUtils.readFileToByteArray(target);
		ByteArrayResource resource = new ByteArrayResource(data);
		
		//헤더와 바디를 설정하며 ResponseEntity를 만들어 반환 (통신은 문자열밖에 안된다)
//		return ResponseEntity.ok()
//				//.header("Content-Type", attachmentDto.getAttachmentType())
//				.header("Content-Type", "application/octet-stream")
//				.header("Content-Length",String.valueOf(attachmentDto.getAttachmentSize()))
//				.header("Content-Disposition","attachment: filename=\""+URLEn+"\"")
//				.header("Content-Encoding","UTF-8")
//			.body(resource); 
		
		// 제공되는 모든 사웃와 명령을 동원해서 최대한 오류 없이 편하게 작성
		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_TYPE,
//				MediaType.APPLICATION_OCTET_STREAM_VALUE)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(attachmentDto.getAttachmentSize())
				.header(HttpHeaders.CONTENT_ENCODING,
							StandardCharsets.UTF_8.name())
				.header(HttpHeaders.CONTENT_DISPOSITION,
						ContentDisposition.attachment()
							.filename(
									attachmentDto.getAttachmentName(),
									StandardCharsets.UTF_8
									).build().toString()
				)
				.body(resource);
	}
	
	
	
}
