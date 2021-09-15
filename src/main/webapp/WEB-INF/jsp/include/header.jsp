<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<header class="d-flex justify-content-between align-items-center bg-secondary">
			<h1 class="ml-3 pt-1 text-light">Memo</h1>
			<c:if test="${not empty userName }">
			<div class="text-white mr-3">
				${userName } <a href="/user/sign_out"> [로그아웃] </a>
			</div>
			</c:if>
		</header>