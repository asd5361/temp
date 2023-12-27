package com.semi.jdgr.user.blame.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.blame.dao.PostBlameDao;
import com.semi.jdgr.user.blame.vo.PostBlameVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostBlameService {

//	//신고하기 버튼 클릭했을 때 포스트 정보 불러오기
//	public PostBlameVo getPostInfo(PostBlameVo vo) throws Exception {
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//dao
//		PostBlameDao dao = new PostBlameDao();
//		PostBlameVo pvo = dao.getPostInfo(conn, vo);
//		
//		//close
//		JDBCTemplate.close(conn);
//		
//		return pvo;	
//		
//	}//getPostInfo
	
	
	
	//신고하기 버튼 클릭했을 때 포스트 정보 불러오기
	public PostBlameVo getPostInfo(String pNo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		PostBlameDao dao = new PostBlameDao();
		PostBlameVo pbo = dao.getPostInfo(conn, pNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return pbo;	
		
	}//getPostInfo
	
	
//	//신고하기
//	public int blame(PostBlameVo vo) throws Exception {
//		
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
////
////		//세부사유 작성
////		String detailContent = vo.getpBlaDetail();
////		boolean detailContentOk = detailContent.matches("[a-z0-9]{0,100}");
////		if(!detailContentOk) {
////			throw new Exception("세부 사유를 작성하세요");
////		}
//
//		
//		//dao
//		PostBlameDao dao =  new PostBlameDao();
//		int result = dao.blame(conn, vo);
//		
//		// tx
//		if(result == 1) {
//			JDBCTemplate.commit(conn);
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
//		
//		// close
//		JDBCTemplate.close(conn);
//		
//		return result;
//
//	}//blame
	
	
	
	
	//신고하기
		public int blame(PostBlameVo  pbo) throws Exception {
			
			//conn
			Connection conn = JDBCTemplate.getConnection();
			
	//
//			//세부사유 작성
//			String detailContent = vo.getpBlaDetail();
//			boolean detailContentOk = detailContent.matches("[a-z0-9]{0,100}");
//			if(!detailContentOk) {
//				throw new Exception("세부 사유를 작성하세요");
//			}

			
			//dao
			PostBlameDao dao =  new PostBlameDao();
			int result = dao.blame(conn, pbo);
			
			// tx
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			// close
			JDBCTemplate.close(conn);
			
			return result;

		}//blame
	
	
	//bla_reason 리스트 불러와서 모달창에 띄우기
	public List<PostBlameVo> blameList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();

		
		//dao
		PostBlameDao dao =  new PostBlameDao();
		List<PostBlameVo> voList = dao.blameList(conn);		
		

		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
				
	}//blameList
	
	
	

}
