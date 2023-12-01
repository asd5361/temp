package com.semi.jdgr.user.member.vo;

public class MemberVo {
	private String memNo;
	private String memName;
	private String memId;
	private String memPwd;
	private String memPwd2;
	private String memNick;
	private String memPhoneNum;
	private String memEmail;
	private String quitYn;
	private String enrollDate;
	private String updateDate;
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVo(String memNo, String memName, String memId, String memPwd, String memPwd2, String memNick,
			String memPhoneNum, String memEmail, String quitYn, String enrollDate, String updateDate) {
		super();
		this.memNo = memNo;
		this.memName = memName;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memPwd2 = memPwd2;
		this.memNick = memNick;
		this.memPhoneNum = memPhoneNum;
		this.memEmail = memEmail;
		this.quitYn = quitYn;
		this.enrollDate = enrollDate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "MemberVo [memNo=" + memNo + ", memName=" + memName + ", memId=" + memId + ", memPwd=" + memPwd
				+ ", memPwd2=" + memPwd2 + ", memNick=" + memNick + ", memPhoneNum=" + memPhoneNum + ", memEmail="
				+ memEmail + ", quitYn=" + quitYn + ", enrollDate=" + enrollDate + ", updateDate=" + updateDate + "]";
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemPwd2() {
		return memPwd2;
	}
	public void setMemPwd2(String memPwd2) {
		this.memPwd2 = memPwd2;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public String getMemPhoneNum() {
		return memPhoneNum;
	}
	public void setMemPhoneNum(String memPhoneNum) {
		this.memPhoneNum = memPhoneNum;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getQuitYn() {
		return quitYn;
	}
	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
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
	}
	
	
}
