package com.brothers.sharegoods.model;

public class PhotoCommentBean {
	private int commentId;
	private int photoId;
	private int user1Id;
	private int user2Id;
	private String comments;
	
	public PhotoCommentBean() {
	}
	
	public PhotoCommentBean(PhotoBean photo, UserBean user1, UserBean user2) {
		photoId = photo.getPhotoId();
		user1Id = user1.getUserId();
		user2Id = user2.getUserId();
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public int getPhotoId() {
		return this.photoId;
	}
	
	public int getUser1Id() {
		return this.user1Id;
	}
	
	public int getUser2Id() {
		return this.user2Id;
	}
	
	public String getComments() {
		return this.comments;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	public void setPhotoId(PhotoBean photo) {
		photoId = photo.getPhotoId();
	}
	
	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}
	
	public void setUser1Id(UserBean user1) {
		user1Id = user1.getUserId();
	}
	
	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}
	
	public void setUser2Id(UserBean user2) {
		user2Id = user2.getUserId();
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

}
