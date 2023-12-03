package com.semi.jdgr.admin.reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.admin.reply.vo.AdminReplyVo;
import com.semi.jdgr.util.JDBCTemplate;
import com.semi.jdgr.page.vo.AdminReplyPageVo;

public class AdminReplyDao{

   //게시글 목록 조회
   public List<AdminReplyVo> selectReplyList(Connection conn, AdminReplyPageVo pvo) throws Exception{
      
      //SQL
      String sql = "SELECT * FROM    ( SELECT ROWNUM RNUM, T.* FROM ( SELECT R.POST_NO ,R.REPLY_MEM ,R.PARENTS_NO ,R.CON ,R.WRITE_DATE ,R.DEL_YN, M.NICK AS MEM_NICK FROM BOARD R JOIN MEMBER M ON R.REPLY_MEM = M.MEM_NO WHERE B.STATUS = 'O' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, pvo.getStartRow());
      pstmt.setInt(2, pvo.getLastRow());
      ResultSet rs = pstmt.executeQuery();
      
      
      //rs
      List<AdminReplyVo> adminReplyVoList = new ArrayList<AdminReplyVo>();
      while(rs.next()) {
         
    	 String replyNo = rs.getString("REPLY_NO");
         String postNo = rs.getString("POST_NO");
         String replyMem = rs.getString("REPLY_MEM");
         String parentsNo = rs.getString("PARENTS_NO");
         String con = rs.getString("CON");
         String writeDate = rs.getString("WRITE_DATE");
         String delYn = rs.getString("DEL_YN");
         
         AdminReplyVo vo = new AdminReplyVo();
         vo.setReplyNo(replyNo);
         vo.setPostNo(postNo);
         vo.setReplyMem(replyMem);
         vo.setParentsNo(parentsNo);
         vo.setCon(con);
         vo.setWriteDate(writeDate);
         vo.setDelYn(delYn);
         
         adminReplyVoList.add(vo);
         
      }
      
      
      //close
      JDBCTemplate.close(pstmt);
      JDBCTemplate.close(rs);
      
      return adminReplyVoList;
      
   }//selectReplyList


   //댓글 검색
	public List<AdminReplyVo> search(Connection conn, Map<String, String> m , AdminReplyPageVo pvo) throws Exception {
		
		String searchType = m.get("searchType");
		
		// SQL
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.get("searchValue"));
		pstmt.setInt(2, pvo.getStartRow());
		pstmt.setInt(3, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
	      List<AdminReplyVo> adminReplyVoList = new ArrayList<AdminReplyVo>();
	      while(rs.next()) {
	    	  
	     	 String replyNo = rs.getString("REPLY_NO");
	          String postNo = rs.getString("POST_NO");
	          String replyMem = rs.getString("REPLY_MEM");
	          String parentsNo = rs.getString("PARENTS_NO");
	          String con = rs.getString("CON");
	          String writeDate = rs.getString("WRITE_DATE");
	          String delYn = rs.getString("DEL_YN");
	         
	         AdminReplyVo vo = new AdminReplyVo();
	         vo.setReplyNo(replyNo);
	         vo.setPostNo(postNo);
	         vo.setReplyMem(replyMem);
	         vo.setParentsNo(parentsNo);
	         vo.setCon(con);
	         vo.setWriteDate(writeDate);
	         vo.setDelYn(delYn);
	         
	         adminReplyVoList.add(vo);
	      }
	
		// close
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);
	      
