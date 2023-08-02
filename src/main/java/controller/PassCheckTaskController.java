package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.MessagesDAO;

@WebServlet("/passCheck-task")
public class PassCheckTaskController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		String no = (String)session.getAttribute("no");
		String action = (String)session.getAttribute("action");
	
		String pass = req.getParameter("pass");
		String targetPass = MessagesDAO.findByPass(no);
		if(targetPass == null) {
			targetPass = "";
		}
		
		if(pass.equals(targetPass)) {
			if(action.equals("M")) {
				resp.sendRedirect("/modify");
			}else if(action.equals("D")) {
				resp.sendRedirect("/delete");
			}
		}else {
			req.getRequestDispatcher("/WEB-INF/views/passCheck.jsp?error=r").forward(req, resp);
		}
	}
}
