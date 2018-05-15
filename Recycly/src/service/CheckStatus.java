package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecycleItem;
import utility.Display;

/**
 * Servlet implementation class CheckStatus
 */
@WebServlet("/CheckStatus")
public class CheckStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setCharacterEncoding("UTF-8");
	    if(request.getSession().getAttribute("account")!=null) response.getWriter().write("Logged In");
	    else response.getWriter().write("Null");
	}

}
