package com.kh.spring13.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring13.configuration.FileUploadProperties;
import com.kh.spring13.dao.AttachmentDao;
import com.kh.spring13.dao.PocketmonDao;
import com.kh.spring13.dao.PocketmonImageDao;
import com.kh.spring13.dao.PocketmonWithImageDao;
import com.kh.spring13.dto.AttachmentDto;
import com.kh.spring13.dto.PocketmonDto;
import com.kh.spring13.dto.PocketmonImageDto;

@CrossOrigin
@Controller
public class FileController {
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private PocketmonDao pocketmonDao;
	
	@Autowired
	private PocketmonImageDao pocketmonImageDao;
	
	@Autowired
	private FileUploadProperties fileUploadProperties;
	
	private File dir;
	
	@PostConstruct
	public void init() {
		dir = new File(fileUploadProperties.getPath());
		dir.mkdirs();
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("list", pocketmonImageDao.selectList());
		return "/WEB-INF/views/home.jsp";
	}
	
	//파일 업로드는 PostMapping만 가능
	// - 전송된 파일은 MultipartFile 이라는 형태로 수신이 가능하다
	// - 파일 전송 완료 후에는 반드시 redirect를 해야 한다(안하면 새로고침시 재업로드)
	// - (주의) 파일을 전송하지 않아도 attach는 null이 아니므로 추가 검사 필요
	@PostMapping("/upload3")
	public String upload3(@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		System.out.println(attach.isEmpty());
		System.out.println("name = " + attach.getName());
		System.out.println("original file name = " + attach.getOriginalFilename());
		System.out.println("content type = " + attach.getContentType());
		System.out.println("size = " + attach.getSize());
		
		if(!attach.isEmpty()) {//파일이 있을 경우
			//번호 생성
			int attachmentNo = attachmentDao.sequence();
			
			//파일 저장(저장 위치는 임시로 생성)
			File target = new File(dir, String.valueOf(attachmentNo));//파일명=시퀀스
			attach.transferTo(target);
			
			//DB 저장
			attachmentDao.insert(AttachmentDto.builder()
							.attachmentNo(attachmentNo)
							.attachmentName(attach.getOriginalFilename())
							.attachmentType(attach.getContentType())
							.attachmentSize(attach.getSize())
						.build());
		}
		return "redirect:/";
	}
	
//	실전 : 기존 테이블에 이미지를 연결하여 저장하고 싶은 경우(포켓몬+이미지)
//	- 포켓몬 정보는 포켓몬 테이블에, 이미지는 첨부파일 테이블에 저장
//	- 두 테이블의 항목간 연결 관계를 나타내는 추가 테이블이 필요
	@PostMapping("/upload4")
	public String upload4(
			@ModelAttribute PocketmonDto pocketmonDto,
			@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		
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
		
		return "redirect:/";
	}
	
//	전송되는 파일이 여러개라면 (1)배열 또는 (2)리스트 형태로 수신한다
//	@PostMapping("/upload5")
//	public String upload5(@RequestParam MultipartFile[] attaches) {
//		System.out.println("전송개수 : " + attaches.length);
//		return "redirect:/";
//	}
	
	@PostMapping("/upload5")
	public String upload5(@RequestParam List<MultipartFile> attaches) {
		System.out.println("전송개수 : " + attaches.size());
		return "redirect:/";
	}
	
	
//	파일 다운로드를 위해서 알아야 할 내용
//	- @Controller에서는 아무 말도 없으면 View를 반환한다
//	- 다운로드를 위해서는 View가 아니라 파일을 줘야 한다(일반적이지 않다)
//	- View를 반환하지 않기 위해 @ResponseBody를 추가한다
//	- 파일의 정보와 내용을 직접 설정해서 반환해야 한다(HTTP 헤더+바디)
//	- 헤더와 바디를 직접 설정해서 반환하려면 ResponseEntity를 사용해야 한다
	
//	아래와 같이 만들면 헤더는 설정이 불가
	@GetMapping("/download1")
	@ResponseBody
	public String download1() {
		return "download1 실행";
	}
	
	@GetMapping("/download2")
	@ResponseBody
	public ResponseEntity<String> download2() {
		//[1] 요청 결과 형태를 정의할 수 있다.
		
		//요청성공(ok=200)+download2 반환
		//return ResponseEntity.ok().build();
		//return ResponseEntity.ok().body("download2");
		
		//못찾음(404) 반환
		//return ResponseEntity.notFound().build();
		//권한없음(403) 반환
		//return ResponseEntity.status(403).build();
		
		//[2] 원하는 형태로 헤더를 설정할 수 있다
		return ResponseEntity.ok()
								.header("khacademy", "web-developer")
								.build();
	}
	
//	실제 파일 다운로드 처리 메소드
//	- 사용자에게 첨부파일 번호(attachmentNo)를 달라고 한다
//	- attachmentNo를 사용하여 DB를 조회한다
//		- 없으면 사용자에게 404 notFound를 반환하고 종료한다
//		- 있으면 실제 파일 데이터를 불러와야 한다(single byte 입력) 
//		-  Apache commons IO 라이브러리를 사용
//		- 헤더에 다음 정보를 설정한다
//			- 파일명(Content-Disposition)
//			- 파일크기(Content-Length)
//			- 파일유형(Content-Type)
//			- 파일인코딩(Content-Encoding)
//		- 바디에 파일의 실제 데이터를 첨부한 뒤 반환하면 스프링이 나머지는 처리
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> download(
									@RequestParam int attachmentNo) throws IOException {
		//DB 조회
		AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
		if(attachmentDto == null) {//없으면 404
			return ResponseEntity.notFound().build();
		}
		
		//파일 찾기
		File target = new File(dir, String.valueOf(attachmentNo));
		
		//보낼 데이터 생성
		byte[] data = FileUtils.readFileToByteArray(target);
		ByteArrayResource resource = new ByteArrayResource(data);
		
		//헤더와 바디를 설정하며 ResponseEntity를 만들어 반환
//		return ResponseEntity.ok()
//					.header("Content-Type", attachmentDto.getAttachmentType())
////					.header("Content-Type", "application/octet-stream")
//					.header("Content-Length", String.valueOf(attachmentDto.getAttachmentSize()))
////					.header("Content-Disposition", "attachment; filename="+attachmentDto.getAttachmentName())
//					.header("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(attachmentDto.getAttachmentName(), "UTF-8")+"\"")
//					.header("Content-Encoding", "UTF-8")
//				.body(resource);
		
//		제공되는 모든 상수와 명령을 동원해서 최대한 오류 없이 편하게 작성
		return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_TYPE, 
//							MediaType.APPLICATION_OCTET_STREAM_VALUE)
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
	
	@Autowired
	private PocketmonWithImageDao pocketmonWithImageDao;
	
	@GetMapping("/pocketmon/list")
	public String pocketmonList(Model model) {
		model.addAttribute("list", pocketmonWithImageDao.selectList());
		return "/WEB-INF/views/list.jsp";
	}
}







