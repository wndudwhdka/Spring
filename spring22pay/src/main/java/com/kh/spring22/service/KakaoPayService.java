package com.kh.spring22.service;

import java.net.URISyntaxException;

import com.kh.spring22.vo.KakaoPayApproveRequestVO;
import com.kh.spring22.vo.KakaoPayApproveResponseVO;
import com.kh.spring22.vo.KakaoPayCancelRequestVO;
import com.kh.spring22.vo.KakaoPayCancelResponseVO;
import com.kh.spring22.vo.KakaoPayOrderRequestVO;
import com.kh.spring22.vo.KakaoPayOrderResponseVO;
import com.kh.spring22.vo.KakaoPayReadyRequestVO;
import com.kh.spring22.vo.KakaoPayReadyResponseVO;
import com.kh.spring22.vo.PurchaseListVO;

public interface KakaoPayService {
	//준비 - ready
	KakaoPayReadyResponseVO ready(KakaoPayReadyRequestVO vo) throws URISyntaxException;
	//승인 - approve
	KakaoPayApproveResponseVO approve(KakaoPayApproveRequestVO vo) throws URISyntaxException;
	//승인 - approveWithDetail
	KakaoPayApproveResponseVO approveWithDetail(KakaoPayApproveRequestVO vo, PurchaseListVO listVO) throws URISyntaxException;
	//조회 - order
	KakaoPayOrderResponseVO order(KakaoPayOrderRequestVO vo) throws URISyntaxException;
	//취소 - cancel
	KakaoPayCancelResponseVO cancel(KakaoPayCancelRequestVO vo) throws URISyntaxException;
}
