package com.semi.jdgr.admin.blame.vo;

public class AdminBlameCategoryVo {

	private String no;
	private String name;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AdminBlameCategoryVo(String no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	@Override
	public String toString() {
		return "AdminBlameCategoryVo [no=" + no + ", name=" + name + "]";
	}
	public AdminBlameCategoryVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
