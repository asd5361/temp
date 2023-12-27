package com.semi.jdgr.post.vo;

public class PostVo {

	private String postNo;
	private String blogNo;
	private String categoryNo;
	private String groupNo;
	private String blogTitle;
	private String content;
	private String open;
	private String inquiry;
	private String enrollDate;
	private String modifyDate;
	private String postDelYn;
	private String postImg;
	private String heartCnt;
	private String replyCnt;
	private String memName;
	private String userNick;
	private String userId;
	private String userNo;
	private String postTitle;
	private String categoryName;
	private String groupName;
	private String blogUrl;

	public PostVo() {
	}

	public PostVo(String postNo, String blogNo, String categoryNo, String groupNo, String blogTitle, String content,
			String open, String inquiry, String enrollDate, String modifyDate, String postDelYn, String postImg,
			String heartCnt, String replyCnt, String memName, String userNick, String userId, String userNo,
			String postTitle, String categoryName, String groupName, String blogUrl) {
		this.postNo = postNo;
		this.blogNo = blogNo;
		this.categoryNo = categoryNo;
		this.groupNo = groupNo;
		this.blogTitle = blogTitle;
		this.content = content;
		this.open = open;
		this.inquiry = inquiry;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.postDelYn = postDelYn;
		this.postImg = postImg;
		this.heartCnt = heartCnt;
		this.replyCnt = replyCnt;
		this.memName = memName;
		this.userNick = userNick;
		this.userId = userId;
		this.userNo = userNo;
		this.postTitle = postTitle;
		this.categoryName = categoryName;
		this.groupName = groupName;
		this.blogUrl = blogUrl;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(String blogNo) {
		this.blogNo = blogNo;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getInquiry() {
		return inquiry;
	}

	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPostDelYn() {
		return postDelYn;
	}

	public void setPostDelYn(String postDelYn) {
		this.postDelYn = postDelYn;
	}

	public String getPostImg() {
		return postImg;
	}

	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}

	public String getHeartCnt() {
		return heartCnt;
	}

	public void setHeartCnt(String heartCnt) {
		this.heartCnt = heartCnt;
	}

	public String getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(String replyCnt) {
		this.replyCnt = replyCnt;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", blogNo=" + blogNo + ", categoryNo=" + categoryNo + ", groupNo=" + groupNo
				+ ", blogTitle=" + blogTitle + ", content=" + content + ", open=" + open + ", inquiry=" + inquiry
				+ ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", postDelYn=" + postDelYn
				+ ", postImg=" + postImg + ", heartCnt=" + heartCnt + ", replyCnt=" + replyCnt + ", memName=" + memName
				+ ", userNick=" + userNick + ", userId=" + userId + ", userNo=" + userNo + ", postTitle=" + postTitle
				+ ", categoryName=" + categoryName + ", groupName=" + groupName + ", blogUrl=" + blogUrl + "]";
	}

}
