package ar.edu.itba.it.paw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.dao.TablesDAO;
import ar.edu.itba.it.paw.domain.ConnectionManager;
import ar.edu.itba.it.paw.domain.Table;
import ar.edu.itba.it.paw.domain.exceptions.DatabaseException;

public class DatabaseTablesDAO implements TablesDAO {

	private static TablesDAO instance;
	
	public static synchronized TablesDAO getInstance(){
		if(instance == null)
			instance = new DatabaseTablesDAO();
		return instance;
	}
	

	@Override
	public List<String> getTables() {
//		select * from information_schema.tables where table_type = 'BASE TABLE' and table_schema = 'public'
		ConnectionManager manager = ConnectionManager.getInstance();
		Connection conn = manager.getConnection();
		PreparedStatement stmt;
		List<String> tables = new LinkedList<String>();
		
		try {
			stmt = conn.prepareStatement("select * from information_schema.tables where table_type = 'BASE TABLE' and table_schema = 'public'");
			ResultSet cur = stmt.executeQuery();
			
			
			while(cur.next()){
				tables.add(cur.getString("table_name"));
			}
			
			manager.closeConnection();
		} catch(Exception e){
			throw new DatabaseException();
		}
		manager.closeConnection();
		return tables;
		
//		ConnectionManager manager = ConnectionManager.getInstance();
//		Connection conn = manager.getConnection();
//		PreparedStatement stmt;
//		List<User> users = new ArrayList<User>();
//		
//		try{
//			stmt = conn.prepareStatement("select * from users");
//			ResultSet cur = stmt.executeQuery();
//			
//			while(cur.next()){
//				User user = null;
//				user = new User(cur.getString("username"),
//						cur.getString("firstname"), cur.getString("lastName"),
//						cur.getString("pass"), Type.valueOf(cur.getString("usertype")));
//				user.setId(cur.getInt("id"));
//				users.add(user);
//			}
//			
//			manager.closeConnection();
//		} catch(Exception e){
//			throw new DatabaseException();
//		}
//		manager.closeConnection();
//		return users;
	}


	@Override
	public void createTable(Table table) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void executeQuery(String query) {
		ConnectionManager manager = ConnectionManager.getInstance();
		Connection conn = manager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.execute();
		} catch (Exception e) {
			throw new DatabaseException();
		}
		manager.closeConnection();
	}

}
