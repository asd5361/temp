package com.semi.jdgr.user.blame.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.admin.blame.vo.AdminPostBlameVo;
import com.semi.jdgr.user.blame.dao.ReplyBlameDao;
import com.semi.jdgr.user.blame.vo.ReplyBlameVo;
import com.semi.jdgr.util.JDBCTemplate;

public class ReplyBlameService {

	//신고하기 버튼 클릭했을 때 댓글 정보 불러오기
	public ReplyBlameVo getReplyInfo(String rNo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		ReplyBlameDao dao = new ReplyBlameDao();
		ReplyBlameVo rbo = dao.getReplyInfo(conn, rNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return rbo;	
	}
	
	
	//신고하기
	public int blame(ReplyBlameVo rbo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
//		//세부사유 작성
//		String detailContent = vo.getrBlaDetail();
//		boolean detailContentOk = detailContent.matches("[a-z0-9]{0,100}");
//		if(!detailContentOk) {
//			throw new Exception("세부 사유를 작성하세요");
//		}
		
		
		//dao
		ReplyBlameDao dao =  new ReplyBlameDao();
		int result = dao.blame(conn, rbo);
		
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
	public List<ReplyBlameVo> blameList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();

		
		//dao
		ReplyBlameDao dao =  new ReplyBlameDao();
		List<ReplyBlameVo> voList = dao.blameList(conn);		
		

		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
				
	}//blameList



	
}
