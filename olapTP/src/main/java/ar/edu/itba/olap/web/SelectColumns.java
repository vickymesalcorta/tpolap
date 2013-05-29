package ar.edu.itba.olap.web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.olap.services.TablesServices;
import ar.edu.itba.olap.services.impl.TablesServicesImpl;

@SuppressWarnings("serial")
public class SelectColumns extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/jsp/selectColumns.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uniqueTable = req.getParameter("table");
		HttpSession session = req.getSession();
		session.setAttribute("uniqueTable", uniqueTable);
		
		TablesServices tablesServices = TablesServicesImpl.getInstance();
		
		List<String> columns = tablesServices.getTableColmnsNames(uniqueTable);
		session.setAttribute("columns", columns);
		req.setAttribute("columns", columns);
		
		req.setAttribute("uniqueTable", uniqueTable);
		
		req.setAttribute("message", "Columnas de la tabla " + uniqueTable);
		
		// Despues de procesar el de entrada, se pide una lista con todos los nombres de las columnas necesarios, ahora lo cableo para probar
		List<String> multidimNames = new LinkedList<String>();
		multidimNames.add("asdf1");
		multidimNames.add("asdf2");
		multidimNames.add("asdf3");
		multidimNames.add("asdf4");
		multidimNames.add("asdf5");
		multidimNames.add("asdf6");
		multidimNames.add("asdf7");
		multidimNames.add("asdf8");
		
		req.setAttribute("multidimNames", multidimNames);
		
		session.setAttribute("multidimNames", multidimNames);
		

		req.getRequestDispatcher("/WEB-INF/jsp/selectColumns.jsp").forward(req, resp);
	}

}
