package com.semi.jdgr.notice.dao;

import java.sql.*;
import java.util.*;

import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class NoticeDao {
	//공지사항 상위 5개 조회하기
	public List<NoticeVo> selectNoticeTopList(Connection conn) throws SQLException {
		
		//sql
		String sql="SELECT * FROM (SELECT * FROM NOTICE WHERE DEL_YN ='Y'ORDER BY DECODE(FIXED_YN,'Y',1), NOTICE_NO DESC) WHERE ROWNUM <=5";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
		
		//rs
		while(rs.next()) {
			NoticeVo vo = new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));
			
			noticeVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeVoList;
		
	}
	//게시글 전체 조회
	public List<NoticeVo> selectNoticeList(Connection conn, PageVo pvo) throws SQLException {
		//페이징 처리 까지 완료
		String sql="SELECT * FROM (SELECT ROWNUM RNUM, N.* FROM (SELECT * FROM NOTICE WHERE DEL_YN = 'Y' ORDER BY DECODE(FIXED_YN,'Y',1), NOTICE_NO DESC)N )WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
		pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
		
		//rs
		while(rs.next()) {
			NoticeVo vo = new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));
			
			noticeVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeVoList;
	}
	//게시글 전체 갯수 확인 
	public int selectNoticeCount(Connection conn) throws SQLException {
		
		//sql
		String sql = "SELECT COUNT(*) FROM NOTICE WHERE DEL_YN = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		
		//rs
		if(rs.next()) {
			cnt = rs.getInt(1);	//첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//게시글 검색 조회
	public List<NoticeVo> search(Connection conn, String searchValue, PageVo pvo) throws Exception{
		
		//sql
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, N.* FROM (SELECT * FROM NOTICE WHERE TITLE LIKE '%'||?||'%' OR CONTENT LIKE '%'||?||'%'  AND DEL_YN = 'Y' ORDER BY NOTICE_NO DESC)N )WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setString(2, searchValue);
		pstmt.setInt(3, pvo.getStartRow());
		pstmt.setInt(4, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
		
		//rs
		while(rs.next()) {
			NoticeVo vo = new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));
			
			noticeVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeVoList;
		
	}
	//전체 검색 게시글 갯수 확인
	public int selectSearchNoticeCount(Connection conn, String searchValue) throws SQLException {
		
		//sql
		String sql = "SELECT COUNT(*) FROM NOTICE WHERE TITLE LIKE '%'||?||'%' OR CONTENT LIKE '%'||?||'%'  AND DEL_YN = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setString(2, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		int cnt = 0;
		while(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	
	//공지사항 1개 상세 조회
	public NoticeVo noticeDetail(Connection conn, String boardno) throws SQLException {
		
		//sql
		String sql ="SELECT * FROM NOTICE LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO WHERE NOTICE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,boardno);
		ResultSet rs = pstmt.executeQuery();
		NoticeVo vo = null;
		
		//rs
		if(rs.next()) {
			vo =new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));			
			vo.setAdminName(rs.getString("ADMIN_NAME"));
		}
		
		//result
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
	//관리자 게시글 전체 갯수 확인 
	public int selectAdminNoticeCount(Connection conn) throws SQLException {
		
		//sql
		String sql = "SELECT COUNT(*) FROM NOTICE";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		
		//rs
		if(rs.next()) {
			cnt = rs.getInt(1);	//첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	//관리자 게시글 전체 조회
	public List<NoticeVo> selectAdminNoticeList(Connection conn, PageVo pvo) throws SQLException {
		
		//sql
//		String sql="SELECT * FROM (SELECT ROWNUM RNUM, N.* FROM (SELECT * FROM NOTICE LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO ORDER BY NOTICE_NO DESC)N )WHERE RNUM BETWEEN ? AND ?";
		String sql="SELECT * FROM ( SELECT ROWNUM RNUM, N.* FROM ( SELECT * FROM NOTICE LEFT JOIN( SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO ORDER BY NOTICE_NO DESC)N )WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
		pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
		
		//rs
		while(rs.next()) {
			NoticeVo vo = new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setAdminName(rs.getString("ADMIN_NAME"));
			
			
			noticeVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeVoList;
	}
	//게시글 수정
	public int noticeUpdate(Connection conn, NoticeVo vo) throws SQLException {
		
		//sql
		String sql = "UPDATE NOTICE SET DEL_YN = ?,FIXED_YN =?, TITLE =? , CONTENT =? WHERE NOTICE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getDelYn());
		pstmt.setString(2, vo.getFixedYn());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getContent());
		pstmt.setString(5, vo.getNoticeNo());
		
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//공지사항 게시글 작성
	public int noticeWrite(Connection conn, NoticeVo vo) throws SQLException {
		
		//sql
		String sql ="INSERT INTO NOTICE(NOTICE_NO,ADMIN_NO,TITLE,CONTENT) VALUES (SEQ_NOTICE.NEXTVAL,?,?,?)";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, vo.getAdminNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		
		return result;
	}
	
	
	//관리자 공지사항 검색 조회 갯수
	public int adminNoticeSearchCount(Connection conn, NoticeVo searchVo) throws SQLException {
		
		//sql
		String sql ="SELECT COUNT(*) FROM NOTICE LEFT JOIN( SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO WHERE TITLE LIKE '%'||?||'%' AND CONTENT LIKE '%'||?||'%' AND ADMIN_NAME LIKE '%'||?||'%' AND FIXED_YN LIKE '%'||?||'%' AND DEL_YN LIKE '%'||?||'%' ORDER BY NOTICE_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchVo.getTitle() );
		pstmt.setString(2, searchVo.getContent() );
		pstmt.setString(3, searchVo.getAdminName() );
		pstmt.setString(4, searchVo.getFixedYn() );
		pstmt.setString(5, searchVo.getDelYn() );

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
	
	
	//관리자 공지사항 검색 조회
	public List<NoticeVo> adminNoticeSearch(Connection conn, NoticeVo searchVo, PageVo pvo) throws SQLException {
		//sql
		String sql="SELECT * FROM ( SELECT ROWNUM RNUM, N.* FROM ( SELECT * FROM NOTICE LEFT JOIN( SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO WHERE TITLE LIKE '%'||?||'%' AND CONTENT LIKE '%'||?||'%' AND ADMIN_NAME LIKE '%'||?||'%' AND FIXED_YN LIKE '%'||?||'%' AND DEL_YN LIKE '%'||?||'%' ORDER BY NOTICE_NO DESC)N ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchVo.getTitle() );
		pstmt.setString(2, searchVo.getContent() );
		pstmt.setString(3, searchVo.getAdminName() );
		pstmt.setString(4, searchVo.getFixedYn() );
		pstmt.setString(5, searchVo.getDelYn() );
		pstmt.setInt(6, pvo.getStartRow());
		pstmt.setInt(7, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
		
		//rs
		while(rs.next()) {
			NoticeVo vo = new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setAdminName(rs.getString("ADMIN_NAME"));
			
			
			
			
			
			noticeVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeVoList;
	}
	//조회수 증가
	public int increaseHit(Connection conn, String boardno) throws SQLException {
		//sql
		String sql = "UPDATE NOTICE SET INQUIRY = INQUIRY+1 WHERE NOTICE_NO = ? AND DEL_YN = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardno);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	public NoticeVo adminNoticeDetail(Connection conn, String noticeNo) throws SQLException {
		//sql
		String sql ="SELECT * FROM NOTICE LEFT JOIN(SELECT ADMIN_NO AS AMO,ADMIN_NAME FROM ADMIN) ON AMO = ADMIN_NO WHERE NOTICE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,noticeNo);
		ResultSet rs = pstmt.executeQuery();
		NoticeVo vo = null;
		
		//rs
		if(rs.next()) {
			vo =new NoticeVo();
			
			vo.setNoticeNo(rs.getString("NOTICE_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setInquiry(rs.getString("INQUIRY"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setFixedYn(rs.getString("FIXED_YN"));
			vo.setDelYn(rs.getString("DEL_YN"));			
			vo.setAdminName(rs.getString("ADMIN_NAME"));
		}
		
		//result
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

}
