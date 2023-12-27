package com.semi.jdgr.user.follow.vo;

import java.util.ArrayList;
import java.util.List;

public class FollowVo {
	
	private String memNo;
	private String FollowMem;
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getFollowMem() {
		return FollowMem;
	}
	public void setFollowMem(String followMem) {
		FollowMem = followMem;
	}
	public FollowVo(String memNo, String followMem) {
		super();
		this.memNo = memNo;
		FollowMem = followMem;
	}
	public FollowVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FollowVo [memNo=" + memNo + ", FollowMem=" + FollowMem + "]";
	}
	
}
