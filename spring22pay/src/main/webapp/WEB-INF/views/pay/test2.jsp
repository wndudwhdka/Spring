<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>진짜같은 결제</h1>

<form class="purchase-form" action="test2" method="post">
	<button type="submit">구매하기</button>

	<c:forEach var="itemDto" items="${itemList}">
	<div>
		<input type="checkbox" name="itemNo" value="${itemDto.itemNo}">
		${itemDto.itemName}
		(
		<strike>${itemDto.itemPrice}</strike>
		→
		<mark>${itemDto.itemDiscount}</mark>
		)
		<input type="number" name="qty" value="1">
	</div>
	</c:forEach>
</form>


<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		//기존 form을 전송하는 대신 체크된 항목들만 새로운 form으로 복사하여 전송
		$(".purchase-form").submit(function(e){
			e.preventDefault();//기본이벤트 차단
			
			//신규 폼을 기존 폼과 동일하게 생성
			const newForm = $("<form>").attr("action", $(this).attr("action"))
															.attr("method", "post");
			
			//기존 폼에 체크된 항목들을 조사하여 신규 폼으로 복사
			$(this).find("[name=itemNo]:checked").each(function(index, element){
				//상품번호와 수량을 찾아야 한다
				const itemNo = $(element).val();
				const qty = $(element).nextAll("[name=qty]").val();
				//console.log(itemNo, qty);
				
				//찾은 데이터를 input[type=hidden]으로 생성하여 신규폼에 추가
				$("<input>").attr("type", "hidden")
									.attr("name", "data["+index+"].itemNo")
									.val(itemNo)
									.appendTo(newForm);
				$("<input>").attr("type", "hidden")
									.attr("name", "data["+index+"].qty")
									.val(qty)
									.appendTo(newForm);
			});
			
			//화면에 존재하지 않는 폼은 전송이 불가능하다(태그도 마찬가지)
			$("body").append(newForm);
			newForm.submit();
		});
	});
</script>