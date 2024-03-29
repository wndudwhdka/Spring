package com.kh.spring22.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.spring22.configuration.KakaoPayProperties;
import com.kh.spring22.dto.ItemDto;
import com.kh.spring22.dto.PaymentDto;
import com.kh.spring22.repo.ItemRepo;
import com.kh.spring22.repo.PaymentRepo;
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
@Service
public class KakaoPayServiceImpl implements KakaoPayService{
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private KakaoPayProperties properties;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Override
	public KakaoPayReadyResponseVO ready(KakaoPayReadyRequestVO vo) throws URISyntaxException {
		//주소 생성
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
		//바디 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", properties.getCid());
		body.add("partner_order_id", vo.getPartner_order_id());
		body.add("partner_user_id", vo.getPartner_user_id());
		body.add("item_name", vo.getItem_name());
		body.add("quantity", String.valueOf(vo.getQuantity()));
		body.add("total_amount", String.valueOf(vo.getTotal_amount()));
		body.add("tax_free_amount", "0");
		
		//현재 접속중인 주소를 계산하는 도구를 사용(ServletUriComponentsBuilder)
		//(주의) 테스트에서는 http://localhost 로 나온다
		//String contextPath = ???;
		String currentPath = ServletUriComponentsBuilder
												.fromCurrentRequestUri()
												.toUriString();
		log.debug("currentPath = {}", currentPath);
		
		body.add("approval_url", currentPath+"/success");
		body.add("fail_url", currentPath+"/fail");
		body.add("cancel_url", currentPath+"/cancel");
		
		//바디+헤더
		HttpEntity entity = new HttpEntity(body, headers);
		
		//요청 전송
		KakaoPayReadyResponseVO response = 
				template.postForObject(uri, entity, KakaoPayReadyResponseVO.class);
		
		return response;
	}
	
	@Override
	public KakaoPayApproveResponseVO approve(KakaoPayApproveRequestVO vo) throws URISyntaxException {
		//주소 설정
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");
		
		//바디 설정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", properties.getCid());
		body.add("partner_order_id", vo.getPartner_order_id());
		body.add("partner_user_id", vo.getPartner_user_id());
		body.add("tid", vo.getTid());
		body.add("pg_token", vo.getPg_token());
		
		//헤더+바디
		//HttpEntity entity = new HttpEntity(body, headers);
		HttpEntity<MultiValueMap<String, String>> entity = 
												new HttpEntity<>(body, headers);
		
		//전송
		KakaoPayApproveResponseVO response = 
				template.postForObject(uri, entity, KakaoPayApproveResponseVO.class);
		
		//실제 결제가 이루어진 후 내역 중 필요한 것을 데이터베이스 저장
		//[1] 결제번호 생성
		int paymentNo = paymentRepo.sequence();
		//[2] 결제정보 DTO 생성
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setPaymentNo(paymentNo);//결제시퀀스번호
		paymentDto.setPaymentTid(response.getTid());//거래번호
		paymentDto.setPaymentName(response.getItem_name());//거래이름
		paymentDto.setPaymentTotal(response.getAmount().getTotal());//결제금액
		paymentDto.setPaymentRemain(response.getAmount().getTotal());//잔여금액
		paymentDto.setPaymentTime(response.getApproved_at());//승인시각
		paymentDto.setMemberId(response.getPartner_user_id());//주문회원
		//[3] 등록
		paymentRepo.save(paymentDto);
		
		return response;
	}
	
	@Override
	public KakaoPayApproveResponseVO approveWithDetail(KakaoPayApproveRequestVO vo, PurchaseListVO listVO)
			throws URISyntaxException {
		//주소 설정
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");
		
		//바디 설정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", properties.getCid());
		body.add("partner_order_id", vo.getPartner_order_id());
		body.add("partner_user_id", vo.getPartner_user_id());
		body.add("tid", vo.getTid());
		body.add("pg_token", vo.getPg_token());
		
		//헤더+바디
		//HttpEntity entity = new HttpEntity(body, headers);
		HttpEntity<MultiValueMap<String, String>> entity = 
												new HttpEntity<>(body, headers);
		
		//전송
		KakaoPayApproveResponseVO response = 
				template.postForObject(uri, entity, KakaoPayApproveResponseVO.class);
		
		//실제 결제가 이루어진 후 내역 중 필요한 것을 데이터베이스 저장
		//[1] 결제번호 생성
		int paymentNo = paymentRepo.sequence();
		//[2] 결제정보 DTO 생성
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setPaymentNo(paymentNo);//결제시퀀스번호
		paymentDto.setPaymentTid(response.getTid());//거래번호
		paymentDto.setPaymentName(response.getItem_name());//거래이름
		paymentDto.setPaymentTotal(response.getAmount().getTotal());//결제금액
		paymentDto.setPaymentRemain(response.getAmount().getTotal());//잔여금액
		paymentDto.setPaymentTime(response.getApproved_at());//승인시각
		paymentDto.setMemberId(response.getPartner_user_id());//주문회원
		//[3] 등록
		paymentRepo.save(paymentDto);
		
		//대표 정보와 함께 상세 정보를 등록(PaymentDetail)
		//[1] listVO의 data를 반복
		for(PurchaseVO purchaseVO : listVO.getData()) {
			//[2] 상품정보를 조회
			ItemDto itemDto = itemRepo.find(purchaseVO.getItemNo());
			//[3] PaymentDetailDto를 만들고 정보를 설정한 뒤 등록 처리
		}
		
		return response;
	}
	
	@Override
	public KakaoPayOrderResponseVO order(KakaoPayOrderRequestVO vo) throws URISyntaxException {
		//주소 생성
		URI uri = new URI("https://kapi.kakao.com/v1/payment/order");
		//바디 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", properties.getCid());
		body.add("tid", vo.getTid());
		//헤더 + 바디
		HttpEntity entity = new HttpEntity(body, headers);
		//전송
		KakaoPayOrderResponseVO response = 
				template.postForObject(uri, entity, KakaoPayOrderResponseVO.class);
		//반환
		return response;
	}
	
	@Override
	public KakaoPayCancelResponseVO cancel(KakaoPayCancelRequestVO vo) throws URISyntaxException {
		//주소 생성
		URI uri = new URI("https://kapi.kakao.com/v1/payment/cancel");
		//바디 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", properties.getCid());
		body.add("tid", vo.getTid());
		body.add("cancel_amount", String.valueOf(vo.getCancel_amount()));
		body.add("cancel_tax_free_amount", String.valueOf(vo.getCancel_tax_free_amount()));
		//헤더 + 바디
		HttpEntity entity = new HttpEntity(body, headers);
		//전송 및 응답 수신
		KakaoPayCancelResponseVO response = 
				template.postForObject(uri, entity, KakaoPayCancelResponseVO.class);
		//반환
		return response;
	}
	
}