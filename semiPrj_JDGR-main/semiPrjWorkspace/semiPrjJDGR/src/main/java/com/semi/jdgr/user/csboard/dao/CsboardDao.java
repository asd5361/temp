package com.semi.jdgr.user.csboard.dao;

import java.sql.*;
import java.util.*;

import com.semi.jdgr.user.csboard.vo.CsboardVo;
import com.semi.jdgr.util.JDBCTemplate;

public class CsboardDao {

	public List<CsboardVo> selectCsboardTopList(Connection conn) throws SQLException {
		
		//sql
		String sql = "SELECT * FROM CUSTOMER_CENTER WHERE ROWNUM <= 5 ORDER BY DEL_YN DESC,Q_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<CsboardVo> csboardVoList = new ArrayList<CsboardVo>();
		//rs
		while(rs.next()) {
			CsboardVo vo = new CsboardVo();
			vo.setqNo(rs.getString("Q_NO"));
			vo.setAdminNo(rs.getString("ADMIN_NO"));
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setqTit(rs.getString("Q_TIT"));
			vo.setqCon((rs.getString("Q_CON")));
			vo.setqWriteDate(rs.getString("Q_WRITE_DATE"));
			vo.setAnsewr(rs.getString("ANSEWR"));
			vo.setAnsewrDate(rs.getString("ANSEWR_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			vo.setDelYn(rs.getString("DEL_YN"));
			vo.setQuestionCategory(rs.getString("QUESTION_CATEGORY"));
			
			csboardVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return csboardVoList;
	}

}
