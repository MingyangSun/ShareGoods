package com.brothers.sharegoods.model;

public class FansBean {
	private int userId;
	private int follower;
	private int following;
	
	public FansBean() {
	}
	
	public FansBean(UserBean user) {
		userId = user.getUserId();
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getFollower() {
		return follower;
	}

	public int getFollowing() {
		return following;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setUserId(UserBean user) {
		this.userId = user.getUserId();
	}
	
	public void setFollower(int follower) {
		this.follower = follower;
	}
	
	public void setFollower(boolean condition) {
		if(condition) {
			follower++;
		} else {
			follower--;
		}
	}
	
	public void setFollowing(int following) {
		this.following = following;
	}
	
	public void setFollowing(boolean condition) {
		if(condition) {
			following++;
		} else {
			following--;
		}
	}
}
