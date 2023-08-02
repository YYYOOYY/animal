package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@WebServlet("/chart")
public class ChartController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		Date today = new Date(System.currentTimeMillis());
		String todayStr = sdf.format(today);
		*/
		// 데이터 처리를 해주는 작업
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<String> de = new ArrayList<>();
		
		for(int i=6; i>=0; i--) {
			Date target = new Date(System.currentTimeMillis() - 1000*60*60*24*i);
			String s = sdf.format(target);
			de.add(s);
		}
		Gson gson = new Gson();
		
		req.setAttribute("labels", gson.toJson(de));
		List<Integer> counts = new ArrayList<>();
		for(String d : de) {
			AnimalResponse response = AnimalAPI.getAnimals(null, null, null, d, d);
			int total = response.getBody().getTotalCount();
			counts.add(total);
		}
		req.setAttribute("counts", gson.toJson(counts));
		
		// 뷰로 제어를 넘기는 작업
		req.getRequestDispatcher("/WEB-INF/views/chart.jsp").forward(req, resp);
	}
}
