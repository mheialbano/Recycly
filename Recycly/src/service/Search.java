package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RecycleItem;
import utility.Display;

/**
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Object> list = new ArrayList<Object>();
	    for(Object obj : new RecycleItem().search(request.getParameter("key"))) {
	    	list.add(obj);
	    }
	    response.getWriter().write(Display.getJSON(list));
	}
	
	public String search(String searchString){
		List<RecycleItem> list = new ArrayList<RecycleItem>();
		for(Object obj : new RecycleItem().findList("name", searchString)){
			 RecycleItem rec = (RecycleItem) obj;
		}
		for(Object obj : new RecycleItem().findChild(searchString)){
			 RecycleItem rec = (RecycleItem) obj;
					 list.add(rec);
		}
		
		return Display.getJSON(list);
	}

}
