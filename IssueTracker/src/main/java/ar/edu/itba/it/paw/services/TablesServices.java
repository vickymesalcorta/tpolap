package ar.edu.itba.it.paw.services;

import java.util.List;

import ar.edu.itba.it.paw.domain.Table;

public interface TablesServices {

//	public void saveUser(User user) throws RegisteredUsernameException;
//
//	public User getUser(String username);
//	
//	public User getUser(int id);
//	
//	public List<User> getAllUsers();
//
//	public boolean authenticate(String username, String password);
//	
//	public void asignIssue(String username, Issue issue);
	
	public List<String> getTables();
	
	public void createTable(Table table);
	
	public void executeQuery(String query);
}
