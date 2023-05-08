package com.kh.spring22.vo;

import java.sql.Date;

import lombok.Data;

//카카오페이 결제 취소에 대한 응답 데이터
@Data
public class KakaoPayCancelResponseVO {
	private String aid;//요청 고유번호
	private String tid;//거래번호
	private String cid;//가맹점코드
	private String status;//결제 상태
	private String partner_order_id;//주문번호
	private String partner_user_id;//주문자
	private String payment_method_type;//결제수단(CARD/MONEY)

	private KakaoPayAmountVO amount;//결제금액정보
	private KakaoPayAmountVO approved_cancel_amount;//지금 취소된금액정보
	private KakaoPayAmountVO canceled_amount;//취소된금액정보
	private KakaoPayAmountVO cancel_available_amount;//취소가능금액정보
	
	private String item_name;//상품명
	private String item_code;//상품코드
	private int quantity;//상품수량
	
	private Date created_at;//결제 준비 요청 시각
	private Date approved_at;//결제 승인 시각
	private Date canceled_at;//결제 취소 시각
	
	private String payload;//결제 메모
}