package com.semi.jdgr.blog.vo;

public class GroupVo {
	
	private String no;
	private String name;
	private String order;
	private String delYn;
	
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
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "GroupVo [no=" + no + ", name=" + name + ", order=" + order + ", delYn=" + delYn + "]";
	}
	public GroupVo(String no, String name, String order, String delYn) {
		super();
		this.no = no;
		this.name = name;
		this.order = order;
		this.delYn = delYn;
	}
	public GroupVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
