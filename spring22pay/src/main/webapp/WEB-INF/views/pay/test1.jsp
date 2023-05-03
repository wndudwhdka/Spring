<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>결제 테스트(무식하게)</h1>

<form action="test1" method="post">
	상품명 : <input type="text" name="item_name"> <br><br>
	상품수량 : <input type="number" name="quantity" value="1"> <br><br>
	판매금액 : <input type="number" name="total_amount"><br><br>
	<button type="submit">구매하기</button>
</form>