package ar.edu.itba.it.paw.dao;

import java.util.List;

import ar.edu.itba.it.paw.domain.Table;


public interface TablesDAO {

	public List<String> getTables();
	
	public void createTable(Table table);
	
	public void executeQuery(String query);
}
