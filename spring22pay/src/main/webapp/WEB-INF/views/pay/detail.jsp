<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>결제 상세 정보</h1>
    
<h2>상품명 : ${paymentDto.paymentName}</h2>
<%-- <h2>총금액 : ${paymentDto.paymentTotal}원</h2> --%>
<h2>총금액 : 
<fmt:formatNumber value="${paymentDto.paymentTotal}" pattern="#,##0.00"></fmt:formatNumber>
원</h2>
<h2>잔여금액 : ${paymentDto.paymentRemain}원</h2>
<h2>현재상태 : ${paymentDto.paymentStatus}</h2>

<!-- 결제 취소 버튼 : 잔여금액이 존재한다면 -->
<c:if test="${paymentDto.paymentRemain > 0}">
	<a href="cancel?paymentNo=${paymentDto.paymentNo}">결제 취소</a>
</c:if>

<hr>

<h2>결제 상태 : ${response.status}</h2>
<h2>주문 번호 : ${response.partner_order_id}</h2>
<h2>주문자 : ${response.partner_user_id}</h2>
<h2>결제 수단 : ${response.payment_method_type}</h2>

<h2>결제 금액 : ${response.amount.total} 원</h2>
<h2>취소 금액 : ${response.canceled_amount.total} 원</h2>
<h2>취소 가능 : ${response.cancel_available_amount.total} 원</h2>

<h2>상품명 : ${response.item_name}</h2>
<h2>상품코드 : ${response.item_code}</h2>
<h2>구매수량 : ${response.quantity}</h2>

<h2>준비시각 : <fmt:formatDate value="${response.created_at}" pattern="y년 M월 d일 E a h시 m분 s초"/></h2>
<h2>승인시각 : <fmt:formatDate value="${response.approved_at}" pattern="y년 M월 d일 E a h시 m분 s초"/></h2>
<h2>취소시각 : <fmt:formatDate value="${response.canceled_at}" pattern="y년 M월 d일 E a h시 m분 s초"/></h2>

<%-- 카드정보는 카드결제일 때만 나옴 --%>
<c:if test="${response.payment_method_type == 'CARD'}"></c:if>
<c:if test="${response.selected_card_info != null}">
	<hr>
	<h3>카드사 정보 : ${response.selected_card_info.card_corp_name}</h3>
	<h3>카드 BIN 코드 : ${response.selected_card_info.card_bin}</h3>
	<h3>할부 개월 수 : ${response.selected_card_info.install_month}</h3>
	<h3>무이자할부 여부 : ${response.selected_card_info.interest_free_install}</h3>
	<hr>
</c:if>

<%-- 결제 전체 순서에 따른 내역 --%>
<h2>결제 순서 및 상세 내역</h2>
<c:forEach var="paymentAction" items="${response.payment_action_details}">
	<hr>
	<h3>요청번호 : ${paymentAction.aid}</h3>
	<h3>거래시각 : ${paymentAction.approved_at}</h3>
	<h3>총액 : ${paymentAction.amount} 원</h3>
	<h3>포인트 : ${paymentAction.point_amount} 원</h3>
	<h3>할인 : ${paymentAction.discount_amount} 원</h3>
	<h3>유형 : ${paymentAction.payment_action_type}</h3>
</c:forEach>