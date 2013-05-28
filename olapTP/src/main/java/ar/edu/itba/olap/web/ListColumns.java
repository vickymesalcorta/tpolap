package ar.edu.itba.olap.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ListColumns extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		HttpSession session = req.getSession();
//		String uniqueTable = (String) session.getAttribute("uniqueTable");
//		
//		req.setAttribute("message", "asdfasdfasdfsdf");
//		
//		TablesServices tablesServices = TablesServicesImpl.getInstance();
//		
//		List<String> columns = tablesServices.getTableColmnsNames(uniqueTable);
//		req.setAttribute("columns", columns);
//		
//		req.setAttribute("uniqueTable", uniqueTable);
//		
//		req.setAttribute("message", "uniqueTable is: " + uniqueTable + " columns length is: " + columns.size());
		
		
		req.getRequestDispatcher("/WEB-INF/jsp/listColumns.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		this.doGet(req,resp);
	}

}
