package service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import utility.Encoder;
import utility.Encryptor;
import utility.Input;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/login")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "", password = "";
		if(!Input.isEmpty(request.getParameter("username"))) username = Encoder.encodeHTML(request.getParameter("username"));
		if(!Input.isEmpty(request.getParameter("password"))) password = Encoder.encodeHTML(request.getParameter("password"));
		Account acc = (Account) new Account().find("username", username);
		if(acc==null) response.getWriter().write("Username Does Not Exist");
		else {
			if(acc.getPassword().equals(Encryptor.hash(password))) {
				request.getSession().setAttribute("account", acc);
				response.getWriter().write(acc.toString());
			}
			else response.getWriter().write("Incorrect Password");
		}
	}

}
