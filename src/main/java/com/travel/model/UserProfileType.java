package com.travel.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	MANAGER("MANAGER"),
	BOOKER("BOOKER"),
	ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}

}
