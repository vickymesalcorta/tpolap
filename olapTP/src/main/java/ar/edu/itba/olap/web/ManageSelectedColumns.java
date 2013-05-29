package ar.edu.itba.olap.web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.olap.domain.Column;
import ar.edu.itba.olap.domain.MultiDim;
import ar.edu.itba.olap.domain.MultiDimToTablesDictionary;
import ar.edu.itba.olap.domain.MultiDimToTablesDictionaryImpl;
import ar.edu.itba.olap.domain.OutputGenerator;

@SuppressWarnings("serial")
public class ManageSelectedColumns extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setAttribute("message", "Su archivo esta listo");
		req.getRequestDispatcher("/WEB-INF/jsp/manageSelectedColumns.jsp").forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setAttribute("message", "Su archivo esta listo");
		HttpSession session = req.getSession();
		String tableName = (String) session.getAttribute("uniqueTable");
		
		List<Column> databaseColumns = (List<Column>) session.getAttribute("columns");
		List<Column> multidimColumns = (List<Column>) session.getAttribute("multidimColumns");
		
		List<MultiDimToTablesDictionary> columnsInTable = new LinkedList<MultiDimToTablesDictionary>();
		for(Column multidimColumn : multidimColumns) {
			String columnTableName = (String) req.getParameter(multidimColumn.getName());
			MultiDimToTablesDictionary dic = new MultiDimToTablesDictionaryImpl(multidimColumn.getName(), columnTableName);
			columnsInTable.add(dic);
		}
		
		req.setAttribute("columnsInTable", columnsInTable);
		
		MultiDim multidim = (MultiDim) session.getAttribute("multidim");
		
		OutputGenerator outputGenerator = new OutputGenerator();
		outputGenerator.generateOutput(columnsInTable, multidim, tableName);
		
		//TODO chequear los tipos
		if(typesAreWrong(multidimColumns, databaseColumns, columnsInTable)) {
			String msg = "Los tipos de las columnas seleccionadas no coinciden con los de la base de datos. Su archivo fue creado, sin embargo puede no funcionar.";
			req.setAttribute("columnTypeWrong", msg);
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/manageSelectedColumns.jsp").forward(req, resp);
	}
	
	private boolean typesAreWrong(List<Column> multidimColumns, List<Column> databaseColumns, List<MultiDimToTablesDictionary> dictionary) {
		for(Column multidimColumn : multidimColumns) {
			for(Column databaseColumn : databaseColumns) {
				for(MultiDimToTablesDictionary dic : dictionary) {
					if(dic.getMultidimName().equalsIgnoreCase(multidimColumn.getName()) 
							&& dic.getColumnName().equalsIgnoreCase(databaseColumn.getName())) {
						if(!typesAreEqual(multidimColumn.getType(), databaseColumn.getType())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean typesAreEqual(String multidimType, String databaseType) {
		if(multidimType.equalsIgnoreCase("numeric")) {
			if(!databaseType.equalsIgnoreCase("numeric")) {
				return false;
			}
		}
		if(multidimType.equalsIgnoreCase("string")) {
			if(!databaseType.equalsIgnoreCase("character_varying")) {
				return false;
			}
		}
		if(multidimType.equalsIgnoreCase("geometry")) {
			if(!databaseType.equalsIgnoreCase("USER-DEFINED")) {
				return false;
			}
		}
		if(multidimType.equalsIgnoreCase("timestamp")) {
			if(!databaseType.equalsIgnoreCase("timestamp")) {
				return false;
			}
		}
//		if(multidimType.equalsIgnoreCase("")) {
//			if(!databaseType.equalsIgnoreCase("")) {
//				return false;
//			}
//		}
//		if(multidimType.equalsIgnoreCase("")) {
//			if(!databaseType.equalsIgnoreCase("")) {
//				return false;
//			}
//		}
//		if(multidimType.equalsIgnoreCase("")) {
//			if(!databaseType.equalsIgnoreCase("")) {
//				return false;
//			}
//		}
		return true;
	}

}
