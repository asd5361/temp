package com.semi.jdgr.blog.vo;

public class GroupVo {
	
	private String groupNo;
	private String groupName;
	
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public String toString() {
		return "GroupVo [groupNo=" + groupNo + ", groupName=" + groupName + "]";
	}
	
	public GroupVo(String groupNo, String groupName) {
		super();
		this.groupNo = groupNo;
		this.groupName = groupName;
	}
	public GroupVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
