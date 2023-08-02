<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 수정</title>
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
	<div style="border: 1px solid black; width: 80%; margin: 0 auto;">
		<h4>메세지 수정</h4>
		<form action="/modify-task" method="post">			
			<div id="comment-area" style="text-align: center">
				<textarea name="body" style="width: 80%; height: 120px; resize: none"></textarea>
			</div>
			<div id="button" style="text-align: center;">
				<button type="submit">작성</button>
			</div>
		</form>
	</div>
</body>
</html>