package com.ssafy.enjoytrip.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContentType {
	
	private int contentTypeId;
	private String contentTypeName;
	
	public int getContentTypeId() {
		return contentTypeId;
	}
	
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	
	public String getContentTypeName() {
		return contentTypeName;
	}
	
	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

}
