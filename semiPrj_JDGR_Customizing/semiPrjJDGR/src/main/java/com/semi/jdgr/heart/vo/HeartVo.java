package com.semi.jdgr.heart.vo;

public class HeartVo {

	private String postNo;
	private String memNo;

	public HeartVo() {
	}

	public HeartVo(String postNo, String memNo) {
		this.postNo = postNo;
		this.memNo = memNo;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	@Override
	public String toString() {
		return "HeartVo [postNo=" + postNo + ", memNo=" + memNo + "]";
	}

}
