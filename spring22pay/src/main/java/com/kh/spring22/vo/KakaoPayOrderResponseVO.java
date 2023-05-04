package com.kh.spring22.vo;

public class KakaoPayOrderResponseVO {
	private String tid; // 거래번호
	private String cid; // 가맹점코드
	private String status; // 결제 상태
	private String partner_order_id; // 주문번호
	private String payment_method_type; // 결제수단(CARD/MONEY)
}
