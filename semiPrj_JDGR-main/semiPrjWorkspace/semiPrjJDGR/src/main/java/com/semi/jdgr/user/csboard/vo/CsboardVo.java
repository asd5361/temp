package com.semi.jdgr.user.csboard.vo;

public class CsboardVo {

	private String qNo;						//문의글 번호"PK"
	private String adminNo;					//답변 관리자 번호
	private String memNo;					//글 작성자 번호
	private String qTit;					//문의글 제목
	private String qCon;					//문의글 내용
	private String qWriteDate;				//작성일
	private String ansewr;					//답변 내용
	private String ansewrDate;				//답변 작성일
	private String updateDate;				//답변 수정일
	private String delYn;					//글 공개 여부(삭제여부)
	private String questionCategory;		//문의글 구분(문의/기능신고)
	private String adminName;				//답변 관리자 이름
	private String questionCategoryName;	//문의글 구분 이름
	
	public CsboardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CsboardVo(String qNo, String adminNo, String memNo, String qTit, String qCon, String qWriteDate,
			String ansewr, String ansewrDate, String updateDate, String delYn, String questionCategory,
			String adminName) {
		super();
		this.qNo = qNo;
		this.adminNo = adminNo;
		this.memNo = memNo;
		this.qTit = qTit;
		this.qCon = qCon;
		this.qWriteDate = qWriteDate;
		this.ansewr = ansewr;
		this.ansewrDate = ansewrDate;
		this.updateDate = updateDate;
		this.delYn = delYn;
		this.questionCategory = questionCategory;
		this.adminName = adminName;
	}
	public String getqNo() {
		return qNo;
	}
	public void setqNo(String qNo) {
		this.qNo = qNo;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getqTit() {
		return qTit;
	}
	public void setqTit(String qTit) {
		this.qTit = qTit;
	}
	public String getqCon() {
		return qCon;
	}
	public void setqCon(String qCon) {
		this.qCon = qCon;
	}
	public String getqWriteDate() {
		return qWriteDate;
	}
	public void setqWriteDate(String qWriteDate) {
		this.qWriteDate = qWriteDate;
	}
	public String getAnsewr() {
		return ansewr;
	}
	public void setAnsewr(String ansewr) {
		this.ansewr = ansewr;
	}
	public String getAnsewrDate() {
		return ansewrDate;
	}
	public void setAnsewrDate(String ansewrDate) {
		this.ansewrDate = ansewrDate;
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
	public String getQuestionCategory() {
		return questionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		if(questionCategory.equals("NM")) {
			this.questionCategoryName = "일반문의";
		}
		if(questionCategory.equals("SM")) {
			this.questionCategoryName = "기능문의";
		}
		if(questionCategory.equals("PM")) {
			this.questionCategoryName = "신고문의";
		}
		this.questionCategory = questionCategory;
	}
	public String getQuestionCategoryName() {
		return questionCategoryName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "CsboardVo [qNo=" + qNo + ", adminNo=" + adminNo + ", memNo=" + memNo + ", qTit=" + qTit + ", qCon="
				+ qCon + ", qWriteDate=" + qWriteDate + ", ansewr=" + ansewr + ", ansewrDate=" + ansewrDate
				+ ", updateDate=" + updateDate + ", delYn=" + delYn + ", questionCategory=" + questionCategory
				+ ", adminName=" + adminName + ", questionCategoryName=" + questionCategoryName + "]";
	}
	
	
}
