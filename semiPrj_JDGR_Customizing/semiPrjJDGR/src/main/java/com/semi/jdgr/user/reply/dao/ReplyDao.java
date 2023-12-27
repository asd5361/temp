package com.semi.jdgr.user.reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.user.reply.vo.ReplyVo;
import com.semi.jdgr.util.JDBCTemplate;

public class ReplyDao {

	
	//댓글 작성하기
	public int write(Connection conn, ReplyVo vo) throws Exception {
	
		// SQL
		String sql = "INSERT INTO REPLY (REPLY_NO, POST_NO, REPLY_MEM, PARENTS_NO, CON) VALUES ( SEQ_REPLY_NO.NEXTVAL , ? , ? , ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getPostNo());
		pstmt.setString(2, vo.getReplyMem());
		pstmt.setString(3, vo.getParentsNo());
		pstmt.setString(4, vo.getCon());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}//write

	
	//댓글 목록 조회
	public List<ReplyVo> getReplyList(Connection conn, String postNum) throws Exception{
		
		// SQL
		String sql = "SELECT R.* , M.MEM_NICK AS REPLY_MEM_NICK FROM REPLY R JOIN MEMBER M ON M.MEM_NO = R.REPLY_MEM WHERE R.POST_NO = ? AND R.DEL_YN = 'N' ORDER BY COALESCE(PARENTS_NO, REPLY_NO) ASC, REPLY_NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		System.out.println("포스트넘버 : " + postNum);
		pstmt.setString(1, postNum);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<ReplyVo> replyVoList = new ArrayList<ReplyVo>();
		while(rs.next()) {
			String no = rs.getString("REPLY_NO");
			String postNo = rs.getString("POST_NO");
			String replyMem = rs.getString("REPLY_MEM");
			String parentsNo = rs.getString("PARENTS_NO");
			String con = rs.getString("CON");
			String writeDate = rs.getString("WRITE_DATE");
			String replyUserName = rs.getString("REPLY_MEM_NICK");
			
			ReplyVo vo = new ReplyVo();
			vo.setReplyNo(no);
			vo.setPostNo(postNo);
			vo.setReplyMem(replyMem);
			vo.setParentsNo(parentsNo);
			vo.setCon(con);
			vo.setWriteDate(writeDate);
			vo.setReplyMemNick(replyUserName);
			
			replyVoList.add(vo);
		}
		System.out.println("dao voList" + replyVoList);
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return replyVoList;
	}//getReplyList

	// 댓글 수정
	public int replyEdit(Connection conn, ReplyVo replyVo) throws Exception {

		// sql
		String sql = "UPDATE REPLY SET CON = ? , UPDATE_DATE = SYSDATE WHERE REPLY_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, replyVo.getCon());
		pstmt.setString(2, replyVo.getReplyNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 댓글 삭제
	public int replyDelete(Connection conn, ReplyVo replyVo) throws Exception {
		// sql
		String sql = "UPDATE REPLY SET DEL_YN = 'Y' WHERE REPLY_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, replyVo.getReplyNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
}
