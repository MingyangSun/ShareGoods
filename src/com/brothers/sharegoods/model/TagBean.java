package com.brothers.sharegoods.model;

public class TagBean {
	private String tagName;
	
	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public boolean equals(TagBean tag) {
		if(this.tagName.equals(tag.tagName)) {
			return true;
		} else {
			return false;
		}
	}

}
