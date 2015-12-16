package com.brothers.sharegoods.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.brothers.sharegoods.model.PhotoBean;
public class TestDB {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/sharegoods";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "123456";
	
	public static List<PhotoBean> getTopPhoto(int number) {
		List<PhotoBean> result = null;
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection

		      conn = DriverManager.getConnection(DB_URL, USER, PASS);

		      
		      //STEP 4: Execute a query

		      stmt = conn.createStatement();

		      // Extract records in ascending order by first name.
		      String sql = "SELECT * FROM photo ORDER BY like_photo DESC limit "+number;
		      ResultSet rs = stmt.executeQuery(sql);
		      if(rs != null) {
		    	  result = new ArrayList<PhotoBean>();
		      }
		      while(rs.next()){
		         //Retrieve by column name
					PhotoBean photoBean = new PhotoBean();
					photoBean.setPhotoId(rs.getInt("photo_id"));
					photoBean.setUserId(rs.getInt("user_id"));
					photoBean.setLikePhoto(rs.getInt("like_photo"));
					photoBean.setDescription(rs.getString("description"));
					photoBean.setImageUri(rs.getString("image_uri"));
					result.add(photoBean);
		      }

		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try

		   return result;
		}
	
}
