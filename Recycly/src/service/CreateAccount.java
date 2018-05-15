package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import utility.Encoder;
import utility.Input;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/createAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name, username, password, confirm;
		if(!Input.isEmpty(request.getParameter("name")) && !Input.isEmpty(request.getParameter("username")) && !Input.isEmpty(request.getParameter("password")) && !Input.isEmpty(request.getParameter("confirm"))) {
			name = Encoder.encodeHTML(request.getParameter("name"));
			username = Encoder.encodeHTML(request.getParameter("username"));
			password = Encoder.encodeHTML(request.getParameter("password"));
			confirm = Encoder.encodeHTML(request.getParameter("confirm"));
			if(password.equals(confirm)) {
				boolean accountExists = false;
				for(Object obj : new Account().getList()) 
					if(((Account) obj).getUsername().equals(username)) accountExists = true;
				if(accountExists)response.getWriter().write("Account Already Exists");
				else {
					Account acc = new Account.AccountBuilder()
							.name(name)
							.username(username)
							.password(password)
							.type("User")
							.build();
							acc.save();
							response.getWriter().write(acc.toString());
				}
			}
			else response.getWriter().write("Password Does Not Match");
		}
		else response.getWriter().write("Fill All Required Fields");
		
	}

}
