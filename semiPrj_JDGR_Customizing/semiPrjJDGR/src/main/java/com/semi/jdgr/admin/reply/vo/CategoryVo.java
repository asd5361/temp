package com.semi.jdgr.admin.reply.vo;

public class CategoryVo {
	
	private String openYn;
	private String delYn;
	
	
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	
	public CategoryVo(String openYn, String delYn) {
		super();
		this.openYn = openYn;
		this.delYn = delYn;
	}
	
	
	@Override
	public String toString() {
		return "CategoryVo [openYn=" + openYn + ", delYn=" + delYn + "]";
	}
	
	
	public CategoryVo() {

	}

	

}