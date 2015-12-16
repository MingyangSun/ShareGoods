package com.brothers.sharegoods.database;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import com.brothers.sharegoods.model.*;

import javax.naming.NamingException;

public class DatabaseAgent {
	protected ConnectPool connectionPool;
	protected Connection connection;
	protected QueryGoods queryGoods;

	public DatabaseAgent() {
		queryGoods = new QueryGoods();
	}
	
	public ResultSet getQueryResult(String queryId, List<?> parameterList) {
		ResultSet queryResultSet = null;
		
		connectionPool = ConnectPool.getInstance();
		String queryStatement = queryGoods.getQuery(queryId);
		System.out.println("String:" + queryStatement);
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			prepareStatement = connection.prepareStatement(queryStatement);
			int parameterNumber = 0;
			if(parameterList != null) {
				for(int i = 0; i < parameterList.size(); i++) {
					parameterNumber = i+1;
					Object object = parameterList.get(i);
					if(object instanceof String) {
						prepareStatement.setString(parameterNumber, (String)object);
					} else {
						prepareStatement.setObject(parameterNumber, object);
					}
					
				}
				/*for(Object object : parameterList) {
						parameterNumber = parameterList.indexOf(object)+1;
						if(object instanceof String) {
							prepareStatement.setString(parameterNumber, (String)object);
						} else {
							prepareStatement.setObject(parameterNumber, object);
						}
				}*/
				queryResultSet = prepareStatement.executeQuery();
				connection.commit();
				connection.setAutoCommit(true);
			} 
		} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnectionPool();
			}
		return queryResultSet;
	}
	
	public void closeConnectionPool() {
		if(connectionPool != null) {
			try {
				connectionPool.close();
			} catch(NamingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getQueryResult(String queryId, int parameter) {
		ArrayList<Integer> tempIntegerArray = new ArrayList<Integer>();
		tempIntegerArray.add(parameter);
		return this.getQueryResult(queryId, tempIntegerArray);
	}
	
	public ResultSet getQueryResult(String queryId, String parameter) {
		ArrayList<String> tempStringArray = new ArrayList<String>();
		tempStringArray.add(parameter);
		return this.getQueryResult(queryId, tempStringArray);
	}
	
	public ResultSet getQueryResult(String queryId) {
		List<?> nullList = null;
		return this.getQueryResult(queryId, nullList);
	}
	
	public int executeSQL(Map<String, ArrayList<String>> queries) {
		int updatedRows = 0;
		try {
			connectionPool = ConnectPool.getInstance();
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			PreparedStatement prepareStatement = null;
			Iterator<Entry<String, ArrayList<String>>> iter = queries.entrySet().iterator();
			String queryId;
			String queryStatement;
			ArrayList<String> parameterList = null;
			Entry<String, ArrayList<String>> entry = null;
			int parameterNumber = 0;
			while(iter.hasNext()) {
				entry = iter.next();
				queryId = entry.getKey();
				queryStatement = queryGoods.getQuery(queryId);
				prepareStatement = connection.prepareStatement(queryStatement);
				parameterList = entry.getValue();
				for(Object object : parameterList) {
					parameterNumber = parameterList.indexOf(object) + 1;
					prepareStatement.setString(parameterNumber, (String)object);
				}
				prepareStatement.execute();
				updatedRows += prepareStatement.getUpdateCount();
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnectionPool();
		}
		return updatedRows;
	}
	
	public int executeSQL(String queryId, List<?> parameterList) {
		int updatedRows = 0;
		connectionPool = ConnectPool.getInstance();
		String queryStatement = queryGoods.getQuery(queryId);
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			prepareStatement = connection.prepareStatement(queryStatement);
			int parameterNumber = 0;
			if(parameterList != null) {
				for(int i = 0; i < parameterList.size(); i++) {
					parameterNumber = i+1;
					Object object = parameterList.get(i);
					if(object instanceof String) {
						prepareStatement.setString(parameterNumber, (String)object);
					} else {
						prepareStatement.setObject(parameterNumber, object);
					}
				}
				/*for(Object object: parameterList) {
					parameterNumber = parameterList.indexOf(object)+1;
					if(object instanceof String) {
						prepareStatement.setString(parameterNumber, (String)object);
					} else {
						prepareStatement.setObject(parameterNumber, object);
					}
				}*/
			}
			prepareStatement.execute();
			connection.commit();
			connection.setAutoCommit(true);
			updatedRows = prepareStatement.getUpdateCount();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return updatedRows;
	}
	
	public int executeSQL(String queryId,String queryId2, List<?> parameterList) {
        int maxId = 0;
		ResultSet queryResultSet = null;
		connectionPool = ConnectPool.getInstance();
		String queryStatement = queryGoods.getQuery(queryId);
		String queryStatement2 = queryGoods.getQuery(queryId2);
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			prepareStatement = connection.prepareStatement(queryStatement);
			//int parameterNumber = 0;
			if(parameterList != null) {
				for(int i = 0; i < parameterList.size(); i++) {
					System.out.println("TT: " + (String)parameterList.get(i));
					prepareStatement.setString(i+1, (String)parameterList.get(i));
				}
				/*for(Object object: parameterList) {
					parameterNumber = parameterList.indexOf(object)+1;
					prepareStatement.setString(parameterNumber, (String)object);
				}*/
			}
			prepareStatement.execute();
			//Get MaxId
			prepareStatement = connection.prepareStatement(queryStatement2);
			queryResultSet = prepareStatement.executeQuery();
			try{
				if(queryResultSet.next()){
					maxId=queryResultSet.getInt(1);
				}
				queryResultSet.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			connection.commit();
			connection.setAutoCommit(true);
			//updatedRows = prepareStatement.getUpdateCount();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}
	
	public int executeSQL(String queryId) {
		return this.executeSQL(queryId, null);
	}
	
	/**Get User By user_id
	 * @param userId
	 */
	public UserBean getUser(int userId) {
		return this.getUser(userId, "");
	}
	/**Get User By user_name
	 * @param userName
	 */
	public UserBean getUser(String userName) {
		return this.getUser(0, userName);
	}
	/**Get User
	 * When userId is over 0, get user by user_id
	 * When userId equals 0, get user by user_name 
	 * @param userId
	 * @param userName
	 * @return
	 */
	public UserBean getUser(int userId, String userName) {
		UserBean userBean = null;
		String queryId;
		ResultSet rs = null;
		if(userId > 0) {
			queryId ="GET_USER_BY_ID";
			rs = this.getQueryResult(queryId, userId);
		} else if(userId == 0) {
			queryId = "GET_USER_BY_USERNAME";
			rs = this.getQueryResult(queryId, userName);
		} else if(userId < 0) {
			queryId = "GET_USER_BY_EMAIL";
			rs = this.getQueryResult(queryId, userName);
		}
		try {
			if(rs.next()) {
				userBean = new UserBean();
				userBean.setUserId(rs.getInt("user_id"));
				userBean.setEmail(rs.getString("email"));
				userBean.setCity(rs.getString("city"));
				userBean.setCountry(rs.getString("country"));
				userBean.setSex(rs.getString("sex"));
				userBean.setUserName(rs.getString("user_name"));
				userBean.setPassword(rs.getString("password"));
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userBean;
	}
	/**Get User By user_name and password
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserBean getUser(String userName, String password) {
		UserBean userBean = null;
		String queryId;
		ResultSet rs = null;
		if(!userName.equals("") && !password.equals("")) {
			userBean = new UserBean();
			queryId = "GET_USER_BY_EMAIL_AND_PASSWORD";
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(userName);
			parameterList.add(password);
			rs = this.getQueryResult(queryId, parameterList);
			try {
				if(rs.next()) {
					userBean.setUserId(rs.getInt("user_id"));
					userBean.setUserName(rs.getString("user_name"));
					userBean.setEmail(rs.getString("email"));
					userBean.setPassword(rs.getString("password"));
					userBean.setSex(rs.getString("sex"));
					userBean.setCity(rs.getString("city"));
					userBean.setCountry(rs.getString("country"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userBean;
	}
	public List<UserBean> getSearchUser(String search) {
		ArrayList <UserBean> userList = null;
		UserBean userBean;
		ResultSet rs = null;
		rs = this.getQueryResult("SEARCH_USER", "%" + search + "%");
		if(rs != null) {
			userList = new ArrayList<UserBean>();
		try {
			while(rs.next()) {
				userBean = new UserBean();
				userBean.setUserId(rs.getInt("user_id"));
				userBean.setUserName(rs.getString("user_name"));
				userBean.setEmail(rs.getString("email"));
				userBean.setPassword(rs.getString("password"));
				userBean.setSex(rs.getString("sex"));
				userBean.setCity(rs.getString("city"));
				userBean.setCountry(rs.getString("country"));
				userList.add(userBean);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return userList;
	}
	/**Add User
	 * @param userBean
	 * @return user_id
	 */
	public int createUser(UserBean userBean) {
		int resultprocess = 0;
		UserBean tempUserBean = this.getUser(userBean.getUserName());
		UserBean tempUserBean2 = this.getUser(-1, userBean.getEmail());
		if( tempUserBean != null && tempUserBean2 != null) {
			resultprocess = 0;
		} else {
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(userBean.getEmail());
			parameterList.add(userBean.getPassword());
			parameterList.add(userBean.getUserName());
			parameterList.add(userBean.getSex());
			parameterList.add(userBean.getCity());
			parameterList.add(userBean.getCountry());
			
			resultprocess = this.executeSQL("ADD_USER", "GET_NEW_USER", parameterList);
		}
		return resultprocess;
	}
	/**Get Top N Photo
	 * @param number
	 * @return a list of photoes
	 */
	public List<PhotoBean> getTopPhoto(int number) {
		System.out.println("Came Here!");
		ArrayList<PhotoBean> photoList = null;
		PhotoBean photoBean = null;
		ResultSet rs = this.getQueryResult("GET_TOP_PHOTO");
		if(rs != null) {
			photoList = new ArrayList<PhotoBean>();
		try {
			while(rs.next()) {
				System.out.println("There is photo");
				photoBean = new PhotoBean();
				photoBean.setPhotoId(rs.getInt("photo_id"));
				photoBean.setUserId(rs.getInt("user_id"));
				photoBean.setLikePhoto(rs.getInt("like_photo"));
				photoBean.setDescription(rs.getString("description"));
				photoBean.setImageUri(rs.getString("image_uri"));
				photoList.add(photoBean);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return photoList;
	}
	/**Get Photo By photo_id
	 * @param photo_id
	 * @return only one photo
	 */
	public PhotoBean getPhoto(int photo_id) {
		PhotoBean photoBean = null;
		String queryId;
		ResultSet rs = null;
		queryId = "GET_PHOTO_BY_PHOTO_ID";
		rs = this.getQueryResult(queryId, photo_id);
		try {
			if(rs.next()) {
				photoBean = new PhotoBean();
				photoBean.setPhotoId(rs.getInt("photo_id"));
				photoBean.setUserId(rs.getInt("user_id"));
				photoBean.setLikePhoto(rs.getInt("like_photo"));
				photoBean.setDescription(rs.getString("description"));
				photoBean.setImageUri(rs.getString("image_uri"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photoBean;
	}
	/**Get Photo By user_id
	 * @param user_id
	 * @return a list of photo
	 */
	public List<PhotoBean> getPhotoByUser(int user_id) {
		ArrayList <PhotoBean> photoList = null;
		PhotoBean photoBean;
		ResultSet rs = null;
		rs = this.getQueryResult("GET_PHOTO_BY_USER_ID", user_id);
		if(rs != null) {
			photoList = new ArrayList<PhotoBean>();
		try {
			while(rs.next()) {
				photoBean = new PhotoBean();
				photoBean.setPhotoId(rs.getInt("photo_id"));
				photoBean.setUserId(rs.getInt("user_id"));
				photoBean.setLikePhoto(rs.getInt("like_photo"));
				photoBean.setDescription(rs.getString("description"));
				photoBean.setImageUri(rs.getString("image_uri"));
				photoList.add(photoBean);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return photoList;
	}
	/**Get Photo By userBean
	 * @param userBean
	 * @return a list of photo
	 */
	public List<PhotoBean> getPhotoByUser(UserBean userBean) {
		return this.getPhotoByUser(userBean.getUserId());
	}
	/**Search Photo
	 * @param search
	 * @return
	 */
	public List<PhotoBean> getSearchPhoto(String search) {
		ArrayList <PhotoBean> photoList = null;
		PhotoBean photoBean;
		ResultSet rs = null;
		rs = this.getQueryResult("SEARCH_PHOTO", "%" + search + "%");
		if(rs != null) {
			photoList = new ArrayList<PhotoBean>();
		try {
			while(rs.next()) {
				photoBean = new PhotoBean();
				photoBean.setPhotoId(rs.getInt("photo_id"));
				photoBean.setUserId(rs.getInt("user_id"));
				photoBean.setLikePhoto(rs.getInt("like_photo"));
				photoBean.setDescription(rs.getString("description"));
				photoBean.setImageUri(rs.getString("image_uri"));
				photoList.add(photoBean);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return photoList;
	}
	/**Add Photo
	 * @param photoBean
	 * @return photo_id
	 */
	public int createPhoto(PhotoBean photoBean) {
		int resultprocess = 0;
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(photoBean.getUserId() + "");
		parameterList.add(""+photoBean.getLikePhoto());
		parameterList.add(photoBean.getDescription());
		parameterList.add(photoBean.getImageUri());
		resultprocess = this.executeSQL("ADD_PHOTO", "GET_NEW_PHOTO", parameterList);
		return resultprocess;
	}
	
	public int updateLikedPhoto(int photoId, int LikedPhoto) {
		int result = -1;
		ArrayList parameterList = new ArrayList();
		parameterList.add(LikedPhoto);
		parameterList.add(photoId);
		result = this.executeSQL("UPDATE_PHOTO_LIKE", parameterList);
		return result;
	}
	
	/**Get Comment By comment_id
	 * @param comment_id
	 * @return only one comment
	 */
	public PhotoCommentBean getComment(int comment_id) {
		PhotoCommentBean photoCommentBean = null;
		ResultSet rs = null;
		rs = this.getQueryResult("GET_COMMENT_BY_COMMENT_ID", comment_id);
		try {
			if(rs.next()) {
				photoCommentBean = new PhotoCommentBean();
				photoCommentBean.setCommentId(rs.getInt("comment_id"));
				photoCommentBean.setPhotoId(rs.getInt("photo_id"));
				photoCommentBean.setUser1Id(rs.getInt("user1_id"));
				photoCommentBean.setUser2Id(rs.getInt("user2_id"));
				photoCommentBean.setComments(rs.getString("comments"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photoCommentBean;
	}
	/**Get Comment By photo_id
	 * @param photo_id
	 * @return a list of comments
	 */
	public List<PhotoCommentBean> getCommentByPhoto(int photo_id) {
		ArrayList<PhotoCommentBean> commentList = null;
		PhotoCommentBean PhotoCommentBean;
		ResultSet rs = null;
		rs = this.getQueryResult("GET_COMMENT_BY_PHOTO_ID", photo_id);
		if(rs != null) {
			commentList = new ArrayList<PhotoCommentBean>();
		try {
			while(rs.next()) {
				PhotoCommentBean = new PhotoCommentBean();
				PhotoCommentBean.setCommentId(rs.getInt("comment_id"));
				PhotoCommentBean.setPhotoId(rs.getInt("photo_id"));
				PhotoCommentBean.setUser1Id(rs.getInt("user1_id"));
				PhotoCommentBean.setUser2Id(rs.getInt("user2_id"));
				PhotoCommentBean.setComments(rs.getString("comments"));
				commentList.add(PhotoCommentBean);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return commentList;
	}
	/**Get Comment By photoBean
	 * @param photoBean
	 * @return a list of photos
	 */
	public List<PhotoCommentBean> getCommentByPhoto(PhotoBean photoBean) {
		return this.getCommentByPhoto(photoBean.getPhotoId());
	}
	/**Add Comment
	 * @param photoCommentBean
	 * @return comment_id
	 */
	public int createComment(PhotoCommentBean photoCommentBean) {
		int resultprocess = 0;
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(photoCommentBean.getPhotoId() +"");
		parameterList.add(photoCommentBean.getUser1Id() +"");
		parameterList.add(photoCommentBean.getUser2Id() +"");
		parameterList.add(photoCommentBean.getComments());
		resultprocess = this.executeSQL("ADD_COMMENT", parameterList);
		return resultprocess;
	}
	/**Get Tag
	 * @param tagName
	 * @return
	 */
	public TagBean getTag(String tagName) {
		TagBean tagBean = null;
		ResultSet rs = null;
		rs = this.getQueryResult("GET_TAG", tagName);
		try {
			if(rs.next()) {
				tagBean = new TagBean();
				tagBean.setTagName(rs.getString("tag_name"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagBean;
	}
	/**Add Tag
	 * @param tagBean
	 * @return 2, when the tag are already in the table
	 * @return 1, after insert the tag to table
	 */
	public int createTag(TagBean tagBean) {
		int resultprocess = 0;
		TagBean tempTagBean = this.getTag(tagBean.getTagName());
		if(tempTagBean != null) {
			resultprocess = 2;
		} else {
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(tagBean.getTagName());
			resultprocess = this.executeSQL("ADD_TAG", parameterList);
		}
		return resultprocess;
	}
	/**Get Photo Tag By tagName
	 * @param tagName
	 * @return a list of photo tags
	 */
	public List<PhotoTagBean> getPhotoTag(String tagName) {
		ArrayList<PhotoTagBean> photoTagList = null;
		PhotoTagBean photoTagBean;
		ResultSet rs = this.getQueryResult("GET_PHOTO_TAG_BY_TAG_NAME", tagName);
		if(rs != null) {
			photoTagList = new ArrayList<PhotoTagBean>();
		try {
			while(rs.next()) {
				photoTagBean = new PhotoTagBean();
				photoTagBean.setPhotoId(rs.getInt("photo_id"));
				photoTagBean.setTagName(rs.getString("tag_name"));
				photoTagList.add(photoTagBean);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return photoTagList;
	}
	/**Get Photo Tag By TagBean
	 * @param tagBean
	 * @return
	 */
	public List<PhotoTagBean> getPhotoTag(TagBean tagBean) {
		return this.getPhotoTag(tagBean.getTagName());
	}
	/**Get Photo Tag By photo_id
	 * @param photo_id
	 * @return a list of photo tags
	 */
	public List<PhotoTagBean> getPhotoTag(int photoId) {
		ArrayList<PhotoTagBean> photoTagList = null;
		PhotoTagBean photoTagBean;
		ResultSet rs = this.getQueryResult("GET_PHOTO_TAG_BY_PHOTO_ID", photoId);
		if(rs != null) {
			photoTagList = new ArrayList<PhotoTagBean>();
			try {
				while(rs.next()) {
					photoTagBean = new PhotoTagBean();
					photoTagBean.setPhotoId(rs.getInt("photo_id"));
					photoTagBean.setTagName(rs.getString("tag_name"));
					photoTagList.add(photoTagBean);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return photoTagList;
	}
	/**Get Photo Tag By PhotoBean
	 * @param photoBean
	 * @return a list of photo tags
	 */
	public List<PhotoTagBean> getPhotoTag(PhotoBean photoBean) {
		return this.getPhotoTag(photoBean.getPhotoId());
	}
	/**Add Photo Tag
	 * @param photoTagBean
	 * @return affected rows
	 */
	public int createPhotoTag(PhotoTagBean photoTagBean) {
		int resultprocess = 0;
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(photoTagBean.getTagName());
		parameterList.add(photoTagBean.getPhotoId() + "");
		resultprocess = this.executeSQL("ADD_PHOTO_TAG", parameterList);
		return resultprocess;
	}
	/**Delete Liked Photo
	 * @param userId
	 * @param photoId
	 * @return
	 */
	public int deleteLikedPhoto(int userId, int photoId) {
		int result = -1;
		ArrayList<Integer> parameterList = new ArrayList<Integer>();
		parameterList.add(userId);
		parameterList.add(photoId);
		result = this.executeSQL("DELETE_LIKED_PHOTO", parameterList);
		return result;
	}
	/**Get Liked Photo
	 * @param userId
	 * @param photoId
	 * @return
	 */
	public LikedPhotoBean getLikedPhotoByUserAndPhoto(int userId, int photoId) {
		LikedPhotoBean result = null;
		ArrayList<Integer> parameterList = new ArrayList<Integer>();
		System.out.println("Agent :" + userId + "," + photoId);
		parameterList.add(userId);
		parameterList.add(photoId);
		ResultSet rs = this.getQueryResult("GET_LIKED_PHOTO_BY_USER_AND_PHOTO", parameterList);
		if(rs != null) {
			result = new LikedPhotoBean();
			try {
				while(rs.next()) {
					result.setPhotoId(rs.getInt("photo_id"));
					result.setUserId(rs.getInt("user_id"));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**Get Liked Photo By user_id
	 * @param userId
	 * @return a list of liked photos
	 */
	public List<LikedPhotoBean> getLikedPhotoByUser(int userId) {
		ArrayList<LikedPhotoBean> likedPhotoList = null;
		LikedPhotoBean likedPhotoBean;
		ResultSet rs = this.getQueryResult("GET_LIKED_PHOTO_BY_USER_ID", userId);
		if(rs != null) {
			likedPhotoList = new ArrayList<LikedPhotoBean>();
			try {
				while(rs.next()) {
					likedPhotoBean = new LikedPhotoBean();
					likedPhotoBean.setUserId(rs.getInt("user_id"));
					likedPhotoBean.setPhotoId(rs.getInt("photo_id"));
					likedPhotoList.add(likedPhotoBean);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return likedPhotoList;
	}
	/**Get Liked Photo By UserBean
	 * @param userBean
	 * @return a list of liked photos
	 */
	public List<LikedPhotoBean> getLikedPhotoByUser(UserBean userBean) {
		return this.getLikedPhotoByUser(userBean.getUserId());
	}

	/**Get Liked Photo By photo_id
	 * @param photoId
	 * @return a list of liked photos
	 */
	public List<LikedPhotoBean> getLikedPhotoByPhoto(int photoId) {
		ArrayList<LikedPhotoBean> likedPhotoList = null;
		LikedPhotoBean likedPhotoBean;
		ResultSet rs = this.getQueryResult("GET_LIKED_PHOTO_BY_PHOTO_ID", photoId);
		if(rs != null) {
			likedPhotoList = new ArrayList<LikedPhotoBean>();
			try {
				while(rs.next()) {
					likedPhotoBean = new LikedPhotoBean();
					likedPhotoBean.setUserId(rs.getInt("user_id"));
					likedPhotoBean.setPhotoId(rs.getInt("photo_id"));
					likedPhotoList.add(likedPhotoBean);
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return likedPhotoList;
	}
	/**Get Liked Photo By PhotoBean
	 * @param photoBean
	 * @return a list of liked photos
	 */
	public List<LikedPhotoBean> getLikedPhotoByPhoto(PhotoBean photoBean) {
		return this.getLikedPhotoByPhoto(photoBean.getPhotoId());
	}
	/**Add Liked Photo
	 * @param likedPhotoBean
	 * @return affect rows
	 */
	public int createLikedPhoto(LikedPhotoBean likedPhotoBean) {
		int resultprocess = 0;
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(likedPhotoBean.getUserId() + "");
		parameterList.add(likedPhotoBean.getPhotoId() + "");
		resultprocess = this.executeSQL("ADD_LIKED_PHOTO", parameterList);
		return resultprocess;
	}
	/**Get Relation By user1_id
	 * @param userId
	 * @return a list of RelationBean
	 */
	public List<RelationBean> getRelationByUser1(int userId) {
		ArrayList<RelationBean> relationList = null;
		RelationBean relationBean;
		ResultSet rs = this.getQueryResult("GET_RELATION_BY_USER1_ID", userId);
		if(rs != null) {
			relationList = new ArrayList<RelationBean>();
			try {
				while(rs.next()) {
					relationBean = new RelationBean();
					relationBean.setUser1Id(rs.getInt("user1_id"));
					relationBean.setUser2Id(rs.getInt("user2_id"));
					relationList.add(relationBean);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return relationList;
	}
	/**Get Relation By user1Bean
	 * @param userBean
	 * @return a list of relation bean
	 */
	public List<RelationBean> getRelationByUser1(UserBean userBean) {
		return this.getRelationByUser1(userBean.getUserId());
	}
	/**Get Relation By user2_id
	 * @param user2Id
	 * @return a list of relation bean
	 */
	public List<RelationBean> getRelationByUser2(int user2Id) {
		ArrayList<RelationBean> relationList = null;
		RelationBean relationBean;
		ResultSet rs = this.getQueryResult("GET_RELATION_BY_USER2_ID", user2Id);
		if(rs != null) {
			relationList = new ArrayList<RelationBean>();
			try {
				while(rs.next()) {
					relationBean = new RelationBean();
					relationBean.setUser1Id(rs.getInt("user1_id"));
					relationBean.setUser2Id(rs.getInt("user2_id"));
					relationList.add(relationBean);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return relationList;
	}
	/**Get Relation By user2Bean
	 * @param userBean
	 * @return a list of relation bean
	 */
	public List<RelationBean> getRelationByUser2(UserBean userBean) {
		return this.getRelationByUser2(userBean.getUserId());
	}
	public RelationBean getRelationByAllUser(int user1Id, int user2Id) {
		RelationBean relationBean = null;
		ArrayList parameterList = new ArrayList();
		parameterList.add(user1Id);
		parameterList.add(user2Id);
		ResultSet rs = this.getQueryResult("GET_RELATION_BY_USER", parameterList);
		if(rs != null) {
			try {
				while(rs.next()) {
					relationBean = new RelationBean();
					relationBean.setUser1Id(rs.getInt("user1_id"));
					relationBean.setUser2Id(rs.getInt("user2_id"));
					
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return relationBean;
	}
	/**Get Relation By user1_id And user2_id
	 * @param user1Id
	 * @param user2Id
	 * @return one RelationBean
	 */
	public RelationBean getRelationByUser(int user1Id, int user2Id) {
		RelationBean relationBean = null;
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(user1Id + "");
		parameterList.add(user2Id + "");
		ResultSet rs = this.getQueryResult("GET_RELATION_BY_USER", parameterList);
		try {
			if(rs.next()) {
				relationBean = new RelationBean();
				relationBean.setUser1Id(rs.getInt("user1_id"));
				relationBean.setUser2Id(rs.getInt("user2_id"));
			}
			rs.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return relationBean;
	}
	/**Add Relation
	 * @param relationBean
	 * @return affected rows when relation is added; 
	 * 		    2 when the relation exists;
	 */
	public int createRelation(RelationBean relationBean) {
		int resultprocess = 0;
		RelationBean tempRelationBean;
		tempRelationBean = this.getRelationByUser(relationBean.getUser1Id(), relationBean.getUser2Id());
		if(tempRelationBean != null) {
			resultprocess = 2;
		} else {
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(relationBean.getUser1Id() + "");
			parameterList.add(relationBean.getUser2Id() + "");
			resultprocess = this.executeSQL("ADD_RELATION", parameterList);
		}
		return resultprocess;
	}
	/**Delete relation
	 * @param relationBean
	 * @return
	 */
	public int deleteRelation(RelationBean relationBean) {
		int resultprocess = 0;
		System.out.println("Agent:" + relationBean.getUser1Id() + "," + relationBean.getUser2Id());
		ArrayList parameterList = new ArrayList();
		parameterList.add(relationBean.getUser1Id());
		parameterList.add(relationBean.getUser2Id());
		resultprocess = this.executeSQL("DELETE_RELATION", parameterList);
		return resultprocess;
	}
	
	/**Get Fans By user_id
	 * @param userId
	 * @return one FansBean
	 */
	public FansBean getFans(int userId) {
		FansBean fansBean = null;
		ResultSet rs = this.getQueryResult("GET_FANS_BY_USER_ID", userId);
		try {
			if(rs.next()) {
				fansBean = new FansBean();
				fansBean.setUserId(rs.getInt("user_id"));
				fansBean.setFollower(rs.getInt("follower"));
				fansBean.setFollowing(rs.getInt("following"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fansBean;
	}
	/**Add Fans And Update Fans
	 * @param fansBean
	 * @return affected rows
	 */
	public int createFans(FansBean fansBean) {
		ArrayList<String> parameterList = null;
		int resultprocess = 0;
		FansBean tempFansBean;
		tempFansBean = this.getFans(fansBean.getUserId());
		if(tempFansBean != null) {
			parameterList = new ArrayList<String>();
			parameterList.add(fansBean.getFollower() +"");
			parameterList.add(fansBean.getFollowing() + "");
			parameterList.add(fansBean.getUserId() + "");
			resultprocess = this.executeSQL("UPDATE_FANS", parameterList);
		} else {
			parameterList = new ArrayList<String>();
			parameterList.add(fansBean.getUserId() + "");
			parameterList.add(fansBean.getFollower() + "");
			parameterList.add(fansBean.getFollowing() + "");
			resultprocess = this.executeSQL("ADD_FANS", parameterList);
		}
		return resultprocess;
	}
	public int updateFans(FansBean fansBean) {
		int result = -1;
		ArrayList parameterList = new ArrayList();
		parameterList.add(fansBean.getFollower());
		parameterList.add(fansBean.getFollowing());
		parameterList.add(fansBean.getUserId());
		result = this.executeSQL("UPDATE_FANS", parameterList);
		return result;
	}
} 


