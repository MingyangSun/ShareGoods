package com.brothers.sharegoods.model;

public class LikedPhotoBean {
	private int userId;
	private int photoId;
	
	public LikedPhotoBean() {
	}
	public LikedPhotoBean(UserBean user, PhotoBean photo) {
		userId = user.getUserId();
		photoId = photo.getPhotoId();
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getPhotoId() {
		return photoId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setUserId(UserBean user) {
		userId = user.getUserId();
	}
	
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	public void setPhotoId(PhotoBean photo) {
		photoId = photo.getPhotoId();
	}
}