	    return adminReplyVoList;
	}//search
   
	
   //댓글 갯수 검색(검색값에 따라)
	public int getReplyCountBySearch(Connection conn, Map<String, String> m) throws Exception {

		// SQL
		String sql = "SELECT COUNT(*) FROM REPLY WHERE STATUS = 'O' AND " + m.get("searchType") + " LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.get("searchValue"));
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	
	//전체 댓글 갯수 조회
	public int selectReplyCount(Connection conn) throws Exception{
		
		//SQL
		String sql = "SELECT COUNT(*) as cnt FROM REPLY WHERE STATUS = 'O'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		int cnt = 0;
		if(rs.next()) {
//	         int cnt = rs.getInt("cnt");
			cnt = rs.getInt(1); //첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
		
	}
	
	//게시글 상세조회(댓글 번호, 포스트 번호, 작성자, 내용, 작성일자, 수정일자, 공개여부(공개, 비공개)

	
	
	
	
	//게시글 번호로 게시글 1개 조회
	public AdminReplyVo selectReplyByNo(Connection conn, String no) throws Exception{

		//SQL
	     String sql = "";
	     PreparedStatement pstmt = conn.prepareStatement(sql);
	     pstmt.setString(1, no);
	     ResultSet rs = pstmt.executeQuery();
	     
	     //rs
	     AdminReplyVo vo = null;
	     if(rs.next()) {
	        String replyNo = rs.getString("REPLY_NO");
	        String postNo = rs.getString("POST_NO");
	        String replyMem = rs.getString("REPLY_MEM");
	        String parentsNo = rs.getString("PARENTS_NO");
	        String con = rs.getString("CON");
	        String writeDate = rs.getString("WRITE_DATE");
	        String delYn = rs.getString("DEL_YN");

	        
	        vo = new AdminReplyVo();
	        vo.setReplyNo(replyNo);
	        vo.setPostNo(postNo);
	        vo.setReplyMem(replyMem);
	        vo.setParentsNo(parentsNo);
	        vo.setCon(con);
	        vo.setWriteDate(writeDate);
	        vo.setDelYn(delYn);
	        
	     }
	     
	     //close
	     JDBCTemplate.close(rs);
	     JDBCTemplate.close(pstmt);
	     return vo;
	  }
	
	
	
   //게시글 삭제
   public int delete(Connection conn, String replyNo, String postNo) throws Exception {
      
      //SQL
      String sql = "UPDATE REPLY SET STATUS = 'X' WHERE NO = ? AND REPLY_MEM = ?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, replyNo);
      pstmt.setString(2, postNo);
      int result = pstmt.executeUpdate();
      
      //close
      JDBCTemplate.close(pstmt);
      
      return result;
      
   }//delete

   
   
   //카테고리 리스트 조회
 	public List<CategoryVo> getCategoryList(Connection conn) throws Exception {
	   
 	   //SQL
	   String sql = "SELECT * FROM CATEGORY ORDER BY NO";
	   PreparedStatement pstmt = conn.prepareStatement(sql);
	   ResultSet rs = pstmt.executeQuery();
	   
	   //rs
	   List<CategoryVo> voList = new ArrayList<CategoryVo>();
	   while(rs.next()) {
		   String replyNo = rs.getString("REPLY_NO");
		   String replyMem = rs.getString("REPLY_MEM");
		   
		   CategoryVo vo = new CategoryVo();
		   vo.setReplyNo(replyNo);
		   vo.setReplyMem(replyMem);
		   voList.add(vo);
	   }
	   //close
	   JDBCTemplate.close(rs);
	   JDBCTemplate.close(pstmt);
	   
	   return voList;
 }


   
   
   




   
   
//   }

//
//   public int selectBoardCount(Connection conn) throws Exception{
//      
//      //SQL
//      String sql = "SELECT COUNT(*) as cnt FROM BOARD WHERE STATUS = 'O'";
//      PreparedStatement pstmt = conn.prepareStatement(sql);
//      
//      ResultSet rs = pstmt.executeQuery();
//      
//      //rs
//      int cnt = 0;
//      if(rs.next()) {
////         int cnt = rs.getInt("cnt");
//         cnt = rs.getInt(1); //첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
//      }
//      
//      //close
//      JDBCTemplate.close(rs);
//      JDBCTemplate.close(pstmt);
//      
//      return cnt;
//         
//   }
   

   



//------------------------↓↓↓↓↓↓↓↓jdbcWorkspace↓↓↓↓↓↓↓↓↓----------------------------------------------------
//// 게시글 검색 (제목)
//	public List<BoardVo> searchBoardByTitle(Connection conn, String searchValue) throws Exception {
//		
//		// SQL
//		String sql = "SELECT B.NO , B.TITLE , M.NICK AS WRITER_NICK , B.HIT , TO_CHAR(B.ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE B.TITLE LIKE '%' || ? || '%' ORDER BY B.NO DESC";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, searchValue);
//		ResultSet rs = pstmt.executeQuery();
//		
//		// rs
//		List<BoardVo> voList = new ArrayList<BoardVo>(); 
//		while(rs.next()) {
//			String no = rs.getString("NO");
//			String title = rs.getString("TITLE");
//			String writerNick = rs.getString("WRITER_NICK");
//			String hit = rs.getString("HIT");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			
//			BoardVo vo = new BoardVo();
//			vo.setNo(no);
//			vo.setTitle(title);
//			vo.setWriterNick(writerNick);
//			vo.setHit(hit);
//			vo.setEnrollDate(enrollDate);
//			
//			voList.add(vo);
//		}
//		
//		// close
//		JDBCTemplate.close(rs);
//		JDBCTemplate.close(pstmt);
//		
//		return voList;
//	}

//// 게시글 검색 (닉네임)
//	public List<BoardVo> searchBoardByNick(Connection conn, String searchValue) throws Exception {
//		// SQL
//		String sql = "SELECT B.NO , B.TITLE , M.NICK AS WRITER_NICK , B.HIT , TO_CHAR(B.ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE M.NICK LIKE '%' || ? || '%' ORDER BY B.NO DESC";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, searchValue);
//		ResultSet rs = pstmt.executeQuery();
//		
//		// rs
//		List<BoardVo> voList = new ArrayList<BoardVo>(); 
//		while(rs.next()) {
//			String no = rs.getString("NO");
//			String title = rs.getString("TITLE");
//			String writerNick = rs.getString("WRITER_NICK");
//			String hit = rs.getString("HIT");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			
//			BoardVo vo = new BoardVo();
//			vo.setNo(no);
//			vo.setTitle(title);
//			vo.setWriterNick(writerNick);
//			vo.setHit(hit);
//			vo.setEnrollDate(enrollDate);
//			
//			voList.add(vo);
//		}
//		
//		// close
//		JDBCTemplate.close(rs);
//		JDBCTemplate.close(pstmt);
//		
//		return voList;
//	}
//
//	// 게시글 검색 (내용)
//	public List<BoardVo> searchBoardByContent(Connection conn, String searchValue) throws Exception {
//		
//		// SQL
//		String sql = "SELECT B.NO , B.TITLE , M.NICK AS WRITER_NICK , B.HIT , TO_CHAR(B.ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE B.CONTENT LIKE '%' || ? || '%' ORDER BY B.NO DESC";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, searchValue);
//		ResultSet rs = pstmt.executeQuery();
//		
//		// rs
//		List<BoardVo> voList = new ArrayList<BoardVo>(); 
//		while(rs.next()) {
//			String no = rs.getString("NO");
//			String title = rs.getString("TITLE");
//			String writerNick = rs.getString("WRITER_NICK");
//			String hit = rs.getString("HIT");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			
//			BoardVo vo = new BoardVo();
//			vo.setNo(no);
//			vo.setTitle(title);
//			vo.setWriterNick(writerNick);
//			vo.setHit(hit);
//			vo.setEnrollDate(enrollDate);
//			
//			voList.add(vo);
//		}
//		
//		// close
//		JDBCTemplate.close(rs);
//		JDBCTemplate.close(pstmt);
//		
//		return voList;
//		
//	}

   
   
   
}//class