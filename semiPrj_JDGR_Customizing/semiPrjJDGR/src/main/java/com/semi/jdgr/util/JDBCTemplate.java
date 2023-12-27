package com.semi.jdgr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

public static Connection getConnection() throws Exception {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##JDGR";
		String pwd = "1234";
  
		//JDBC 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
  
		Connection conn = DriverManager.getConnection(url , id, pwd);
		conn.setAutoCommit(false);
		return conn;
	}
	
	//commit
	public static void commit(Connection conn) {
	   try {
		   if(conn != null && !conn.isClosed())
			   conn.commit();

	   } catch (SQLException e) {}
	}
	
	//rollback
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();

		} catch (SQLException e) {}
   }
   
   //close (conn)
   public static void close(Connection conn) {
	   try {
		   if(conn != null && !conn.isClosed())
			   conn.close();
      } catch (SQLException e) {}
   }
   
   //close (stmt)
   public static void close(Statement stmt) {
	   try {
		   if(stmt != null && !stmt.isClosed())
			   stmt.close();
      } catch (SQLException e) {}
   }
   
   //close (rs)
   public static void close(ResultSet rs) {
	   try {
		   if(rs != null && !rs.isClosed())
			   rs.close();
      } catch (SQLException e) {}
   }
}
