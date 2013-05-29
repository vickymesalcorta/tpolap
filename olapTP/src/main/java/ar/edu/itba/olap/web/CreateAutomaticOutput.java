package ar.edu.itba.olap.web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.olap.domain.MultiDimToTablesDictionary;
import ar.edu.itba.olap.domain.MultiDimToTablesDictionaryDummy;
import ar.edu.itba.olap.domain.OutputGenerator;

@SuppressWarnings("serial")
public class CreateAutomaticOutput extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/jsp/manageSelectedColumns.jsp").forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setAttribute("message", "Su archivo esta listo");
		HttpSession session = req.getSession();
		
		List<String> multidimNames = new LinkedList<String>();
		multidimNames.add("asdf1");
		multidimNames.add("asdf2");
		multidimNames.add("asdf3");
		multidimNames.add("asdf4");
		multidimNames.add("asdf5");
		multidimNames.add("asdf6");
		multidimNames.add("asdf7");
		multidimNames.add("asdf8");
		
		List<MultiDimToTablesDictionary> columnsInTable = new LinkedList<MultiDimToTablesDictionary>();
		
		for(String multidimName : multidimNames) {
			MultiDimToTablesDictionaryDummy dic = new MultiDimToTablesDictionaryDummy(multidimName);
			columnsInTable.add(dic);
		}
		
		req.setAttribute("columnsInTable", columnsInTable);
		
		OutputGenerator outputGenerator = new OutputGenerator();
		outputGenerator.generateOutput(columnsInTable, null, null);
		
		req.getRequestDispatcher("/WEB-INF/jsp/manageSelectedColumns.jsp").forward(req, resp);
	}

}
