<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<style>
*{
	padding: 2px
}
h4{
	text-align: center;
}
</style>
</head>
<link rel="stylesheet" href="/resource/css/font.css?${millis }" />
<body>
	<h4>메세지 수정</h4>
		<form action="/passCheck-task" method="post">
			<div id="comment-area" style="text-align: center">
				<input type="password" name="pass"/>
			</div>
			<div style="text-align: center; color: red;">
				<c:if test="${param.error eq 'r' }">비밀번호가 일치하지 않습니다</c:if>
			</div>
			<div id="button" style="text-align: center;">
				<button type="submit">확인</button>
			</div>
		</form>
</body>
</html>