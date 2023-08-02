package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.animal.AnimalResponse;
import data.sido.SidoResponse;
import util.AnimalAPI;
import util.SidoAPI;

@WebServlet("/index")
public class IndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// API 연동해서 받아온 데이터를 setAttribute 해주면 될 것 같음
		String upkind = req.getParameter("upkind");
		String uprCd = req.getParameter("uprCd");
		String pageNo = req.getParameter("pageNo");
		String bgnde = req.getParameter("bgnde");
		String endde = req.getParameter("endde");
		
		bgnde = bgnde != null ? bgnde.replaceAll("-", "") : bgnde;
		endde = endde != null ? endde.replaceAll("-", "") : endde;
		
		AnimalResponse animalResponse = AnimalAPI.getAnimals(upkind, uprCd, pageNo, bgnde, endde);
		SidoResponse sidoResponse = SidoAPI.getSidos();
		
		if(sidoResponse != null) {
			req.setAttribute("sidos", sidoResponse.getBody().getItems().getItem());
		}
		
		if(animalResponse != null) {			
			req.setAttribute("datas", animalResponse.getBody().getItems().getItem());
			req.setAttribute("total", animalResponse.getBody().getTotalCount());
		}
		
		int p;
		if(req.getParameter("pageNo") == null) {
			p = 1;
		}else {
			p = Integer.parseInt(req.getParameter("pageNo"));
		}
		
		
		int total = animalResponse.getBody().getTotalCount();
		int lastPage = total / 12 + (total % 12 > 0 ? 1 : 0);
		
		int start = p % 10 == 0 ? p - 9 : p - (p % 10) + 1;
		int last = p % 10 == 0 ? p : p - (p % 10) + 10;
		
		last = last > lastPage ? lastPage : last;
		
		req.setAttribute("start", start);
		req.setAttribute("last", last);
		
		boolean existPrev = start == 1 ? false : true;
		boolean existNext = last < lastPage-1 ? true : false;	
		
		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);
		
		
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
}
