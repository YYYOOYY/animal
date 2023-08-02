package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.vo.Message;
import repository.MessagesDAO;

@WebServlet("/api/messages")
public class MessageController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		
		resp.setContentType("text/plain;charset=utf-8");
		
		List<Message> li = MessagesDAO.readMessages(no);
		
		Gson gson = new Gson();
		
		PrintWriter out = resp.getWriter();
		
		out.println(gson.toJson(li));
	}
}
