package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.MessagesDAO;

@WebServlet("/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String no = (String)session.getAttribute("no");
		String target = (String)session.getAttribute("target");
		
		int r = MessagesDAO.deleteMessage(no);
		
		if(r == 1) {
			resp.sendRedirect("/detail?no="+target);
		}else {
			resp.sendRedirect("/delete");
		}
	}
}
