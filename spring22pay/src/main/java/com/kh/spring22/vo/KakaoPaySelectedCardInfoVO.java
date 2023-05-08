package com.kh.spring22.vo;

import lombok.Data;

@Data
public class KakaoPaySelectedCardInfoVO {
	private String card_bin;//카드 BIN 코드
	private int install_month;//할부 개월 수
	private String card_corp_name;//카드사 정보
	private String interest_free_install;//무이자할부(Y/N)
}