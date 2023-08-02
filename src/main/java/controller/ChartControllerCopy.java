package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import util.AnimalAPI;

public class ChartControllerCopy extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 처리를 해주는 작업
		String[] de = new String[] {"20230412", "20230413", "20230414", "20230415", "20230416", "20230417", "20230418"};
		List<Integer> chart = new ArrayList<>();
		for(String d : de) {
			AnimalResponse response = AnimalAPI.getAnimals(null, null, null, d, d);
			int total = response.getBody().getTotalCount();
			chart.add(total);
		}
		
		 LocalDate today = LocalDate.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	     String formattedDate = today.format(formatter);
	     System.out.println(formattedDate);
		
		// 처리결과를 setAttribute
		Map<String, Object> map = new LinkedHashMap<>();
			map.put("labels", List.of(de));
			map.put("datasets", List.of(Map.of("label", "발생건수", "data", chart)));
			
		Gson gson = new Gson();
		String mapJSON = gson.toJson(map);
		System.out.println(mapJSON);
		req.setAttribute("mapJSON", mapJSON);
//		req.setAttribute("de", de);
//		req.setAttribute("chart", chart);
		// 뷰로 제어를 넘기는 작업
		req.getRequestDispatcher("/WEB-INF/views/chart.jsp").forward(req, resp);
	}
}
