<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1> 결제 내역</h1>

<table>
	<thead>
		<tr>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="paymentDto" items="${list}">
		<tr>
			<td>${paymentDto.paymentNo}</td>
			<td>${paymentDto.paymentName}</td>
			<td>${paymentDto.paymentTotal}</td>
			<td>${paymentDto.paymentStatus}</td>
			<td>${paymentDto.paymentTime}</td>
			<td>
				
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>