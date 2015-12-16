package com.brothers.sharegoods.database;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectPool {

	private static ConnectPool connPool = null;
	private static Connection conn = null;
	private Context initContext = null;
	private DataSource dataSource;	
	private String dbSource = "java:comp/env/jdbc/sharegoods";
			
	private ConnectPool() {
		try {
			initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup(dbSource);
		} catch (NamingException e) {
			System.err.println("initial context error!\n" + e);
		}
	}
	
	public static ConnectPool getInstance() {
		if(connPool == null) {
			connPool = new ConnectPool();
		}
		return connPool;
	}
	
	public Connection connect() throws SQLException, ClassNotFoundException {
		if( conn == null || conn.isClosed()) {
			getConnection();
		}
		return conn;
	}
	
	public void getConnection() {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void close() throws NamingException {
		if(initContext != null)
			initContext.close();
	}
}
