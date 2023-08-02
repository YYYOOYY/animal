package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/passCheck")
public class PassCheckController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String no = req.getParameter("no");
		String target = req.getParameter("target");
		String action = req.getParameter("A");
		
		HttpSession session = req.getSession();
		
		session.setAttribute("no", no);
		session.setAttribute("target", target);
		session.setAttribute("action", action);
	
		req.getRequestDispatcher("/WEB-INF/views/passCheck.jsp").forward(req, resp);
	}
}
