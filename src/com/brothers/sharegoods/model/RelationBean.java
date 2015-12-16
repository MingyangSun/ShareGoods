package com.brothers.sharegoods.model;

public class RelationBean {
	private int user1Id;
	private int user2Id;
	
	public RelationBean() {
	}
	
	public RelationBean(UserBean User1, UserBean User2) {
		user1Id = User1.getUserId();
		user2Id = User2.getUserId();
	}
	
	public RelationBean(int user1Id, int user2Id) {
		this.user1Id = user1Id;
		this.user2Id = user2Id;
	}
	
	public int getUser1Id() {
		return user1Id;
	}
	
	public int getUser2Id() {
		return user2Id;
	}

	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}
	
	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}
}
