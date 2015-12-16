package com.brothers.sharegoods.model;

public class PhotoTagBean {
	private String tagName;
	private int photoId;
	
	public PhotoTagBean() {
	}
	
	public PhotoTagBean(TagBean tag, PhotoBean photo, UserBean user) {
		tagName = tag.getTagName();
		photoId = photo.getPhotoId();
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public int getPhotoId() {
		return photoId;
	}
		
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public void setTagName(TagBean tag) {
		tagName = tag.getTagName();
	}
	
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	public void setPhotoId(PhotoBean photo) {
		photoId = photo.getPhotoId();
	}	
}
