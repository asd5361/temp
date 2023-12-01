package com.semi.jdgr.blog.vo;

public class PostVo {
	
	private String postNo;
	private String blogNo;
	private String categoryNo;
	private String categoryName;
	private String groupNo;
	private String groupName;
	private String title;
	private String content;
	
	public PostVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostVo(String postNo, String blogNo, String categoryNo, String categoryName, String groupNo,
			String groupName, String title, String content) {
		super();
		this.postNo = postNo;
		this.blogNo = blogNo;
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.title = title;
		this.content = content;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", blogNo=" + blogNo + ", categoryNo=" + categoryNo + ", categoryName="
				+ categoryName + ", groupNo=" + groupNo + ", groupName=" + groupName + ", title=" + title + ", content="
				+ content + "]";
	}
	
}
