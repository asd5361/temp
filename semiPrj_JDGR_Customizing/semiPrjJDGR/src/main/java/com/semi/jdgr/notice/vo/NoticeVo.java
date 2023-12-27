package com.semi.jdgr.notice.vo;

public class NoticeVo {
	private String noticeNo;	//공지글번호"PK"
	private String adminNo;		//작성한 관리자 번호
	private String title;		//공지사항 제목
	private String content;		//공지사항 내용
	private String inquiry;		//조회수
	private String enrollDate;	//작성일
	private String updateDate;	//수정일
	private String fixedYn;		//상단 고정 여부
	private String delYn;		//글 공개 여부
	private String adminName;	//작성한 관리자 이름
	private String delName;		//공개 여부 이름
	private String fixedName;	//고정 여부 이름
	
	public NoticeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NoticeVo(String noticeNo, String adminNo, String title, String content, String inquiry, String enrollDate,
			String updateDate, String fixedYn, String delYn, String adminName) {
		super();
		this.noticeNo = noticeNo;
		this.adminNo = adminNo;
		this.title = title;
		this.content = content;
		this.inquiry = inquiry;
		this.enrollDate = enrollDate;
		this.updateDate = updateDate;
		this.fixedYn = fixedYn;
		this.delYn = delYn;
		this.adminName = adminName;
	}

	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		if(title == null && this.noticeNo != null) {
			this.title = "";
		}
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		if(content == null && this.noticeNo != null) {
			this.content = "";
		}
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
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
		if(updateDate == null && this.noticeNo != null) {
			this.updateDate = "";
		}
	}
	public String getFixedYn() {
		return fixedYn;
	}
	public void setFixedYn(String fixedYn) {
		if(fixedYn.equals("Y")) {
			this.fixedName = "고정";
		}
		if(fixedYn.equals("N")) {
			this.fixedName = "미고정";
		}
		this.fixedYn = fixedYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		if(delYn.equals("Y")) {
			this.delName = "공개";
		}
		if(delYn.equals("N")) {
			this.delName = "비공개";
		}
		this.delYn = delYn;
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getDelName() {
		return delName;
	}

	public String getFixedName() {
		return fixedName;
	}

	@Override
	public String toString() {
		return "NoticeVo [noticeNo=" + noticeNo + ", adminNo=" + adminNo + ", title=" + title + ", content=" + content
				+ ", inquiry=" + inquiry + ", enrollDate=" + enrollDate + ", updateDate=" + updateDate + ", fixedYn="
				+ fixedYn + ", delYn=" + delYn + "]";
	}

}
