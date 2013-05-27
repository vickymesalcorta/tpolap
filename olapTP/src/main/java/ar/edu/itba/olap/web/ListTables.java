package ar.edu.itba.olap.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.olap.services.TablesServices;
import ar.edu.itba.olap.services.impl.TablesServicesImpl;

@SuppressWarnings("serial")
public class ListTables extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		TablesServices tablesServices = TablesServicesImpl.getInstance();
		List<String> tables = tablesServices.getTables();
		req.setAttribute("tables", tables);
		
		List<String> columns = tablesServices.getTableColmnsNames("usuarios");
		req.setAttribute("columns", columns);
		
		req.setAttribute("message", "Listado de tablas");
		
		req.getRequestDispatcher("/WEB-INF/jsp/listTables.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
