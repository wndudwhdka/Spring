package com.kh.spring22.vo;

import lombok.Data;

@Data
public class KakaoPayPaymentActionDetailVO {
	private String aid;//요청(Request) 고유 번호
	private String approved_at;//거래시각
	private int amount;//결제/취소 총액
	private int point_amount;//결제/취소 포인트 총액
	private int discount_amount;//할인 금액
	private int green_deposit;//컵 보증금
	private String payment_action_type;//결제 유형(PAYMENT/CANCEL/ISSUED_SID)
	private String payload;//요청 메모
}