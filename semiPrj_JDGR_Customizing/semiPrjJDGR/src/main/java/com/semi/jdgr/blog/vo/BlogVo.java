package com.semi.jdgr.blog.vo;

public class BlogVo {
	
	private String blogNo;
	private String memNo;
	private String memNick;
	private String memId;
	private String blogTitle;
	private String openYn;
	private String layout;
	private String skin;
	private String clockYn;
	private String mapYn;
	private String rCommentsYn;
	private String followBlogYn;
	private String visitorsCntYn;
	private String blogImg;
	private String rComments;
	private String visitCnt;
	private String blogMain;
	private String repYn;
	private String blogUrl;
	
	public String getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(String blogNo) {
		this.blogNo = blogNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getClockYn() {
		return clockYn;
	}
	public void setClockYn(String clockYn) {
		this.clockYn = clockYn;
	}
	public String getMapYn() {
		return mapYn;
	}
	public void setMapYn(String mapYn) {
		this.mapYn = mapYn;
	}
	public String getrCommentsYn() {
		return rCommentsYn;
	}
	public void setrCommentsYn(String rCommentsYn) {
		this.rCommentsYn = rCommentsYn;
	}
	public String getFollowBlogYn() {
		return followBlogYn;
	}
	public void setFollowBlogYn(String followBlogYn) {
		this.followBlogYn = followBlogYn;
	}
	public String getVisitorsCntYn() {
		return visitorsCntYn;
	}
	public void setVisitorsCntYn(String visitorsCntYn) {
		this.visitorsCntYn = visitorsCntYn;
	}
	public String getBlogImg() {
		return blogImg;
	}
	public void setBlogImg(String blogImg) {
		this.blogImg = blogImg;
	}
	public String getrComments() {
		return rComments;
	}
	public void setrComments(String rComments) {
		this.rComments = rComments;
	}
	public String getVisitCnt() {
		return visitCnt;
	}
	public void setVisitCnt(String visitCnt) {
		this.visitCnt = visitCnt;
	}
	public String getBlogMain() {
		return blogMain;
	}
	public void setBlogMain(String blogMain) {
		this.blogMain = blogMain;
	}
	public String getRepYn() {
		return repYn;
	}
	public void setRepYn(String repYn) {
		this.repYn = repYn;
	}
	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	public BlogVo(String blogNo, String memNo, String memNick, String memId, String blogTitle, String openYn,
			String layout, String skin, String clockYn, String mapYn, String rCommentsYn, String followBlogYn,
			String visitorsCntYn, String blogImg, String rComments, String visitCnt, String blogMain, String repYn,
			String blogUrl) {
		super();
		this.blogNo = blogNo;
		this.memNo = memNo;
		this.memNick = memNick;
		this.memId = memId;
		this.blogTitle = blogTitle;
		this.openYn = openYn;
		this.layout = layout;
		this.skin = skin;
		this.clockYn = clockYn;
		this.mapYn = mapYn;
		this.rCommentsYn = rCommentsYn;
		this.followBlogYn = followBlogYn;
		this.visitorsCntYn = visitorsCntYn;
		this.blogImg = blogImg;
		this.rComments = rComments;
		this.visitCnt = visitCnt;
		this.blogMain = blogMain;
		this.repYn = repYn;
		this.blogUrl = blogUrl;
	}
	public BlogVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlogVo(String blogNo, String memNo, String blogTitle, String openYn, String layout, String skin,
			String clockYn, String mapYn, String rCommentsYn, String followBlogYn, String visitorsCntYn, String blogImg,
			String rComments, String visitCnt, String blogMain, String repYn, String blogUrl) {
		super();
		this.blogNo = blogNo;
		this.memNo = memNo;
		this.blogTitle = blogTitle;
		this.openYn = openYn;
		this.layout = layout;
		this.skin = skin;
		this.clockYn = clockYn;
		this.mapYn = mapYn;
		this.rCommentsYn = rCommentsYn;
		this.followBlogYn = followBlogYn;
		this.visitorsCntYn = visitorsCntYn;
		this.blogImg = blogImg;
		this.rComments = rComments;
		this.visitCnt = visitCnt;
		this.blogMain = blogMain;
		this.repYn = repYn;
		this.blogUrl = blogUrl;
	}

}