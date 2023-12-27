package com.semi.jdgr.admin.blame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.admin.blame.vo.AdminBlameCategoryVo;
import com.semi.jdgr.admin.blame.vo.AdminPostBlameVo;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;
import com.semi.jdgr.page.vo.AdminBlamePageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AdminReplyBlameDao {

	   //게시글 목록 조회
	   public List<AdminReplyBlameVo> selectBlameList(Connection conn, AdminBlamePageVo pvo) throws Exception{
	      
	      //SQL
	      String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , RB.* FROM ( SELECT RB.R_BLA_NO , RB.R_NO, RB.R_BLAMER_NO , RB.R_WRITER_NO , RB.R_BLA_CON , RB.R_BLA_DATE , RB.R_BLA_LIST , RB.R_SANC_YN , RB.R_ANS_DATE , RB.R_BLA_DETAIL , RB.R_DEL_YN FROM REPLY_BLAME RB ORDER BY RB.R_NO DESC ) RB ) WHERE RNUM BETWEEN ? AND ?";
	      //"SELECT * FROM ( SELECT ROWNUM RNUM , RB.* FROM ( SELECT RB.R_BLA_NO , RB.R_BLAMER_NO , RB.R_WRITER_NO , RB.R_BLA_CON , RB.R_BLA_DATE , RB.R_BLA_LIST , RB.R_SANC_YN , RB.R_ANS_DATE , RB.R_BLA_DETAIL , RB.R_DEL_YN , R.CON , M.MEM_NO AS BLAMER_NO , M.MEM_NO AS WRITER_NO FROM REPLY_BLAME RB JOIN REPLY R ON RB.R_NO = R.REPLY_NO JOIN MEMBER M ON RB.R_BLAMER_NO = M.MEM_NO JOIN MEMBER M ON RB.R_WRITER_NO = M.MEM_NO ORDER BY RB.R_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?"
	      //JOIN 제거
	      //RB.R_NO 지움
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, pvo.getStartRow());
	      pstmt.setInt(2, pvo.getLastRow());
	      ResultSet rs = pstmt.executeQuery();
	      
	      
	      //rs
	      List<AdminReplyBlameVo> blameVoList = new ArrayList<AdminReplyBlameVo>();
	      while(rs.next()) {

	    	 String rBlaNo = rs.getString("R_BLA_NO");
	         String rNo = rs.getString("R_NO");
	         String rBlamerNo = rs.getString("R_BLAMER_NO");
	         String rWriterNo = rs.getString("R_WRITER_NO");
	         String rBlaCon = rs.getString("R_BLA_CON");
	         String rBlaDate = rs.getString("R_BLA_DATE");
	         String rBlaList = rs.getString("R_BLA_LIST");
	         String rSancYn = rs.getString("R_SANC_YN");
	         String rAnsDate = rs.getString("R_ANS_DATE");
	         String rBlaDetail = rs.getString("R_BLA_DETAIL");
	         String rDelYn = rs.getString("R_DEL_YN");
	         
	         AdminReplyBlameVo vo = new AdminReplyBlameVo();
	         	vo.setrBlaNo(rBlaNo);
	         	vo.setrNo(rNo);
	         	vo.setrBlamerNo(rBlamerNo);
	         	vo.setrWriterNo(rWriterNo);
	         	vo.setrBlaCon(rBlaCon);
	         	vo.setrBlaDate(rBlaDate);
	         	vo.setrBlaList(rBlaList);
	         	vo.setrSancYn(rSancYn);
	         	vo.setrAnsDate(rAnsDate);
	         	vo.setrBlaDetail(rBlaDetail);
	         	vo.setrDelYn(rDelYn);
	         
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
		      String sql = "SELECT COUNT(*) as cnt FROM REPLY_BLAME";
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
//	   public List<AdminReplyBlameVo> getBlameList(Connection conn, AdminBlamePageVo pvo) throws Exception {
//		   //SQL
//		   String sql = "SELECT * FROM REPLY_BLAME ORDER BY R_BLA_LIST";
//		   PreparedStatement pstmt = conn.prepareStatement(sql);
//		   ResultSet rs = pstmt.executeQuery();
//		   
//		   //rs
//		   List<AdminReplyBlameVo> voList = new ArrayList<AdminReplyBlameVo>();
//		   while(rs.next()) {
//			   String pBlaList = rs.getString("R_BLA_LIST");
//			   
//			   AdminReplyBlameVo vo = new AdminReplyBlameVo();
//			   vo.setrBlaList(pBlaList);
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
	   public AdminReplyBlameVo selectBlameDetail(Connection conn, String no) throws Exception{
	      
	      //SQL
	      String sql = "SELECT * FROM REPLY_BLAME WHERE R_BLA_NO=?";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, no);
	      ResultSet rs = pstmt.executeQuery();
	      
	      //rs
	      AdminReplyBlameVo vo = null;
	      if(rs.next()) {
	    	 String rBlaNo = rs.getString("R_BLA_NO"); 
	         String rNo = rs.getString("R_NO");
	         String rBlamerNo = rs.getString("R_BLAMER_NO");
	         String rWriterNo = rs.getString("R_WRITER_NO");
	         String rBlaCon = rs.getString("R_BLA_CON");
	         String rBlaDate = rs.getString("R_BLA_DATE");
	         String rBlaList = rs.getString("R_BLA_LIST");
	         String rSancYn = rs.getString("R_SANC_YN");
	         String rAnsDate = rs.getString("R_ANS_DATE");
	         String rBlaDetail = rs.getString("R_BLA_DETAIL");
	         String rDelYn = rs.getString("R_DEL_YN");

	        vo = new AdminReplyBlameVo(); 
         	vo.setrBlaNo(rBlaNo);
         	vo.setrNo(rNo);
         	vo.setrBlamerNo(rBlamerNo);
         	vo.setrWriterNo(rWriterNo);
         	vo.setrBlaCon(rBlaCon);
         	vo.setrBlaDate(rBlaDate);
         	vo.setrBlaList(rBlaList);
         	vo.setrSancYn(rSancYn);
         	vo.setrAnsDate(rAnsDate);
         	vo.setrBlaDetail(rBlaDetail);
         	vo.setrDelYn(rDelYn);
	         
	      }
	      //close
	      JDBCTemplate.close(rs);
	      JDBCTemplate.close(pstmt);
	      return vo;
	   }//selectBlameByNo
	   
	   
	   
	   
	   
	   //제재 여부, 답변 여부, 처리여부 null값에서 데이터 입력(게시글 수정)
	   public int updateBlame(Connection conn, AdminReplyBlameVo vo) throws Exception {
		   // SQL
		   String sql = "UPDATE REPLY_BLAME SET R_SANC_YN = ? , R_ANS_DATE = ?, R_DEL_YN =? WHERE R_BLA_NO = ?";
		   PreparedStatement pstmt = conn.prepareStatement(sql);
		   
		   pstmt.setString(1, vo.getrSancYn());
		   pstmt.setString(2, vo.getrAnsDate());
		   pstmt.setString(3, vo.getrDelYn());
		   pstmt.setString(4, vo.getrBlaNo());
		   int result = pstmt.executeUpdate();
		   
		   // close
		   JDBCTemplate.close(pstmt);
		   
		   return result; 
	   	}
	   
	   
		// 신고 목록 검색(신고 번호 / 댓글 번호(신고되지 않은 댓글은 조회되지 않게) / 작성자(신고되지 않은 댓글은 작성자 조회되지 않게)
		// 신고자(신고하지 않은 일반 유저 조회되지 않게) / 제목 / 날짜 설정.. / 리스트 / 상세내용 / 답변일자.. /
		public List<AdminReplyBlameVo> searchBlame(Connection conn, AdminReplyBlameVo arbv , AdminBlamePageVo pvo) throws Exception {
			
			
			// SQL
			String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , RB.* FROM ( SELECT * FROM REPLY_BLAME WHERE  R_BLAMER_NO LIKE '%'||?||'%' AND R_WRITER_NO LIKE '%'||?||'%' AND R_BLA_CON LIKE '%'||?||'%' AND R_BLA_DATE LIKE '%'||?||'%' AND R_BLA_LIST LIKE '%'||?||'%' AND R_DEL_YN LIKE '%'||?||'%' ORDER BY R_BLA_NO DESC)RB ) WHERE RNUM BETWEEN ? AND ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arbv.getrBlamerNo());
			pstmt.setString(2, arbv.getrWriterNo());
			pstmt.setString(3, arbv.getrBlaCon());
			pstmt.setString(4, arbv.getrBlaDate());
			pstmt.setString(5, arbv.getrBlaList());
			pstmt.setString(6, arbv.getrDelYn());
			
			pstmt.setInt(7, pvo.getStartRow());
			pstmt.setInt(8, pvo.getLastRow());
			ResultSet rs = pstmt.executeQuery();
			
			// rs
		      List<AdminReplyBlameVo> blameVoList = new ArrayList<AdminReplyBlameVo>();
		      while(rs.next()) {
		    	 String rBlaNo = rs.getString("R_BLA_NO");
//		         String rNo = rs.getString("R_NO");
		         String rBlamerNo = rs.getString("R_BLAMER_NO");
		         String rWriterNo = rs.getString("R_WRITER_NO");
		         String rBlaCon = rs.getString("R_BLA_CON");
		         String rBlaDate = rs.getString("R_BLA_DATE");
		         String rBlaList = rs.getString("R_BLA_LIST");
		         String rSancYn = rs.getString("R_SANC_YN");
		         String rAnsDate = rs.getString("R_ANS_DATE");
		         String rBlaDetail = rs.getString("R_BLA_DETAIL");
		         String rDelYn = rs.getString("R_DEL_YN");
		         
		         
		         AdminReplyBlameVo vo = new AdminReplyBlameVo();
	         	 vo.setrBlaNo(rBlaNo);
//	         	 vo.setrNo(rNo);
	         	 vo.setrBlamerNo(rBlamerNo);
	         	 vo.setrWriterNo(rWriterNo);
	         	 vo.setrBlaCon(rBlaCon);
	         	 vo.setrBlaDate(rBlaDate);
	         	 vo.setrBlaList(rBlaList);
	         	 vo.setrSancYn(rSancYn);
	         	 vo.setrAnsDate(rAnsDate);
	         	 vo.setrBlaDetail(rBlaDetail);
	         	 vo.setrDelYn(rDelYn);
		         
		         blameVoList.add(vo);
		      }
		
			// close
		    JDBCTemplate.close(rs);
		    JDBCTemplate.close(pstmt);
		      
		    return blameVoList;
		}//search
		
		
		// 신고 댓글 갯수 조회 (검색값에 따라)
		public int selectSearchBlameCount(Connection conn, AdminReplyBlameVo arbv) throws Exception {
			
			// SQL
			String sql = "SELECT COUNT(*) FROM REPLY_BLAME WHERE R_BLAMER_NO LIKE '%'||?||'%' AND R_WRITER_NO LIKE '%'||?||'%' AND R_BLA_CON LIKE '%'||?||'%' AND R_BLA_DATE LIKE '%'||?||'%' AND R_BLA_LIST LIKE '%'||?||'%' AND R_DEL_YN LIKE '%'||?||'%' ORDER BY R_BLA_NO DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arbv.getrBlamerNo());
			pstmt.setString(2, arbv.getrWriterNo());
			pstmt.setString(3, arbv.getrBlaCon());
			pstmt.setString(4, arbv.getrBlaDate());
			pstmt.setString(5, arbv.getrBlaList());
			pstmt.setString(6, arbv.getrDelYn());
			
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
		public int rBlameUpdate(Connection conn, AdminReplyBlameVo vo) throws Exception {
			//sql
			String sql = "UPDATE REPLY_BLAME SET R_SANC_YN = 'Y', R_ANS_DATE =SYSDATE WHERE R_BLA_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getrNo());
			int result = pstmt.executeUpdate();
			
			//close
			JDBCTemplate.close(pstmt);
			
			return result;
		}


		//제재
		public int rSancUpdate(Connection conn, AdminReplyBlameVo vo) throws Exception {
			
			//sql
			String sql = "INSERT INTO REPLY_SANCTIONS ( R_SANC_NO , R_BLA_NO , BAN_DAY , SANC_DATE) VALUES(SEQ_REPLY_SANCTIONS.NEXTVAL, ?,?, SYSDATE)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getrNo());
			pstmt.setString(2, vo.getrSancYn());
			int result = pstmt.executeUpdate();
			
			//close
			JDBCTemplate.close(pstmt);
			
			return result;
		
		}

		
		
	   
}//class
