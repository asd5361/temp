package com.semi.jdgr.user.member.vo;

import java.time.LocalDate;

public class MemberReplySanctionVo {

	private String memId;
	private LocalDate sancDate;
	private long banDay;
	public MemberReplySanctionVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberReplySanctionVo(String memId, LocalDate sancDate, long banDay) {
		super();
		this.memId = memId;
		this.sancDate = sancDate;
		this.banDay = banDay;
	}
	@Override
	public String toString() {
		return "MemberReplySanctionVo [memId=" + memId + ", sancDate=" + sancDate + ", banDay=" + banDay + "]";
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public LocalDate getSancDate() {
		return sancDate;
	}
	public void setSancDate(LocalDate sancDate) {
		this.sancDate = sancDate;
	}
	public long getBanDay() {
		return banDay;
	}
	public void setBanDay(long banDay) {
		this.banDay = banDay;
	}
	
	
}
