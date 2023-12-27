package com.semi.jdgr.csboard.dao;

import java.nio.file.spi.FileSystemProvider;
import java.sql.*;
import java.util.*;

import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class CsboardDao {
	//상위 5개 게시글 조회
	public List<CsboardVo> selectCsboardTopList(Connection conn) throws SQLException {
		
		//sql
		String sql = "SELECT * FROM (SELECT * FROM CUSTOMER_CENTER WHERE DEL_YN ='Y'ORDER BY Q_NO DESC) WHERE ROWNUM <=5";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<CsboardVo> csboardVoList = new ArrayList<CsboardVo>();
		//rs
		while(rs.next()) {
			CsboardVo vo = new CsboardVo();
			vo.setqNo(rs.getString("Q_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setqTit(rs.getString("Q_TIT"));
			vo.setqCon((rs.getString("Q_CON")));
			vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
			vo.setAnsewr(rs.getString("ANSEWR"));
			vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
			
			csboardVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return csboardVoList;
	}
	//게시글 전체 조회
	public List<CsboardVo> selectCsboardList(Connection conn, PageVo pvo) throws SQLException {
		
		//sql
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, CSBOARD.* FROM ( SELECT * FROM CUSTOMER_CENTER WHERE DEL_YN = 'Y' ORDER BY Q_NO DESC) CSBOARD) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
		pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<CsboardVo> csboardVoList = new ArrayList<CsboardVo>();
		
		//rs
		while(rs.next()) {
			CsboardVo vo = new CsboardVo();
			vo.setqNo(rs.getString("Q_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setqTit(rs.getString("Q_TIT"));
			vo.setqCon((rs.getString("Q_CON")));
			vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
			vo.setAnsewr(rs.getString("ANSEWR"));
			vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
			
			csboardVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		//close
		
		return csboardVoList;
	}
	//전체 게시글 갯수 확인
	public int selectCsboardCount(Connection conn) throws SQLException {
		//sql
		String sql ="SELECT COUNT(*) FROM CUSTOMER_CENTER WHERE DEL_YN= 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		
		//rs
		while(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	//전체 검색 게시글 갯수 확인
	public int selectSearchCsboardCount(Connection conn, String searchValue) throws SQLException {
		
		String sql ="SELECT COUNT(*) FROM CUSTOMER_CENTER WHERE Q_TIT LIKE '%'||?||'%' OR Q_CON LIKE '%'||?||'%' AND DEL_YN= 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setString(2, searchValue);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		while(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	//검색 게시글 조회
	public List<CsboardVo> search(Connection conn, String searchValue, PageVo pvo) throws SQLException {
		
		//sql
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, N.* FROM (SELECT * FROM CUSTOMER_CENTER WHERE Q_TIT LIKE '%'||?||'%' OR Q_CON LIKE '%'||?||'%'  AND DEL_YN = 'Y' ORDER BY Q_NO DESC)N )WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setString(2, searchValue);
		pstmt.setInt(3, pvo.getStartRow());
		pstmt.setInt(4, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<CsboardVo> csboardVoList = new ArrayList<CsboardVo>();
		
		//rs
		while(rs.next()) {
			CsboardVo vo = new CsboardVo();
			vo.setqNo(rs.getString("Q_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setqTit(rs.getString("Q_TIT"));
			vo.setqCon((rs.getString("Q_CON")));
			vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
			vo.setAnsewr(rs.getString("ANSEWR"));
			vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
			
			csboardVoList.add(vo);
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return csboardVoList;
	}
	//1:1문의 1개 상세 조회
	public CsboardVo csboardDetail(Connection conn, String board) throws SQLException {
		
		//sql
		String sql = "SELECT * FROM CUSTOMER_CENTER WHERE DEL_YN= 'Y' AND Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board);
		ResultSet rs = pstmt.executeQuery();
		CsboardVo vo = null;
		
		//rs
		if(rs.next()) {
			vo = new CsboardVo();
			vo.setqNo(rs.getString("Q_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setqTit(rs.getString("Q_TIT"));
			vo.setqCon((rs.getString("Q_CON")));
			vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
			vo.setAnsewr(rs.getString("ANSEWR"));
			vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
			
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
	//1:1 문의글 작성 
	public int CsboardWrite(Connection conn, CsboardVo vo) throws SQLException {
		
		//sql
		String sql = "INSERT INTO CUSTOMER_CENTER(Q_NO,MEM_NO,Q_TIT,Q_CON,QUESTION_CATEGORY) VALUES (SEQ_CUSTOMER_CENTER.NEXTVAL,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemNo());
		pstmt.setString(2, vo.getqTit());
		pstmt.setString(3, vo.getqCon());
		pstmt.setString(4, vo.getQuestionCategory());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//관리자 게시글 전체 갯수
	public int selectAdminCsboardCount(Connection conn) throws SQLException {
		//sql
		String sql ="SELECT COUNT(*) FROM CUSTOMER_CENTER ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		
		//rs
		while(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	//관리자 게시글 전체 조회
	public List<CsboardVo> selectAdminCsboardList(Connection conn, PageVo pvo) throws SQLException {
	//sql
	String sql = "SELECT * FROM (SELECT ROWNUM RNUM, CSBOARD.* FROM ( SELECT * FROM CUSTOMER_CENTER JOIN(SELECT MEM_NO AS MNO, MEM_NICK FROM MEMBER) ON MNO = MEM_NO LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO ORDER BY Q_NO DESC) CSBOARD) WHERE RNUM BETWEEN ? AND ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, pvo.getStartRow());
	pstmt.setInt(2, pvo.getLastRow());
	ResultSet rs = pstmt.executeQuery();
	List<CsboardVo> csboardVoList = new ArrayList<CsboardVo>();
	
	//rs
	while(rs.next()) {
		CsboardVo vo = new CsboardVo();
		vo.setqNo(rs.getString("Q_NO"));
		vo.setAdminNo(rs.getString("ADMIN_NO"));
		vo.setMemNo(rs.getString("MEM_NO"));
		vo.setqTit(rs.getString("Q_TIT"));
		vo.setqCon((rs.getString("Q_CON")));
		vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
		vo.setAnsewr(rs.getString("ANSEWR"));
		vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
		vo.setUpdateDate(rs.getString("UPDATE_DATE"));
		vo.setDelYn(rs.getString("DEL_YN"));
		vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
		vo.setAdminName(rs.getString("ADMIN_NAME"));
		vo.setMemNick(rs.getString("MEM_NICK")); 
		csboardVoList.add(vo);
	}
	
	//close
	JDBCTemplate.close(rs);
	JDBCTemplate.close(pstmt);
	
	return csboardVoList;
	}
	//관리자 1:1문의 1개 상세 조회
		public CsboardVo adminCsboardDetail(Connection conn, String board) throws SQLException {
			
			//sql
			String sql = "SELECT * FROM CUSTOMER_CENTER JOIN(SELECT MEM_NO AS MNO, MEM_NICK FROM MEMBER) ON MNO = MEM_NO LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO WHERE Q_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board);
			ResultSet rs = pstmt.executeQuery();
			CsboardVo vo = null;
			
			//rs
			if(rs.next()) {
				vo = new CsboardVo();
				vo.setqNo(rs.getString("Q_NO"));
				vo.setAdminNo(rs.getString("ADMIN_NO"));
				vo.setMemNo(rs.getString("MEM_NO"));
				vo.setqTit(rs.getString("Q_TIT"));
				vo.setqCon((rs.getString("Q_CON")));
				vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
				vo.setAnsewr(rs.getString("ANSEWR"));
				vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				vo.setDelYn(rs.getString("DEL_YN"));
				vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
				vo.setAdminName(rs.getString("ADMIN_NAME"));
				vo.setMemNick(rs.getString("MEM_NICK")); 				
			}
			//close
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return vo;
		}
	
	
	//관리자 문의 답변 작성 기능
	public int csboardAnsewr(Connection conn, CsboardVo vo, String dateColumn ) throws SQLException {
		
		//sql
		String sql ="UPDATE CUSTOMER_CENTER SET DEL_YN = ?, ANSEWR = ? ,"+dateColumn+" = SYSDATE ,ADMIN_NO = ? WHERE Q_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getDelYn());
		pstmt.setString(2, vo.getAnsewr());
		pstmt.setString(3, vo.getAdminNo());
		pstmt.setString(4, vo.getqNo());
		int result = pstmt.executeUpdate();
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//관리자 문의 검색 갯수
	public int adminCSboardSearchCount(Connection conn, CsboardVo searchVo) throws SQLException {
		
		//sql
		String ansewr = "";
		if(searchVo.getAnsewr().length() != 0) {
			ansewr = "AND ANSEWR LIKE '%'||?||'%'";
		}
		
		//답변 여부 처리
		String ansewrYn = "";
		if(searchVo.getAnsewrDate().equals("Y")) {
			ansewrYn = "AND ANSEWR_DATE IS NOT NULL";			
		}
		if(searchVo.getAnsewrDate().equals("N")) {
			ansewrYn = "AND ANSEWR_DATE IS NULL";
		}
		
		String sql = " SELECT COUNT(*) FROM CUSTOMER_CENTER JOIN(SELECT MEM_NO AS MNO, MEM_NICK FROM MEMBER) ON MNO = MEM_NO LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO "
				+ "WHERE Q_TIT LIKE '%'||?||'%' AND Q_CON LIKE '%'||?||'%' AND MEM_NICK LIKE '%'||?||'%' AND QUESTION_CATEGORY LIKE '%'||?||'%' " + ansewr + ansewrYn
				+ " ORDER BY Q_NO DESC"; 
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,searchVo.getqTit());
		pstmt.setString(2,searchVo.getqCon());
		pstmt.setString(3,searchVo.getMemNick());
		pstmt.setString(4,searchVo.getQuestionCategory());
		
		if(searchVo.getAnsewr().length() != 0) {	

			pstmt.setString(5, searchVo.getAnsewr());

		}
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		//rs
		while(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//관리자 문의글 검색 조회
	public List<CsboardVo> adminCsboardSearch(Connection conn, CsboardVo searchVo, PageVo pvo) throws SQLException {

		//sql
		String ansewr = "";
		if(searchVo.getAnsewr().length() != 0) {
			ansewr = "AND ANSEWR LIKE '%'||?||'%'";
		}
		
		//답변 여부 처리
		String ansewrYn = "";
		if(searchVo.getAnsewrDate().equals("Y")) {
			ansewrYn = "AND ANSEWR_DATE IS NOT NULL";			
		}
		if(searchVo.getAnsewrDate().equals("N")) {
			ansewrYn = "AND ANSEWR_DATE IS NULL";
		}
		
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, CSBOARD.* FROM ( SELECT * FROM CUSTOMER_CENTER JOIN(SELECT MEM_NO AS MNO, MEM_NICK FROM MEMBER) ON MNO = MEM_NO LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO "
				+ "WHERE Q_TIT LIKE '%'||?||'%' AND Q_CON LIKE '%'||?||'%' AND MEM_NICK LIKE '%'||?||'%' AND QUESTION_CATEGORY LIKE '%'||?||'%' " + ansewr + ansewrYn
				+ " ORDER BY Q_NO DESC) CSBOARD) WHERE RNUM BETWEEN ? AND ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,searchVo.getqTit());
		pstmt.setString(2,searchVo.getqCon());
		pstmt.setString(3,searchVo.getMemNick());
		pstmt.setString(4,searchVo.getQuestionCategory());
		
		if(searchVo.getAnsewr().length() != 0) {
			
			pstmt.setString(5, searchVo.getAnsewr());
			pstmt.setInt(6, pvo.getStartRow());
			pstmt.setInt(7, pvo.getLastRow());	
		}else {
			pstmt.setInt(5, pvo.getStartRow());
			pstmt.setInt(6, pvo.getLastRow());				
		}
		
		ResultSet rs = pstmt.executeQuery();
		List<CsboardVo> csboardVoList = new ArrayList<CsboardVo>();
		
		//rs
		while(rs.next()) {
			CsboardVo vo = new CsboardVo();
			vo.setqNo(rs.getString("Q_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setqTit(rs.getString("Q_TIT"));
			vo.setqCon((rs.getString("Q_CON")));
			vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
			vo.setAnsewr(rs.getString("ANSEWR"));
			vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
			vo.setAdminName(rs.getString("ADMIN_NAME"));
			vo.setMemNick(rs.getString("MEM_NICK")); 
			csboardVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return csboardVoList;
	}

}
