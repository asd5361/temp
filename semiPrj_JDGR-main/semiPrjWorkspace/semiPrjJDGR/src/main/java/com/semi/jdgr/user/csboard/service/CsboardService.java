package com.semi.jdgr.user.csboard.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.user.csboard.dao.CsboardDao;
import com.semi.jdgr.user.csboard.vo.CsboardVo;
import com.semi.jdgr.util.JDBCTemplate;

public class CsboardService {

	//문의글 상위 5개 불러오기
	public List<CsboardVo> selectCsboardTopList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		List<CsboardVo> csboardVoList = dao.selectCsboardTopList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return csboardVoList;
	}

}
