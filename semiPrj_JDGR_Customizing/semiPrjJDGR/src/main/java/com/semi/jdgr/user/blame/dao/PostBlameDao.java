package com.semi.jdgr.user.blame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.blame.vo.PostBlameVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostBlameDao {

	
//	public PostBlameVo getPostInfo(Connection conn, PostBlameVo vo) throws Exception {
//		
//		//gpt
////		String sql = "SELECT P_BLA_LIST, P_BLA_DETAIL FROM POST_BLAME WHERE P_NO = ?";
//		String sql = "SELECT P.POST_NO , P.TITLE FROM POST P JOIN POST_BLAME PB ON P.POST_NO = PB.P_NO ORDER BY PB.P_NO DESC WHERE P.POST_NO = ?";
//	    PreparedStatement pstmt = conn.prepareStatement(sql);
////	    pstmt.setString(1, vo.getpNo());
//
//	    ResultSet rs = pstmt.executeQuery();
//
//	    // rs
//	    PostBlameVo getPostInfo = null;
//	    while (rs.next()) {
//	        String pBlaList = rs.getString("P_BLA_LIST");
//	        String pBlaDetail = rs.getString("P_BLA_DETAIL");
//
//	        getPostInfo = new PostBlameVo();
//	        getPostInfo.setpBlaList(pBlaList);
//	        getPostInfo.setpBlaDetail(pBlaDetail);
//	        
//	        getPostInfo.ad
//	    }
//	    
//	    
//	    //close
//	    JDBCTemplate.close(pstmt);
//	    JDBCTemplate.close(rs);
//	    
//	    return getPostInfo;
//	    
//
//
//	}//getPostInfo
	
	
	
	public PostBlameVo getPostInfo(Connection conn, String pNo) throws Exception {
		
		//gpt
		String sql = "SELECT P.POST_NO , P.TITLE , M.MEM_NO FROM POST P JOIN POST_BLAME PB ON P.POST_NO = PB.P_NO JOIN MEMBER M  ON PB.P_WRITER_NO = M.MEM_NO WHERE P.POST_NO = ? ORDER BY PB.P_NO DESC";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, pNo);

	    ResultSet rs = pstmt.executeQuery();

	    // rs
	    PostBlameVo getPostInfo = null;
	    if (rs.next()) {
	    	String postNo = rs.getString("POST_NO");
	    	String pTit = rs.getString("TITLE");
	    	String pWriterNo = rs.getString("MEM_NO");
//	    	String pBlamerNick = rs.getString("MEM_NICK");
	    	

	    	getPostInfo = new PostBlameVo();
	        getPostInfo.setpNo(postNo);
	        getPostInfo.setpTit(pTit);
	        getPostInfo.setpWriterNo(pWriterNo);
//	        getPostInfo.setpBlamerNick(pBlamerNick);
	        

	    }
	    
	    
	    //close
	    JDBCTemplate.close(pstmt);
	    JDBCTemplate.close(rs);
	    
	    return getPostInfo;
	    


	}//getPostInfo
	
	
//	public int blame(Connection conn, PostBlameVo vo) throws Exception {
//
//		// sql
//		String sql = "INSERT INTO POST_BLAME (P_BLA_NO, P_NO, P_BLAMER_NO, P_WRITER_NO, P_BLA_TIT, P_BLA_LIST, P_BLA_DETAIL) VALUES(SEQ_POST_BLAME.NEXTVAL, ?, ?, ?, ?, ?, ?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, vo.getpNo());
//		pstmt.setString(2, vo.getpBlamerNo());
//		pstmt.setString(3, vo.getpWriterNo());
//		pstmt.setString(4, vo.getpBlaTit());
//		pstmt.setString(5, vo.getpBlaList());
//		pstmt.setString(6, vo.getpBlaDetail());
//
//		
//	    System.out.println(vo.getpNo());
//	    System.out.println(vo.getpBlamerNo());
//	    System.out.println(vo.getpWriterNo());
//	    System.out.println(vo.getpBlaTit());
//	    System.out.println(vo.getpBlaList());
//	    System.out.println(vo.getpBlaDetail());
//	    
//	    
//		int result = pstmt.executeUpdate();
//		
//		
//		// close
//		JDBCTemplate.close(pstmt);
//		
//		return result;		
//		
//		
//	}//blame
	

	public int blame(Connection conn, PostBlameVo pbo) throws Exception {

		// sql
		String sql = "INSERT INTO POST_BLAME ( P_BLA_NO , P_NO , P_BLAMER_NO , P_WRITER_NO , P_BLA_TIT , P_BLA_LIST , P_BLA_DETAIL ) VALUES( SEQ_POST_BLAME.NEXTVAL , ? , ? , ? , ? , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "8");
		pstmt.setString(2, pbo.getpBlamerNo());
		pstmt.setString(3, pbo.getpWriterNo());
		pstmt.setString(4, pbo.getpBlaTit());
		pstmt.setString(5, pbo.getpBlaList());
		pstmt.setString(6, pbo.getpBlaDetail());

		
		System.out.println(pbo.getPostNo());
		System.out.println(pbo.getpBlamerNo());
		System.out.println(pbo.getpWriterNo());
		System.out.println(pbo.getpTit());
		System.out.println(pbo.getpBlaList());
		System.out.println(pbo.getpBlaDetail());
	    
	    
		int result = pstmt.executeUpdate();
		
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;		
		
		
	}//blame
	
	
	

	//BLA_LIST 불러와서 모달창 띄우기
	public List<PostBlameVo> blameList(Connection conn) throws Exception {
		   //SQL
		   String sql = "SELECT * FROM BLAME_REASON";
		   PreparedStatement pstmt = conn.prepareStatement(sql);
		   ResultSet rs = pstmt.executeQuery();
		   //rs
		   List<PostBlameVo> reasonList = new ArrayList<PostBlameVo>();
		   while(rs.next()) {
			   String blaList = rs.getString("BLA_LIST");
			   String blaReason = rs.getString("BLA_REASON");
			   
			   PostBlameVo vo = new PostBlameVo();
			   vo.setpBlaList(blaList);
			   vo.setpBlamerNo(blaReason);
			   reasonList.add(vo);
		   }
		   
		   //close
		   JDBCTemplate.close(rs);
		   JDBCTemplate.close(pstmt);
		   
		   return reasonList;
	}
	
	
	
	
//	//BLA_LIST 불러와서 모달창 띄우기
//	public PostBlameVo blameList(Connection conn, PostBlameVo pvo) throws Exception {
//		   //SQL
//		   String sql = "SELECT * FROM BLAME_REASON";
//		   PreparedStatement pstmt = conn.prepareStatement(sql);
//		   ResultSet rs = pstmt.executeQuery();
//		   //rs
//		   PostBlameVo reasonList = new ArrayList<>();
//		   while(rs.next()) {
//			   String blaList = rs.getString("BLA_LIST");
//			   String blaReason = rs.getString("BLA_REASON");
//			   
//			   PostBlameVo vo = new PostBlameVo();
//			   vo.setpBlaList(blaList);
//			   vo.setpBlamerNo(blaReason);
//			   reasonList.add(vo);
//		   }
//		   
//		   //close
//		   JDBCTemplate.close(rs);
//		   JDBCTemplate.close(pstmt);
//		   
//		   return reasonList;
//	}

}//class
