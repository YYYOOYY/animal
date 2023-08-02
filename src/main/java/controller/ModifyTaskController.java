package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.MessagesDAO;

@WebServlet("/modify-task")
public class ModifyTaskController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		
		HttpSession session = req.getSession();
		String no = (String)session.getAttribute("no");
		String target = (String)session.getAttribute("target");
		String body = req.getParameter("body");
		
		int r = MessagesDAO.modifyMessage(no, body);
		
		if(r == 1) {
			resp.sendRedirect("/detail?no="+target);
		}else {
			resp.sendRedirect("/modify");
		}
	}
}
