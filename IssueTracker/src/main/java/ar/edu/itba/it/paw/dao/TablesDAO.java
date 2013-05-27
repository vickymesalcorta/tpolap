package ar.edu.itba.it.paw.dao;

import ar.edu.itba.it.paw.domain.Table;


public interface TablesDAO {

	public Table getTables();
	
	public void createTable(Table table);
	
}
