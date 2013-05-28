package ar.edu.itba.olap.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.olap.services.TablesServices;
import ar.edu.itba.olap.services.impl.TablesServicesImpl;

@SuppressWarnings("serial")
public class Index extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		session.setAttribute("uniqueTable", null);
		session.setAttribute("myMessage", "mensaje que viene de la sesion");
		
		TablesServices tablesServices = TablesServicesImpl.getInstance();
		List<String> tables = tablesServices.getTables();
		if(tables.size() > 0) {
			req.setAttribute("tables", tables);
		} else {
			req.setAttribute("tables", null);
		}
		
		req.setAttribute("message", "Seleccione la tabla sobre la que quiere hacer el MDX");
		
		req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}

}
