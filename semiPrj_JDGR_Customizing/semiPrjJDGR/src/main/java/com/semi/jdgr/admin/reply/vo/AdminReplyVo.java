package com.semi.jdgr.admin.reply.vo;

public class AdminReplyVo {

	private String replyNo;
	private String postNo;
	private String replyMem;
	private String parentsNo;
	private String con;
	private String writeDate;
	private String updateDate;
	private String openYn;
	private String sancYn;
	
	
	public String getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getReplyMem() {
		return replyMem;
	}
	public void setReplyMem(String replyMem) {
		this.replyMem = replyMem;
	}
	public String getParentsNo() {
		return parentsNo;
	}
	public void setParentsNo(String parentsNo) {
		this.parentsNo = parentsNo;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getSancYn() {
		return sancYn;
	}
	public void setSancYn(String sancYn) {
		this.sancYn = sancYn;
	}
	
	
	@Override
	public String toString() {
		return "AdminReplyVo [replyNo=" + replyNo + ", postNo=" + postNo + ", replyMem=" + replyMem + ", parentsNo="
				+ parentsNo + ", con=" + con + ", writeDate=" + writeDate + ", updateDate=" + updateDate + ", openYn="
				+ openYn + ", sancYn=" + sancYn + "]";
	}
	
	
	public AdminReplyVo(String replyNo, String postNo, String replyMem, String parentsNo, String con, String writeDate,
			String updateDate, String openYn, String sancYn) {
		super();
		this.replyNo = replyNo;
		this.postNo = postNo;
		this.replyMem = replyMem;
		this.parentsNo = parentsNo;
		this.con = con;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.openYn = openYn;
		this.sancYn = sancYn;
	}
	
	
	public AdminReplyVo() {
	}

	

	
	
}
