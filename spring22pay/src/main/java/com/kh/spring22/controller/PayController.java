package com.kh.spring22.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring22.dto.PaymentDto;
import com.kh.spring22.repo.PaymentRepo;
import com.kh.spring22.service.KakaoPayService;
import com.kh.spring22.vo.KakaoPayApproveRequestVO;
import com.kh.spring22.vo.KakaoPayApproveResponseVO;
import com.kh.spring22.vo.KakaoPayReadyRequestVO;
import com.kh.spring22.vo.KakaoPayReadyResponseVO;
import com.kh.spring22.vo.PurchaseListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@GetMapping("/test1")
	public String test1( ) {
		//return "/WEB-INF/views/pay/test1.jsp";
		return "pay/test1";
	}
	
	@PostMapping("/test1")
	public String test1(@ModelAttribute KakaoPayReadyRequestVO vo) throws URISyntaxException {
		//정보추가(주문자번호, 주문번호)
		vo.setPartner_order_id(UUID.randomUUID().toString());
		vo.setPartner_user_id(UUID.randomUUID().toString());
		
		//준비요청
		KakaoPayReadyResponseVO response = kakaoPayService.ready(vo);
		
		//사용자를 결제페이지로 리다이렉트
		return "redirect:" + response.getNext_redirect_pc_url();
	}
	
	//test1 결제 성공 매핑 - 카카오페이가 불러주는 주소
	@GetMapping("/test1/success")
	public String test1Success(
			// @RequestParam String pg_token
			@ModelAttribute KakaoPayApproveRequestVO vo,
			HttpSession session
			) throws URISyntaxException {
		//partner_order_id, partner_user_id, tid, pg_token 필요하지만
		//메소드에는 pg_token밖에 없다
		//승인을 하기 위해서는 *준비 단계에서 만든 정보*가 필요하다
		//-> 같은 브라우저에서만 데이터가 전달되도록 HttpSession을 사용
		
		// 세션에 첨부된 임시 데이터 추출/삭제(partner_order_id, partner_user_id)
		vo.setPartner_order_id((String)session.getAttribute("partner_order_id"));
		vo.setPartner_user_id((String)session.getAttribute("partner_user_id"));
		vo.setTid((String)session.getAttribute("tid"));
		
		session.removeAttribute("partner_order_id");
		session.removeAttribute("partner_user_id");
		session.removeAttribute("tid");
		
		// 준비요청
		KakaoPayApproveResponseVO response = kakaoPayService.approve(vo);
		
	
		return "redirect:clear";
	}
	
	@GetMapping("/test1/clear")
	public String test1Clear() {
		// return "/WEB-INF/views/pay/claer.jsp";
		return "pay/clear";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		// String memberId = (String)session.getAttribute("memberId");
		String memberId = "testuser119";
		List<PaymentDto> list = paymentRepo.selectByMember(memberId);
		model.addAttribute("list",list);
		// return "/WEB-INF/views/pay/list.jsp"; 
		return "pay/list"; 
	}
	
	@PostMapping("/test2")
	public String test2(@ModelAttribute PurchaseListVO listVO) {
		log.debug("데이터 개수 = {}", listVO.getData());
		
	}
	
	
}