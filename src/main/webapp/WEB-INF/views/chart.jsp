<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최근 일주일간 발생건수</title>
<style>
* {
	box-sizing: border-box;
	padding: 5px;
}
</style>
</head>
<link rel="stylesheet" href="/resource/css/font.css?${millis }" />
<body style="margin: 0">
<div style="display: flex; flex-direction: column;">
	<div style="flex:1; text-align: center; background-color: #bae0ed; width: 200px; margin: auto;">
		<a href="/" style="font-size: 17px; color: #81bdd9; text-decoration: none; background-color: white;">홈으로</a>
	</div>
	<div style="text-align: center; flex: 9">
		<div style="max-height: 300px; display: flex; justify-content: center;">
		  <canvas id="myChart"></canvas>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script>
		  const ctx = document.querySelector('#myChart');
		
		  new Chart(ctx, {
		    type: 'line',
		    data : {
				  labels: ${labels},
					  datasets: [{
					    label: '최근 일주일간 발생건수',
					    data: ${counts}
					  }]
					}
		  });
		</script>
	</div>
</div>
</body>
</html>