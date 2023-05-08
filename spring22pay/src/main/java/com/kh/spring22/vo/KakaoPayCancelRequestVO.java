package com.kh.spring22.vo;

import lombok.Data;

//카카오페이 취소 요청을 위한 데이터
@Data
public class KakaoPayCancelRequestVO {
	private String tid;//거래번호
	private int cancel_amount;//취소금액
	private int cancel_tax_free_amount;//최소 비과세 금액(0으로 설정)
}