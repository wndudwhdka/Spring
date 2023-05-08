package com.kh.spring22.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring22.dto.ItemDto;
import com.kh.spring22.dto.PaymentDto;
import com.kh.spring22.repo.ItemRepo;
import com.kh.spring22.repo.PaymentRepo;
import com.kh.spring22.service.KakaoPayService;
import com.kh.spring22.vo.KakaoPayApproveRequestVO;
import com.kh.spring22.vo.KakaoPayApproveResponseVO;
import com.kh.spring22.vo.KakaoPayCancelRequestVO;
import com.kh.spring22.vo.KakaoPayCancelResponseVO;
import com.kh.spring22.vo.KakaoPayOrderRequestVO;
import com.kh.spring22.vo.KakaoPayOrderResponseVO;
import com.kh.spring22.vo.KakaoPayReadyRequestVO;
import com.kh.spring22.vo.KakaoPayReadyResponseVO;
import com.kh.spring22.vo.PurchaseListVO;
import com.kh.spring22.vo.PurchaseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	@GetMapping("/test1")
	public String test1( ) {
		//return "/WEB-INF/views/pay/test1.jsp";
		return "pay/test1";
	}
	
	@PostMapping("/test1")
	public String test1(@ModelAttribute KakaoPayReadyRequestVO vo,
			HttpSession session) throws URISyntaxException {
		//정보추가(주문자번호, 주문번호)
		vo.setPartner_order_id(UUID.randomUUID().toString());
		//vo.setPartner_user_id((String)session.getAttribute("memberId"));
		vo.setPartner_user_id("testuser119");
		
		//준비요청
		KakaoPayReadyResponseVO response = kakaoPayService.ready(vo);
		
		//세션에 데이터 임시 첨부(partner_order_id, partner_user_id, tid)
		session.setAttribute("partner_order_id", vo.getPartner_order_id());
		session.setAttribute("partner_user_id", vo.getPartner_user_id());
		session.setAttribute("tid", response.getTid());
		
		//사용자를 결제페이지로 리다이렉트
		return "redirect:" + response.getNext_redirect_pc_url();
	}
	
	//test1 결제 성공 매핑 - 카카오페이가 불러주는 주소
	@GetMapping("/test1/success")
	public String test1Success(
			//@RequestParam String pg_token
			@ModelAttribute KakaoPayApproveRequestVO vo,
			HttpSession session
			) throws URISyntaxException {
		//partner_order_id, partner_user_id, tid, pg_token 필요하지만
		//메소드에는 pg_token밖에 없다
		//승인을 하기 위해서는 *준비 단계에서 만든 정보*가 필요하다
		//-> 같은 브라우저에서만 데이터가 전달되도록 HttpSession을 사용
		
		//세션에 첨부된 임시 데이터 추출/삭제(partner_order_id, partner_user_id, tid)
		vo.setPartner_order_id((String)session.getAttribute("partner_order_id"));
		vo.setPartner_user_id((String)session.getAttribute("partner_user_id"));
		vo.setTid((String)session.getAttribute("tid"));
		
		session.removeAttribute("partner_order_id");
		session.removeAttribute("partner_user_id");
		session.removeAttribute("tid");
		
		KakaoPayApproveResponseVO response = kakaoPayService.approve(vo);
		
		//return "redirect:/pay/test1/clear";
		return "redirect:clear";
	}
	
	@GetMapping("/test1/clear")
	public String test1Clear() {
		//return "/WEB-INF/views/pay/clear.jsp";
		return "pay/clear";
	}
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@GetMapping("/test1/list")
	public String list(Model model) {
		//String memberId = (String)session.getAttribute("memberId");
		String memberId = "testuser119";
		List<PaymentDto> list = paymentRepo.selectByMember(memberId);
		model.addAttribute("list", list);
		//return "/WEB-INF/views/pay/list.jsp";
		return "pay/list";
	}
	
	@GetMapping("/test1/detail")
	public String detail(@RequestParam int paymentNo, Model model) throws URISyntaxException {
		//우리 DB에서 정보를 찾아라
		PaymentDto paymentDto = paymentRepo.find(paymentNo);
		
		//찾은 정보에서 TID를 조회하여 카카오페이에서 실제 정보를 조회하라
		KakaoPayOrderRequestVO vo = new KakaoPayOrderRequestVO();
		vo.setTid(paymentDto.getPaymentTid());
		KakaoPayOrderResponseVO response = kakaoPayService.order(vo);
		
		//모든 정보를 Model에 첨부
		model.addAttribute("paymentDto", paymentDto);
		model.addAttribute("response", response);
		
		//상세 페이지 반환
		return "pay/detail";//"/WEB-INF/views/pay/detail.jsp"
	}
	
	@GetMapping("/test1/cancel")
	public String cancel(
			@RequestParam int paymentNo, 
			HttpServletResponse resp,
			RedirectAttributes attr) throws URISyntaxException, IOException, NoHandlerFoundException {
		//[1] paymentNo로 PaymentDto 정보를 조회
		PaymentDto paymentDto = paymentRepo.find(paymentNo);
		if(paymentDto == null || paymentDto.getPaymentRemain() == 0) {
			//resp.sendError(500);//사용자에게 500번을 내보내라
			//return null;
			throw new NoHandlerFoundException(null, null, null);
		}
		
		//[2] 1번에서 구한 정보의 TID와 잔여금액 정보로 카카오에게 취소 요청
		KakaoPayCancelRequestVO vo = new KakaoPayCancelRequestVO();
		vo.setTid(paymentDto.getPaymentTid());
		vo.setCancel_amount(paymentDto.getPaymentRemain());
		
		KakaoPayCancelResponseVO response = kakaoPayService.cancel(vo);
		
		//[3] 내 DB의 잔여 금액을 0으로 변경(paymentRepo)
		paymentRepo.cancelRemain(paymentNo);
		
		//[4] 상세 페이지로 돌려보낸다
		//return "redirect:detail?paymentNo="+paymentNo;
		attr.addAttribute("paymentNo", paymentNo);
		return "redirect:detail";
	}
	
	/////////////////////////////////////////////////////////////////////
	@Autowired
	private ItemRepo itemRepo;
	
	@GetMapping("/test2")
	public String test2(Model model) {
		model.addAttribute("itemList", itemRepo.list());
		return "pay/test2";
	}
	
	//객체 배열 형태로 전송되는 데이터를 수신하는 처리(ex : data[0].qty)
	@PostMapping("/test2")
	public String test2(@ModelAttribute PurchaseListVO listVO,
			HttpSession session) throws URISyntaxException {
		//전달받은 내용에서 결제 정보를 생성 
		String name = "";
		int total = 0;
		int count = 0;
		
		log.debug("데이터 개수 = {}", listVO.getData().size());
		for(PurchaseVO vo : listVO.getData()) {
			//[1] vo에 있는 상품번호로 상품정보(ItemDto)를 불러와야 한다
			ItemDto itemDto = itemRepo.find(vo.getItemNo());
			//[2] 상품이름과 상품가격을 조회하여 필요한 정보를 계산한다
			name = itemDto.getItemName();
			total += itemDto.getItemDiscount() * vo.getQty();
			count++;
		}
		
		KakaoPayReadyRequestVO request = new KakaoPayReadyRequestVO();
		request.setPartner_order_id(UUID.randomUUID().toString());
		request.setPartner_user_id("testuser119");
		request.setItem_name(name + " 외 " + (count-1) + "건");
		request.setQuantity(1);
		request.setTotal_amount(total);
		
		KakaoPayReadyResponseVO response = kakaoPayService.ready(request);
		
		//세션에 데이터 임시 첨부(partner_order_id, partner_user_id, tid)
		//(+추가) 구매내역(번호+수량)
		session.setAttribute("partner_order_id", request.getPartner_order_id());
		session.setAttribute("partner_user_id", request.getPartner_user_id());
		session.setAttribute("tid", response.getTid());
		session.setAttribute("listVO", listVO);
		
		return "redirect:" + response.getNext_redirect_pc_url();
	}
	
	@GetMapping("/test2/success")
	public String test2Success(
			HttpSession session,
			@ModelAttribute KakaoPayApproveRequestVO vo) throws URISyntaxException {
		vo.setPartner_order_id((String)session.getAttribute("partner_order_id"));
		vo.setPartner_user_id((String)session.getAttribute("partner_user_id"));
		vo.setTid((String)session.getAttribute("tid"));
		PurchaseListVO listVO = (PurchaseListVO)session.getAttribute("listVO");
		
		session.removeAttribute("partner_order_id");
		session.removeAttribute("partner_user_id");
		session.removeAttribute("tid");
		session.removeAttribute("listVO");
		//session.invalidate(); 으로 작성하면 세션이 사라짐
		
		KakaoPayApproveResponseVO response = 
					kakaoPayService.approveWithDetail(vo, listVO);
		
		return "redirect:clear";//redirect:/pay/test2/clear
	}
	
	@GetMapping("/test2/clear")
	public String test2Clear() {
		return "pay/clear";
	}
	
}