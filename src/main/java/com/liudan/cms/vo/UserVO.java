package com.liudan.cms.vo;

import com.liudan.cms.domain.User;

public class UserVO extends User{


	private static final long serialVersionUID = -7513685440606460708L;

	private String repassword;

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
