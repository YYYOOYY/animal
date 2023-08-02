<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기동물 정보 조회</title>
<style>
* {
	box-sizing: border-box;
	padding: 5px;
}
input, select, button {
	padding : 4px 8px;
	height: 40px;
}
</style>
</head>
<link rel="stylesheet" href="/resource/css/font.css?${millis }" />
<body style="margin: 0">
<div style="display: flex; flex-direction: column;">
	<div style="flex:1; text-align: center; background-color: #bae0ed; width: 200px; margin: auto;">
		<a href="/" style="font-size: 17px; color: #81bdd9; text-decoration: none; background-color: white;">홈으로</a> <a href="/chart" style="font-size: 17px; color: #81bdd9; text-decoration: none; background-color: white;">차트</a>
	</div>
	<div style="text-align: center; flex: 9;">
		<h1>유기동물 정보 조회 <small>(OPEN API 활용)</small></h1>
		<div>
			<form action="/index" style="display: flex; align-items: center; 
				justify-content: center; gap : 10px">
				<select name="uprCd">
					<option value="" ${param.upkind eq '' ? 'selected' : ''}>전국</option>
					<c:forEach items="${sidos }" var="sido">
					<option value="${sido.orgCd }" ${param.uprCd eq sido.orgCd ? 'selected' : ''}>${sido.orgdownNm }</option>

					</c:forEach>
				</select>
				<select name="upkind">
					<option value="" ${param.upkind eq '' ? 'selected' : ''}>전체</option>
					<option value="417000" ${param.upkind eq '417000' ? 'selected' : ''}>개</option>
					<option value="422400" ${param.upkind eq '422400' ? 'selected' : ''}>고양이</option>
					<option value="429900" ${param.upkind eq '429900' ? 'selected' : ''}>기타</option>					
				</select>
				<input type="date" name="bgnde" value="${param.bgnde }"/> ~ <input type="date" name="endde" value="${param.endde }"/>
				<button type="submit">조회</button>
			</form>	
		</div>
		<div>
			<c:choose>
				<c:when test="${total eq 0 }">
					조회하는 유기동물정보가 없습니다.
				</c:when>
				<c:otherwise>
					총 <b>${total }</b>건의 유기동물정보가 존재합니다.
				</c:otherwise>
			</c:choose>
		</div>
		<%--정보 보여주는 곳--%>
		<div style="display: flex; flex-wrap: wrap;">
			<c:forEach items="${datas }" var="obj">
			<div style="width: 33.3%; padding: 10px; height: 280px; cursor: pointer;" 
					onclick="location.href='/detail?no=${obj.desertionNo}'">
				<div style="width: 100%; border: 1px solid #dddddd; padding: 4px; height: 100%;">
						<div style="height: 10%; font-size: 20px"><b>${obj.kindCd }</b></div>
						<div style="height: 50%"><img src="${obj.filename }" style="height: 100%;"/></div>
						<div style="height: 10%">${obj.happenDt }</div>
						<div style="height: 10%; text-overflow: ellipsis; white-space: nowrap; overflow: hidden">
							<span>${obj.orgNm } ${obj.happenPlace }</span>
						</div>
						<div
							style="height: 20%; text-overflow: ellipsis; white-space: nowrap; overflow: hidden">
						<c:choose>
							<c:when test="${fn:length(obj.specialMark) gt 15}">
								(${fn:substring(obj.specialMark, 0, 15) }...)
							</c:when>
							<c:otherwise>
								(${obj.specialMark })
							</c:otherwise>
						</c:choose>
						</div>
				</div>
			</div>
			</c:forEach>				
		</div>
		<%-- --%>
		<div style="font-size: 17px; color: ">
		<c:set var="currentPage" value="${empty param.pageNo ? 1 : param.pageNo }"/>
		<%--이전버튼--%>
			<c:if test="${existPrev }">
				<c:url value="/" var="target">
					<c:param name="upkind" value="${param.upkind }"/>
					<c:param name="uprCd" value="${param.uprCd }"/>
					<c:param name="pageNo" value="${start-1 }" />
					<c:param name="bgnde" value="${param.bgnde }"/>
					<c:param name="endde" value="${param.endde }"/>
				</c:url>
				<a href="${target}" style="color: black;">&lt;</a>
			</c:if>
		<%----------%>
			<c:forEach var="p" begin="${start }" end="${last }">
				<c:url value="/" var="target">
					<c:param name="upkind" value="${param.upkind }"/>
					<c:param name="uprCd" value="${param.uprCd }"/>
					<c:param name="pageNo" value="${p }" />
					<c:param name="bgnde" value="${param.bgnde }"/>
					<c:param name="endde" value="${param.endde }"/>	
				</c:url>
		<%--현재 누른 페이지--%>
				<c:choose>
					<c:when test="${p eq currentPage }">
						<b style="color: orange;">${p }</b>
					</c:when>
					<c:otherwise>
						<a href="${target }" style="color: black;">${p }</a>
					</c:otherwise>
				</c:choose>
		<%----------------%>
			</c:forEach>
		<%--다음버튼 --%>
			<c:if test="${existNext }">
				<c:url value="/" var="target">
					<c:param name="upkind" value="${param.upkind }"/>
					<c:param name="uprCd" value="${param.uprCd }"/>
					<c:param name="pageNo" value="${last + 1 }" />
					<c:param name="bgnde" value="${param.bgnde }"/>
					<c:param name="endde" value="${param.endde }"/>
				</c:url>
				<a href="${target }" style="color: black;">&gt;</a>
			</c:if>
		<%----------%>
		</div>
	</div>
</div>
</body>
</html>