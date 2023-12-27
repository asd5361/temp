package com.semi.jdgr.user.blame.vo;

import java.util.Arrays;

public class ReplyBlameVo {
	
	
	private String rBlaNo;
	private String rNo;
	private String rBlamerNo;
	private String rWriterNo;
	private String rBlaCon;
	private String rBlaDate;
	private String rBlaList;
	private String rSancYn;
	private String rAnsDate;
	private String rBlaDetail;
	private String rDelYn;
	
	private String replyNo;
	private String postNo;
	private String replyMem;
	private String parentsNo;
	private String con;
	
	
	public String getrBlaNo() {
		return rBlaNo;
	}
	public void setrBlaNo(String rBlaNo) {
		this.rBlaNo = rBlaNo;
	}
	public String getrNo() {
		return rNo;
	}
	public void setrNo(String rNo) {
		this.rNo = rNo;
	}
	public String getrBlamerNo() {
		return rBlamerNo;
	}
	public void setrBlamerNo(String rBlamerNo) {
		this.rBlamerNo = rBlamerNo;
	}
	public String getrWriterNo() {
		return rWriterNo;
	}
	public void setrWriterNo(String rWriterNo) {
		this.rWriterNo = rWriterNo;
	}
	public String getrBlaCon() {
		return rBlaCon;
	}
	public void setrBlaCon(String rBlaCon) {
		this.rBlaCon = rBlaCon;
	}
	public String getrBlaDate() {
		return rBlaDate;
	}
	public void setrBlaDate(String rBlaDate) {
		this.rBlaDate = rBlaDate;
	}
	public String getrBlaList() {
		return rBlaList;
	}
	public void setrBlaList(String rBlaList) {
		this.rBlaList = rBlaList;
	}
	public String getrSancYn() {
		return rSancYn;
	}
	public void setrSancYn(String rSancYn) {
		this.rSancYn = rSancYn;
	}
	public String getrAnsDate() {
		return rAnsDate;
	}
	public void setrAnsDate(String rAnsDate) {
		this.rAnsDate = rAnsDate;
	}
	public String getrBlaDetail() {
		return rBlaDetail;
	}
	public void setrBlaDetail(String rBlaDetail) {
		this.rBlaDetail = rBlaDetail;
	}
	public String getrDelYn() {
		return rDelYn;
	}
	public void setrDelYn(String rDelYn) {
		this.rDelYn = rDelYn;
	}
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
	public ReplyBlameVo(String rBlaNo, String rNo, String rBlamerNo, String rWriterNo, String rBlaCon, String rBlaDate,
			String rBlaList, String rSancYn, String rAnsDate, String rBlaDetail, String rDelYn, String replyNo,
			String postNo, String replyMem, String parentsNo, String con) {
		super();
		this.rBlaNo = rBlaNo;
		this.rNo = rNo;
		this.rBlamerNo = rBlamerNo;
		this.rWriterNo = rWriterNo;
		this.rBlaCon = rBlaCon;
		this.rBlaDate = rBlaDate;
		this.rBlaList = rBlaList;
		this.rSancYn = rSancYn;
		this.rAnsDate = rAnsDate;
		this.rBlaDetail = rBlaDetail;
		this.rDelYn = rDelYn;
		this.replyNo = replyNo;
		this.postNo = postNo;
		this.replyMem = replyMem;
		this.parentsNo = parentsNo;
		this.con = con;
	}
	@Override
	public String toString() {
		return "ReplyBlameVo [rBlaNo=" + rBlaNo + ", rNo=" + rNo + ", rBlamerNo=" + rBlamerNo + ", rWriterNo="
				+ rWriterNo + ", rBlaCon=" + rBlaCon + ", rBlaDate=" + rBlaDate + ", rBlaList=" + rBlaList
				+ ", rSancYn=" + rSancYn + ", rAnsDate=" + rAnsDate + ", rBlaDetail=" + rBlaDetail + ", rDelYn="
				+ rDelYn + ", replyNo=" + replyNo + ", postNo=" + postNo + ", replyMem=" + replyMem + ", parentsNo="
				+ parentsNo + ", con=" + con + "]";
	}
	public ReplyBlameVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
		
}
