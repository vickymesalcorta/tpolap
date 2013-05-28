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
public class SelectTable extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		session.setAttribute("uniqueTable", null);
		session.setAttribute("myMessage", "mensaje que viene de la sesion");
		
		TablesServices tablesServices = TablesServicesImpl.getInstance();
		List<String> tables = tablesServices.getTables();
		req.setAttribute("tables", tables);
		
//		List<String> columns = tablesServices.getTableColmnsNames("usuarios");
//		req.setAttribute("columns", columns);
		
		req.setAttribute("message", "Seleccione la tabla sobre la que quiere hacer el MDX");
		
		req.getRequestDispatcher("/WEB-INF/jsp/selectTable.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uniqueTable = req.getParameter("table");
		HttpSession session = req.getSession();
		session.setAttribute("uniqueTable", uniqueTable);
		
		TablesServices tablesServices = TablesServicesImpl.getInstance();
		
		List<String> columns = tablesServices.getTableColmnsNames(uniqueTable);
		req.setAttribute("columns", columns);
		
		req.setAttribute("uniqueTable", uniqueTable);
		
		req.setAttribute("message", "Columnas de la tabla " + uniqueTable);

		req.getRequestDispatcher("/WEB-INF/jsp/listColumns.jsp").forward(req, resp);
	}

}
