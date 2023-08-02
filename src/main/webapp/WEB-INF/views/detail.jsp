<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기동물 상세정보</title>
<style>
*{
	padding: 5px;
}
h4{
	text-align: center;
}
.msg{
	padding: 4px;
	border: 1px solid #cccccc;
	display: flex; 
	justify-content: space-between; 
	align-items: center;
}
.body{
	text-align: left;
	flex: 1;
	padding-left: 30px;
}
.ac{
	text-align: right; 
	display: flex;
	padding-right: 20px;
}
.action{
	margin-left: 10px; 
	color: black;
	padding: 5px;
}
</style>
</head>
<link rel="stylesheet" href="/resource/css/font.css?${millis }" />
<body>
<div style="display: flex; flex-direction: column;">
	<div style="flex:1; text-align: center; background-color: #bae0ed; width: 200px; margin: auto;">
		<a href="/" style="font-size: 17px; color: #81bdd9; text-decoration: none; background-color: white;">홈으로</a> <a href="/chart" style="font-size: 17px; color: #81bdd9; text-decoration: none; background-color: white;">차트</a>
	</div>
	<div style="flex:5;">
	<div style="border: 1px solid black; width: 80em; margin: 0 auto; display: flex;">
		<div style="margin-top: 10px; flex: 1; margin-left: 150px;">
			<%-- 동물 정보 디스플레이 영역 --%>
			<div style="height: 276px; margin-bottom: 4px;">
				<img src="${item.popfile }" style="object-fit: cover; height: 100%; width: auto;"/>
			</div>
			<div>
				<div><h2 style="margin-left: 77px;">${item.noticeNo }</h2></div>
				<div><span style="margin-left: 150px; font-size: 20px; color: #89D60C">&lt;${item.processState }&gt;</span></div>
				<ul style="list-style: none;">
					<li><b>정보</b> : 나이-${item.age } 몸무게-${item.weight }</li>
					<li><b>특징</b> : ${item.specialMark }</li>
					<li><b>보호소</b> : ${item.careNm } Tel. ${item.careTel }</li>
					<li><b>보호소위치</b> : ${item.careAddr }</li>
				</ul>
			</div>
		</div>
	<div style="flex: 1; width: 50px">
	<div style="width: 80em; margin: 0 auto; display: flex; flex-direction: column; margin-top: 300px;">
		<span style="align-items: center; padding-bottom: 20px;"><b>발견장소</b> : ${item.orgNm } ${item.happenPlace }</span>
		<div id="map" style="width: 400px; height: 300px; max-width: 400px">-</div>
		<c:if test="${empty address }">
			위치가 정확하지 않아 지도를 불러오지 못했습니다.
		</c:if>
		<c:if test="${!empty address }">
		<div style="margin-top: 10px;">
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=94072c8aeb913ec7aeaec91f4cf80dc2"></script>
			<script>
			
				let pos = new kakao.maps.LatLng(${address.lng}, ${address.lat}); //지도의 중심좌표.
				
				let container = document.querySelector('#map'); //지도를 담을 영역의 DOM 레퍼런스
				let options = { //지도를 생성할 때 필요한 기본 옵션
					center : pos, 
					level : 4
				//지도의 레벨(확대, 축소 정도)
				};
		
				let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
				
				let marker = new kakao.maps.Marker({
				    position: pos
				});
				
				marker.setMap(map);
				
			</script>
		</div>
		</c:if>
	</div>
	</div>
	</div>
		<%-- 동물에 대한 코멘트 영역 --%>
		<div style="border: 1px solid black; width: 80em; margin: 0 auto;">
		<h4>희망의 메세지 남기기</h4>
		<form action="/write" method="post">
			<input type="hidden" name="target" value="${item.desertionNo }"/>
			<div style="text-align: center">
				<textarea name="body" style="width: 80%; height: 120px; resize: none"></textarea>
			</div>
			<h4>메세지 비밀번호</h4>
			<div style="text-align: center;">
				<input type="password" name="pass"/>
			</div>
			<div style="text-align: center;">
				<button type="submit">작성</button>
			</div>
		</form>
		</div>
	<div style="border: 1px solid black; width: 80em; margin: 0 auto;">
		<h4>
			희망의 메세지 (${fn:length(messages) }건) 
		</h4>
		<div style="text-align: right;">
			<small>&lt;<b id="count" style="cursor: pointer;">10</b>초 후 갱신 &gt;</small>
			<span id="refresh" style="cursor: pointer; font-size: 25px;">↻</span>
		</div>
		<div id="messages">
			<c:forEach items="${messages }" var="m">
				<div class="msg">
					<span class="body">${m.body }</span>	
					<span class="ac">
						<a href="/passCheck?no=${m.no }&target=${item.desertionNo }&A=M" class="action">수정</a>
						<a href="/passCheck?no=${m.no }&target=${item.desertionNo }&A=D" class="action">삭제</a>
					</span>
				</div>
			</c:forEach>
		</div>
	</div>
	<script>
		const getMessages = function() {
			const xhr = new XMLHttpRequest();
			xhr.open("get","/api/messages?no=${item.desertionNo}",true);
			xhr.send();
			xhr.onreadystatechange = function() {
				if(this.readyState === 4) {
					console.log(this.reponseText);
					const json = JSON.parse(this.responseText);
					const messages = document.querySelector("#messages");
					messages.innerHTML = "";
					for(let o of json) {
						messages.innerHTML += "<div class='msg'>"
							+"<span class='body'>"+o.body+"</span>"
							+"<span class='ac'>"
							+"<a href='/passCheck?no="+o.no+"&target=${item.desertionNo }&A=M' class='action'>수정</a>"
							+"<a href='/passCheck?no="+o.no+"&target=${item.desertionNo }&A=D' class='action'>삭제</a>"
							+"</span>"
							+"</div>";
					}
				}
			}
		};
		
		document.querySelector("#count").onclick = getMessages;
		setInterval(getMessages, 10000);
		
		setInterval(function(){
			let value = parseInt(document.querySelector("#count").innerHTML);
			value--;
			if(value == 0) {
				getMessages();
				value = 10;
			}
			document.querySelector("#count").innerHTML = value;
		}, 1000);
		
	</script>
	</div>
</div>
</body>
</html>