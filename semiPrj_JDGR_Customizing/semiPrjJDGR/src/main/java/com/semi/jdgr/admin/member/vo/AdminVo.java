package com.semi.jdgr.admin.member.vo;

public class AdminVo {
	
	
	private String adminNo;
	private String adminId;
	private String adminPwd;
	private String adminName;
	private String quitYn;
	private String enrollDate;
	public AdminVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminVo(String adminNo, String adminId, String adminPwd, String adminName, String quitYn,
			String enrollDate) {
		super();
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
		this.quitYn = quitYn;
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return "AdaminVo [adminNo=" + adminNo + ", adminId=" + adminId + ", adminPwd=" + adminPwd + ", adminName="
				+ adminName + ", quitYn=" + quitYn + ", enrollDate=" + enrollDate + "]";
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	
	
}
