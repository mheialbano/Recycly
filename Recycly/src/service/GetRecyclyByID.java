package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecycleItem;
import utility.Display;
import utility.Encoder;

/**
 * Servlet implementation class GetRecyclys
 */
@WebServlet("/getrecyclybyid")
public class GetRecyclyByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    if(request.getSession().getAttribute("account")!=null) response.getWriter().write(Display.getJSON(new RecycleItem().getModel(Integer.parseInt(Encoder.encodeSQL(request.getAttribute("id").toString())))));
	    else response.getWriter().write("Unauthorized Access");
	}

}
