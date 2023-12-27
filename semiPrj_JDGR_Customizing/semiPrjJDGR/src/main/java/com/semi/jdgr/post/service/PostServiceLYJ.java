package com.semi.jdgr.post.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.notice.dao.NoticeDao;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.dao.PostDaoLYJ;
import com.semi.jdgr.util.JDBCTemplate;

public class PostServiceLYJ {
   
   //맨 처음에 보이는 전체 리스트 조회
   public List<PostVo> allSelectPostList(PageVo pvo) throws Exception {
      
      // conn
      Connection conn = JDBCTemplate.getConnection();
      
      // dao
      PostDaoLYJ dao = new PostDaoLYJ();
      List<PostVo> postVoList = dao.allSelectPostList(conn,pvo);
      
      
      // 공감수 조회하는 쿼리문 실행
      for (PostVo vo : postVoList) {
//               vo = postVoList.get(0)
          String heartCnt = dao.getheartCnt(conn , vo.getPostNo());
         vo.setHeartCnt(heartCnt);
      }
      
      // 댓글수 조회하는 쿼리문 실행      
      for (PostVo vo : postVoList) {
         String replyCnt = dao.getreplyCnt(conn , vo.getPostNo());
         vo.setReplyCnt(replyCnt);
      }
      
      // close
      JDBCTemplate.close(conn);
      
      return postVoList;

   }//allSelectPostList
   
   

   //작성자 이름 검색 시 관리자 포스트 목록 조회
   public List<PostVo> selectPostList(String memName) throws Exception {
      
      // conn
      Connection conn = JDBCTemplate.getConnection();
      
      // dao
      PostDaoLYJ dao = new PostDaoLYJ();
      List<PostVo> postVoList = dao.selectPostList(conn, memName);
      
      
      // 공감수 조회하는 쿼리문 실행
      for (PostVo vo : postVoList) {
//         vo = postVoList.get(0)
          String heartCnt = dao.getheartCnt(conn , vo.getPostNo());
         vo.setHeartCnt(heartCnt);
      }
      
      // 댓글수 조회하는 쿼리문 실행      
      for (PostVo vo : postVoList) {
         String replyCnt = dao.getreplyCnt(conn , vo.getPostNo());
         vo.setReplyCnt(replyCnt);
      }
      
      // close
      JDBCTemplate.close(conn);
      
      return postVoList;
   
   }//selectPostList


   // 게시글 갯수 조회(맨 처음에 보이는 전체 리스트 조회)
   public int selectPostCount() throws Exception {
      
      // conn
      Connection conn = JDBCTemplate.getConnection();
      
      // DAO
      PostDaoLYJ dao = new PostDaoLYJ();
      int cnt = dao.selectPostCount(conn);
      
      // close
      JDBCTemplate.close(conn);
      
      return cnt;
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   //유저 홈화면에 맨 처음에 보이는 전체 리스트 조회
   public List<PostVo> allSelectUserPostList(PageVo pvo) throws Exception {
      
      // conn
      Connection conn = JDBCTemplate.getConnection();
      
      // dao
      PostDaoLYJ dao = new PostDaoLYJ();
      List<PostVo> postVoList = dao.allSelectUserPostList(conn,pvo);
      
      
      // 공감수 조회하는 쿼리문 실행
      for (PostVo vo : postVoList) {
//               vo = postVoList.get(0)
          String heartCnt = dao.getheartCnt(conn , vo.getPostNo());
         vo.setHeartCnt(heartCnt);
      }
      
      // 댓글수 조회하는 쿼리문 실행      
      for (PostVo vo : postVoList) {
         String replyCnt = dao.getreplyCnt(conn , vo.getPostNo());
         vo.setReplyCnt(replyCnt);
      }
      
      // close
      JDBCTemplate.close(conn);
      
      return postVoList;

   }//allSelectUserPostList

   
// 카테고리 선택
public List<CategoryVo> selectCategory() throws Exception {
	
	//conn
	Connection conn = JDBCTemplate.getConnection();
	
	//DAO
	PostDaoLYJ dao = new PostDaoLYJ();
	List<CategoryVo> categoryVoList = dao.selectCategory(conn);
	
	JDBCTemplate.close(conn);
	
	return categoryVoList;
}


//카테고리별 리스트 조회
public List<PostVo> separatedList(String categoryNo, PageVo pvo) throws Exception {
	//conn
	Connection conn = JDBCTemplate.getConnection();
	
	//dao
	PostDaoLYJ dao = new PostDaoLYJ();
	List<PostVo> postVoList = dao.separatedList(conn,categoryNo,pvo);
	
	// 공감수 조회하는 쿼리문 실행
    for (PostVo vo : postVoList) {
//             vo = postVoList.get(0)
        String heartCnt = dao.getheartCnt(conn , vo.getPostNo());
       vo.setHeartCnt(heartCnt);
    }
    
    // 댓글수 조회하는 쿼리문 실행      
    for (PostVo vo : postVoList) {
       String replyCnt = dao.getreplyCnt(conn , vo.getPostNo());
       vo.setReplyCnt(replyCnt);
    }
	
	return postVoList;
	

	
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//인기베스트 10포스트 보여주기
public List<PostVo> bestSelectPostList() throws Exception {
	// conn
    Connection conn = JDBCTemplate.getConnection();
    
    // dao
    PostDaoLYJ dao = new PostDaoLYJ();
    List<PostVo> bestVoList = dao.bestSelectPostList(conn);
    
    
    // 공감수 조회하는 쿼리문 실행
    for (PostVo vo : bestVoList) {
//             vo = postVoList.get(0)
        String heartCnt = dao.getheartCnt(conn , vo.getPostNo());
       vo.setHeartCnt(heartCnt);
    }
    
    // close
    JDBCTemplate.close(conn);
    
    return bestVoList;

}//bestSelectPostList
   
   

   
   

}//class