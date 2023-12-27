package com.semi.jdgr.user.reply.vo;

public class ReplyVo {

	private String replyNo;
	private String postNo;
	private String replyMem;
	private String parentsNo;
	private String con;
	private String writeDate;
	private String updateDate;
	private String delYn;
	private String replyMemNick;
	
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
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getReplyMemNick() {
		return replyMemNick;
	}
	public void setReplyMemNick(String replyMemNick) {
		this.replyMemNick = replyMemNick;
	}
	@Override
	public String toString() {
		return "ReplyVo [replyNo=" + replyNo + ", postNo=" + postNo + ", replyMem=" + replyMem + ", parentsNo="
				+ parentsNo + ", con=" + con + ", writeDate=" + writeDate + ", updateDate=" + updateDate + ", delYn="
				+ delYn + ", replyMemNick=" + replyMemNick + "]";
	}
	public ReplyVo(String replyNo, String postNo, String replyMem, String parentsNo, String con, String writeDate,
			String updateDate, String delYn, String replyMemNick) {
		super();
		this.replyNo = replyNo;
		this.postNo = postNo;
		this.replyMem = replyMem;
		this.parentsNo = parentsNo;
		this.con = con;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.delYn = delYn;
		this.replyMemNick = replyMemNick;
	}
	public ReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
