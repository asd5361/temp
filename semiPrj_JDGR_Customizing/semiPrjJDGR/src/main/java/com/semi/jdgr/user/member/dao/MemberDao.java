package com.semi.jdgr.user.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.semi.jdgr.user.member.vo.MemberPostSanctionVo;
import com.semi.jdgr.user.member.vo.MemberReplySanctionVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.DateTemplate;
import com.semi.jdgr.util.JDBCTemplate;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		// sql
		String sql = "INSERT INTO MEMBER (MEM_NO,MEM_NAME,MEM_ID,MEM_PWD,MEM_NICK,MEM_PHONE_NUM,MEM_EMAIL) VALUES (SEQ_MEMBER.NEXTVAL, ?,?, ?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemName());
		pstmt.setString(2, vo.getMemId());
		pstmt.setString(3, vo.getMemPwd());
		pstmt.setString(4, vo.getMemNick());
		pstmt.setString(5, vo.getMemPhoneNum());
		pstmt.setString(6, vo.getMemEmail());
		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;

	}

	public boolean checkIdDup(Connection conn, String joinId) throws Exception {
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, joinId);
		ResultSet rs = pstmt.executeQuery();

		boolean result = true;
		if (rs.next()) {
			result = false;
		}

		System.out.println(result);
		return result;
	}

	public boolean checkNickDup(Connection conn, String joinNick) throws Exception {
		String sql = "SELECT * FROM MEMBER WHERE MEM_NICK = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, joinNick);
		ResultSet rs = pstmt.executeQuery();

		boolean result = true;
		if (rs.next()) {
			result = false;
		}

		System.out.println(result);
		return result;
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {

		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PWD = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemId());
		pstmt.setString(2, vo.getMemPwd());
		ResultSet rs = pstmt.executeQuery();

		MemberVo loginMember = null;

		if (rs.next()) {
			String memNo = rs.getString("MEM_NO");
			String memName = rs.getString("MEM_NAME");
			String memId = rs.getString("MEM_ID");
			String memPwd = rs.getString("MEM_PWD");
			String memNick = rs.getString("MEM_NICK");
			String memPhoneNum = rs.getString("MEM_PHONE_NUM");
			String memEmail = rs.getString("MEM_EMAIL");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			String updateDate = rs.getString("UPDATE_DATE");

			loginMember = new MemberVo();
			loginMember.setMemNo(memNo);
			loginMember.setMemName(memName);
			loginMember.setMemId(memId);
			loginMember.setMemPwd(memPwd);
			loginMember.setMemNick(memNick);
			loginMember.setMemPhoneNum(memPhoneNum);
			loginMember.setMemEmail(memEmail);
			loginMember.setQuitYn(quitYn);
			loginMember.setEnrollDate(enrollDate);
			loginMember.setUpdateDate(updateDate);

		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return loginMember;
	}

	public int updateMemberInfo(Connection conn, MemberVo vo) throws Exception {

		String sql = "UPDATE MEMBER SET MEM_NAME = ? , MEM_PWD = ? , MEM_NICK = ? , MEM_PHONE_NUM =? , UPDATE_DATE = SYSDATE WHERE MEM_EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemName());
		pstmt.setString(2, vo.getMemPwd());
		pstmt.setString(3, vo.getMemNick());
		pstmt.setString(4, vo.getMemPhoneNum());
		pstmt.setString(5, vo.getMemEmail());

		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);

		return result;

	}

	public MemberVo printId(Connection conn, String nowEmail) throws Exception {

		String sql = "SELECT * FROM MEMBER WHERE MEM_EMAIL = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nowEmail);
		ResultSet rs = pstmt.executeQuery();

		MemberVo loginMember = null;

		if (rs.next()) {
			String memNo = rs.getString("MEM_NO");
			String memName = rs.getString("MEM_NAME");
			String memId = rs.getString("MEM_ID");
			String memPwd = rs.getString("MEM_PWD");
			String memNick = rs.getString("MEM_NICK");
			String memPhoneNum = rs.getString("MEM_PHONE_NUM");
			String memEmail = rs.getString("MEM_EMAIL");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			String updateDate = rs.getString("UPDATE_DATE");

			loginMember = new MemberVo();
			loginMember.setMemNo(memNo);
			loginMember.setMemName(memName);
			loginMember.setMemId(memId);
			loginMember.setMemPwd(memPwd);
			loginMember.setMemNick(memNick);
			loginMember.setMemPhoneNum(memPhoneNum);
			loginMember.setMemEmail(memEmail);
			loginMember.setQuitYn(quitYn);
			loginMember.setEnrollDate(enrollDate);
			loginMember.setUpdateDate(updateDate);

		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return loginMember;
	}

	public int updateMemberInfo(Connection conn, String newPwd, String nowEmail) throws Exception {
		String sql = "UPDATE MEMBER SET MEM_PWD = ? WHERE MEM_EMAIL = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd);
		pstmt.setString(2, nowEmail);

		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);

		return result;
	}

	public List<MemberReplySanctionVo> findMRSVoList(Connection conn) throws Exception {
		// SQL
		String sql = "SELECT M.MEM_ID, RS.SANC_DATE,RS.BAN_DAY FROM MEMBER M JOIN REPLY R ON R.REPLY_MEM = M.MEM_NO JOIN REPLY_BLAME RB ON R.REPLY_NO = RB.R_NO JOIN REPLY_SANCTIONS RS ON RB.R_BLA_NO = RS.R_BLA_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		// rs
		List<MemberReplySanctionVo> mrsVoList = new ArrayList<MemberReplySanctionVo>();
		// 현재 날짜 구하기
		LocalDate today = DateTemplate.findToday();

		while (rs.next()) {

			String memId = rs.getString("MEM_ID");
			String sancDate = rs.getString("SANC_DATE");
			String banDay = rs.getString("BAN_DAY");
			
			LocalDate date = DateTemplate.changeTypeDbDate(sancDate);
			
			LocalDate endDate = date.plusDays(Integer.parseInt(banDay));
	        
	       
	        
	        long daysDifference = today.until(endDate, ChronoUnit.DAYS);


			MemberReplySanctionVo mrsVo = new MemberReplySanctionVo();
			
			int comparisonResult = endDate.compareTo(today);

			if (comparisonResult >= 0) {
			    mrsVo.setMemId(memId);
				mrsVo.setSancDate(endDate);
				mrsVo.setBanDay(daysDifference);
				
				mrsVoList.add(mrsVo);
			} 
			

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return mrsVoList;

	}

	public List<MemberPostSanctionVo> findMPSVoList(Connection conn) throws Exception {
		// SQL
		String sql = "SELECT M.MEM_ID, PS.SANC_DATE,PS.BAN_DAY FROM MEMBER M JOIN BLOG B ON B.MEM_NO = M.MEM_NO JOIN POST P ON P.BLOG_NO = B.BLOG_NO JOIN POST_BLAME PB ON P.POST_NO = PB.P_NO JOIN POST_SANCTIONS PS ON PB.P_BLA_NO = PS.P_BLA_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		// rs
		List<MemberPostSanctionVo> mpsVoList = new ArrayList<MemberPostSanctionVo>();
		
		
		LocalDate today = DateTemplate.findToday();
		
//		// 현재 날짜 구하기


		while (rs.next()) {

			String memId = rs.getString("MEM_ID");
			String sancDate = rs.getString("SANC_DATE");
			String banDay = rs.getString("BAN_DAY");
			// 디비 날짜 변경

			
			LocalDate date = DateTemplate.changeTypeDbDate(sancDate);
			
			// 제재가 걸린 날짜
			LocalDate endDate = date.plusDays(Integer.parseInt(banDay));
	        
	        long daysDifference = today.until(endDate, ChronoUnit.DAYS);


	        MemberPostSanctionVo mpsVo = new MemberPostSanctionVo();
			
			int comparisonResult = endDate.compareTo(today);

			if (comparisonResult >= 0) {
			    mpsVo.setMemId(memId);
			    mpsVo.setSancDate(endDate);
			    mpsVo.setBanDay(daysDifference);
				
				mpsVoList.add(mpsVo);
			} 
			

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return mpsVoList;

	
	}

}
