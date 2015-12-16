package com.brothers.sharegoods.model;

public class PhotoBean {
	private int photoId;
	private int userId;
	private int likePhoto;
	private String description;
	private String imageUri;
	
	public PhotoBean() {
	}
	public PhotoBean(UserBean user) {
		userId = user.getUserId();
		likePhoto = 0;
	}
	
	public int getPhotoId() {
		return photoId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getLikePhoto() {
		return likePhoto;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImageUri() {
		return imageUri;
	}
	
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setUserId(UserBean user) {
		userId = user.getUserId();
	}
	
	public void setLikePhoto(int likePhoto) {
		this.likePhoto = likePhoto;
	}
	
	public void setLikePhoto(boolean condition) {
		if(condition) {
			likePhoto++;
		} else {
			likePhoto--;
		}
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
}
