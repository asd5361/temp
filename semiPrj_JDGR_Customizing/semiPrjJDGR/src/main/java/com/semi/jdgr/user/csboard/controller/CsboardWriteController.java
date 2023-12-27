package com.semi.jdgr.user.csboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.service.CsboardService;
import com.semi.jdgr.csboard.vo.CsboardVo;

@WebServlet("/csboard/write")
public class CsboardWriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/views/user/csboard/csboardWrite.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			System.out.println(req.getParameter("category"));
			//로그인 확인 후 안되어 있으면 에러페이지로 보내기 _ 임시 번호 6/****************************************************************/
			String mem_no = "6";
			CsboardVo vo = new CsboardVo();
			vo.setMemNo(mem_no);
			vo.setQuestionCategory(req.getParameter("category"));
			vo.setqTit(req.getParameter("title"));
			vo.setqCon(req.getParameter("content").replace("\r\n", "<br>"));

			//service
			CsboardService cs = new CsboardService();
			int result = cs.CsboardWrite(vo);
			
			//view
			if(result != 1) {
				throw new Exception();
			}
			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "1:1 문의 완료");
			popText.put("completeContent", "");
			req.getSession().setAttribute("popText", popText);
			resp.sendRedirect("/jdgr/csboard/list?pno=1");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 1:1문의 작성 기능 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);			
		}
	}
}
