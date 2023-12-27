package com.semi.jdgr.user.blame.vo;


public class PostBlameVo {
	
	
	private String pBlaNo;
	private String pNo;
	private String pBlamerNo;
	private String pWriterNo;
	private String pBlaTit;
	private String pTit;
	private String pBlaDate;
	private String pBlaList;
	private String pSancYn;
	private String pAnsDate;
	private String pBlaDetail;
	private String pDelYn;
	private String postNo;
	
	
	public String getpBlaNo() {
		return pBlaNo;
	}
	public void setpBlaNo(String pBlaNo) {
		this.pBlaNo = pBlaNo;
	}
	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	public String getpBlamerNo() {
		return pBlamerNo;
	}
	public void setpBlamerNo(String pBlamerNo) {
		this.pBlamerNo = pBlamerNo;
	}
	public String getpWriterNo() {
		return pWriterNo;
	}
	public void setpWriterNo(String pWriterNo) {
		this.pWriterNo = pWriterNo;
	}
	public String getpBlaTit() {
		return pBlaTit;
	}
	public void setpBlaTit(String pBlaTit) {
		this.pBlaTit = pBlaTit;
	}
	public String getpTit() {
		return pTit;
	}
	public void setpTit(String pTit) {
		this.pTit = pTit;
	}
	public String getpBlaDate() {
		return pBlaDate;
	}
	public void setpBlaDate(String pBlaDate) {
		this.pBlaDate = pBlaDate;
	}
	public String getpBlaList() {
		return pBlaList;
	}
	public void setpBlaList(String pBlaList) {
		this.pBlaList = pBlaList;
	}
	public String getpSancYn() {
		return pSancYn;
	}
	public void setpSancYn(String pSancYn) {
		this.pSancYn = pSancYn;
	}
	public String getpAnsDate() {
		return pAnsDate;
	}
	public void setpAnsDate(String pAnsDate) {
		this.pAnsDate = pAnsDate;
	}
	public String getpBlaDetail() {
		return pBlaDetail;
	}
	public void setpBlaDetail(String pBlaDetail) {
		this.pBlaDetail = pBlaDetail;
	}
	public String getpDelYn() {
		return pDelYn;
	}
	public void setpDelYn(String pDelYn) {
		this.pDelYn = pDelYn;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	@Override
	public String toString() {
		return "PostBlameVo [pBlaNo=" + pBlaNo + ", pNo=" + pNo + ", pBlamerNo=" + pBlamerNo + ", pWriterNo="
				+ pWriterNo + ", pBlaTit=" + pBlaTit + ", pTit=" + pTit + ", pBlaDate=" + pBlaDate + ", pBlaList="
				+ pBlaList + ", pSancYn=" + pSancYn + ", pAnsDate=" + pAnsDate + ", pBlaDetail=" + pBlaDetail
				+ ", pDelYn=" + pDelYn + ", postNo=" + postNo + "]";
	}
	private PostBlameVo(String pBlaNo, String pNo, String pBlamerNo, String pWriterNo, String pBlaTit, String pTit,
			String pBlaDate, String pBlaList, String pSancYn, String pAnsDate, String pBlaDetail, String pDelYn,
			String postNo) {
		super();
		this.pBlaNo = pBlaNo;
		this.pNo = pNo;
		this.pBlamerNo = pBlamerNo;
		this.pWriterNo = pWriterNo;
		this.pBlaTit = pBlaTit;
		this.pTit = pTit;
		this.pBlaDate = pBlaDate;
		this.pBlaList = pBlaList;
		this.pSancYn = pSancYn;
		this.pAnsDate = pAnsDate;
		this.pBlaDetail = pBlaDetail;
		this.pDelYn = pDelYn;
		this.postNo = postNo;
	}
	public PostBlameVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
