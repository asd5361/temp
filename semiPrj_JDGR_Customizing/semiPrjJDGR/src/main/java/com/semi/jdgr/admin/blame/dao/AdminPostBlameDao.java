package com.semi.jdgr.admin.blame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.admin.blame.vo.AdminBlameCategoryVo;
import com.semi.jdgr.admin.blame.vo.AdminPostBlameVo;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;
import com.semi.jdgr.page.vo.AdminBlamePageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AdminPostBlameDao {

	   //포스트 신고 목록 조회
	   public List<AdminPostBlameVo> selectBlameList(Connection conn, AdminBlamePageVo pvo) throws Exception{
	      
	      //SQL
	      String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , PB.* FROM ( SELECT PB.P_BLA_NO , PB.P_NO , PB.P_BLAMER_NO , PB.P_WRITER_NO , PB.P_BLA_TIT , PB.P_BLA_DATE , PB.P_BLA_LIST , PB.P_SANC_YN , PB.P_ANS_DATE , PB.P_BLA_DETAIL , PB.P_DEL_YN FROM POST_BLAME PB ORDER BY PB.P_BLA_NO DESC ) PB) WHERE RNUM BETWEEN ? AND ?";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, pvo.getStartRow());
	      pstmt.setInt(2, pvo.getLastRow());
	      ResultSet rs = pstmt.executeQuery();
	      
	      
	      //rs
	      List<AdminPostBlameVo> blameVoList = new ArrayList<AdminPostBlameVo>();
	      while(rs.next()) {

	    	 String pBlaNo = rs.getString("P_BLA_NO");
	         String pNo = rs.getString("P_NO");
	         String pBlamerNo = rs.getString("P_BLAMER_NO");
	         String pWriterNo = rs.getString("P_WRITER_NO");
	         String pBlaTit = rs.getString("P_BLA_TIT");
	         String pBlaDate = rs.getString("P_BLA_DATE");
	         String pBlaList = rs.getString("P_BLA_LIST");
	         String pSancYn = rs.getString("P_SANC_YN");
	         String pAnsDate = rs.getString("P_ANS_DATE");
	         String pBlaDetail = rs.getString("P_BLA_DETAIL");
	         String pDelYn = rs.getString("P_DEL_YN");
	         
	         AdminPostBlameVo vo = new AdminPostBlameVo();
	         	vo.setpBlaNo(pBlaNo);
	         	vo.setpNo(pNo);
	         	vo.setpBlamerNo(pBlamerNo);
	         	vo.setpWriterNo(pWriterNo);
	         	vo.setpBlaTit(pBlaTit);
	         	vo.setpBlaDate(pBlaDate);
	         	vo.setpBlaList(pBlaList);
	         	vo.setpSancYn(pSancYn);
	         	vo.setpAnsDate(pAnsDate);
	         	vo.setpBlaDetail(pBlaDetail);
	         	vo.setpDelYn(pDelYn);
	         
	         blameVoList.add(vo);
	         
	      }
	      
	      
	      //close
	      JDBCTemplate.close(pstmt);
	      JDBCTemplate.close(rs);
	      
	      return blameVoList;
	   }//selectBoardList
	   
	   
	   //전체 신고 갯수 조회
	   public int selectBlameCount(Connection conn) throws Exception{
		      
		      //SQL
		      String sql = "SELECT COUNT(*) as cnt FROM POST_BLAME";
		      PreparedStatement pstmt = conn.prepareStatement(sql);
		      
		      ResultSet rs = pstmt.executeQuery();
		      
		      //rs
		      int cnt = 0;
		      if(rs.next()) {
//		         int cnt = rs.getInt("cnt");
		         cnt = rs.getInt(1); //첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		      }
		      
		      //close
		      JDBCTemplate.close(rs);
		      JDBCTemplate.close(pstmt);
		      
		      return cnt;
		         
		   }
	   
	   
//	   //신고 항목 카테고리 조회(신고항목별, 제재 여부, 답변 여부, 처리 여부)
//	   public List<AdminPostBlameVo> getBlameList(Connection conn) throws Exception {
//		   //SQL
//		   String sql = "SELECT * FROM POST_BLAME ORDER BY P_BLA_LIST";
//		   PreparedStatement pstmt = conn.prepareStatement(sql);
//		   ResultSet rs = pstmt.executeQuery();
//		   
//		   //rs
//		   List<AdminPostBlameVo> voList = new ArrayList<AdminPostBlameVo>();
//		   while(rs.next()) {
//			   String pBlaList = rs.getString("P_BLA_LIST");
//			   
//			   AdminPostBlameVo vo = new AdminPostBlameVo();
//			   vo.setpBlaList(pBlaList);
//			   voList.add(vo);
//		   }
//		   
//		   //close
//		   JDBCTemplate.close(rs);
//		   JDBCTemplate.close(pstmt);
//		   
//		   return voList;
//	   }//getCategoryList
	   

	   //신고 목록 상세 조회(번호로)
	   public AdminPostBlameVo selectBlameDetail(Connection conn, String no) throws Exception{
	      
	      //SQL
	      String sql = "SELECT * FROM POST_BLAME WHERE P_BLA_NO=?";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, no);
	      ResultSet rs = pstmt.executeQuery();
	      
	      //rs
	      AdminPostBlameVo vo = null;
	      if(rs.next()) {
	    	 String pBlaNo = rs.getString("P_BLA_NO");
	         String pNo = rs.getString("P_NO");
	         String pBlamerNo = rs.getString("P_BLAMER_NO");
	         String pWriterNo = rs.getString("P_WRITER_NO");
	         String pBlaTit = rs.getString("P_BLA_TIT");
	         String pBlaDate = rs.getString("P_BLA_DATE");
	         String pBlaList = rs.getString("P_BLA_LIST");
	         String pSancYn = rs.getString("P_SANC_YN");
	         String pAnsDate = rs.getString("P_ANS_DATE");
	         String pBlaDetail = rs.getString("P_BLA_DETAIL");
	         String pDelYn = rs.getString("P_DEL_YN");

	         
	         vo = new AdminPostBlameVo();
         	 vo.setpBlaNo(pBlaNo);
         	 vo.setpNo(pNo);
         	 vo.setpBlamerNo(pBlamerNo);
         	 vo.setpWriterNo(pWriterNo);
         	 vo.setpBlaTit(pBlaTit);
         	 vo.setpBlaDate(pBlaDate);
         	 vo.setpBlaList(pBlaList);
         	 vo.setpSancYn(pSancYn);
         	 vo.setpAnsDate(pAnsDate);
         	 vo.setpBlaDetail(pBlaDetail);
         	 vo.setpDelYn(pDelYn);
	         
	      }
	      //close
	      JDBCTemplate.close(rs);
	      JDBCTemplate.close(pstmt);
	      return vo;
	   }//selectBlameByNo
	   
	   
	   
	   
	   //제재 여부, 답변 여부, 처리여부 null값에서 데이터 입력(게시글 수정)
	   public int updateBlame(Connection conn, AdminPostBlameVo vo) throws Exception {
		   // SQL
		   String sql = "UPDATE POST_BLAME SET P_SANC_YN = ?, P_ANS_DATE = ?, P_DEL_YN =?  WHERE P_BLA_NO = ?";
		   PreparedStatement pstmt = conn.prepareStatement(sql);
		   
		   pstmt.setString(1, vo.getpSancYn());
		   pstmt.setString(2, vo.getpAnsDate());
		   pstmt.setString(3, vo.getpDelYn());
		   pstmt.setString(4, vo.getpBlaNo());
		   int result = pstmt.executeUpdate();
		   
		   // close
		   JDBCTemplate.close(pstmt);
		   
		   return result; 
	   	}
	   
	   
		// 신고 목록 검색(신고 번호 / 포스트 번호(신고되지 않은 포스트는 조회되지 않게) / 작성자(신고되지 않은 포스트 작성자 조회되지 않게)
		// 신고자(신고하지 않은 일반 유저 조회되지 않게) / 제목 / 날짜 설정.. / 리스트 / 상세내용 / 답변일자.. / 
		public List<AdminPostBlameVo> searchBlame(Connection conn, AdminPostBlameVo apbv, AdminBlamePageVo pvo) throws Exception {
			
			
			// SQL
			String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, PB.* FROM ( SELECT * FROM POST_BLAME WHERE P_BLAMER_NO LIKE  '%'||?||'%' AND P_WRITER_NO LIKE '%'||?||'%' AND P_BLA_TIT LIKE '%'||?||'%' AND P_BLA_DATE LIKE '%'||?||'%' AND P_BLA_LIST LIKE '%'||?||'%' AND P_DEL_YN LIKE '%'||?||'%' ORDER BY P_BLA_NO DESC)PB ) WHERE RNUM BETWEEN ? AND ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, apbv.getpBlamerNo());
			pstmt.setString(2, apbv.getpWriterNo());
			pstmt.setString(3, apbv.getpBlaTit());
			pstmt.setString(4, apbv.getpBlaDate());
			pstmt.setString(5, apbv.getpBlaList());
			pstmt.setString(6, apbv.getpDelYn());
			
			pstmt.setInt(7, pvo.getStartRow());
			pstmt.setInt(8, pvo.getLastRow());
			ResultSet rs = pstmt.executeQuery();
			
			// rs
		      List<AdminPostBlameVo> blameVoList = new ArrayList<AdminPostBlameVo>();
		      while(rs.next()) {
		    	 String pBlaNo = rs.getString("P_BLA_NO");
//		         String pNo = rs.getString("P_NO");
		         String pBlamerNo = rs.getString("P_BLAMER_NO");
		         String pWriterNo = rs.getString("P_WRITER_NO");
		         String pBlaTit = rs.getString("P_BLA_TIT");
		         String pBlaDate = rs.getString("P_BLA_DATE");
		         String pBlaList = rs.getString("P_BLA_LIST");
		         String pSancYn = rs.getString("P_SANC_YN");
		         String pAnsDate = rs.getString("P_ANS_DATE");
		         String pBlaDetail = rs.getString("P_BLA_DETAIL");
		         String pDelYn = rs.getString("P_DEL_YN");
		         
		         AdminPostBlameVo vo = new AdminPostBlameVo();
	         	 vo.setpBlaNo(pBlaNo);
//	         	 vo.setpNo(pNo);
	         	 vo.setpBlamerNo(pBlamerNo);
	         	 vo.setpWriterNo(pWriterNo);
	         	 vo.setpBlaTit(pBlaTit);
	         	 vo.setpBlaDate(pBlaDate);
	         	 vo.setpBlaList(pBlaList);
	         	 vo.setpSancYn(pSancYn);
	         	 vo.setpAnsDate(pAnsDate);
	         	 vo.setpBlaDetail(pBlaDetail);
	         	 vo.setpDelYn(pDelYn);
		         
		         blameVoList.add(vo);
		      }
		
			// close
		    JDBCTemplate.close(rs);
		    JDBCTemplate.close(pstmt);
		      
		    return blameVoList;
		}//search
		
		
		// 신고 댓글 갯수 조회 (검색값에 따라)
		public int selectSearchBlameCount(Connection conn, AdminPostBlameVo apbv) throws Exception {
			
			// SQL
			String sql = "SELECT COUNT(*) FROM POST_BLAME WHERE P_BLAMER_NO LIKE '%'||?||'%' AND P_WRITER_NO LIKE '%'||?||'%' AND P_BLA_TIT LIKE '%'||?||'%' AND P_BLA_DATE LIKE  '%'||?||'%' AND P_BLA_LIST LIKE '%'||?||'%' AND P_DEL_YN LIKE '%'||?||'%' ORDER BY P_BLA_NO DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, apbv.getpBlamerNo());
			pstmt.setString(2, apbv.getpWriterNo());
			pstmt.setString(3, apbv.getpBlaTit());
			pstmt.setString(4, apbv.getpBlaDate());
			pstmt.setString(5, apbv.getpBlaList());
			pstmt.setString(6, apbv.getpDelYn());
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
		
		
		
		
		//제재 처리 저장
		public int pBlameUpdate(Connection conn, AdminPostBlameVo vo) throws Exception {
			//sql
			String sql = "UPDATE POST_BLAME SET P_SANC_YN = 'Y', P_ANS_DATE =SYSDATE WHERE P_BLA_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getpNo());
			int result = pstmt.executeUpdate();
			
			//close
			JDBCTemplate.close(pstmt);
			
			return result;
		}


		//제재
		public int pSancUpdate(Connection conn, AdminPostBlameVo vo) throws Exception {
			
			//sql
			String sql = "INSERT INTO POST_SANCTIONS ( P_SANC_NO , P_BLA_NO , BAN_DAY , SANC_DATE) VALUES(SEQ_REPLY_SANCTIONS.NEXTVAL, ?,?, SYSDATE)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getpNo());
			pstmt.setString(2, vo.getpSancYn());
			int result = pstmt.executeUpdate();
			
			//close
			JDBCTemplate.close(pstmt);
			
			return result;
		
		}

	   
}//class
