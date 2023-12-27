package com.semi.jdgr.user.csboard.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.service.CsboardService;
import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;

@WebServlet("/csboard")
public class CsboardController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//service
			NoticeService ns = new NoticeService();
			CsboardService cs = new CsboardService();
			List<NoticeVo> noticeVoList = ns.selectNoticeTopList();
			List<CsboardVo> csboardVoList = cs.selectCsboardTopList();
			
			if(noticeVoList == null && csboardVoList == null) {
				throw new Exception();
			}
			
			//view 

			req.setAttribute("noticeVoList", noticeVoList);
			req.setAttribute("csboardVoList", csboardVoList);

			req.getRequestDispatcher("/WEB-INF/views/user/csboard/csboard.jsp").forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 고객센터 메인 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
}
