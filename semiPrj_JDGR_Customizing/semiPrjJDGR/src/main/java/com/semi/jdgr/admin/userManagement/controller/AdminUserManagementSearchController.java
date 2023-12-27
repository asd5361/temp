package com.semi.jdgr.admin.userManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.userManagement.service.UserManagementService;

@WebServlet("/admin/userManagement/search")
public class AdminUserManagementSearchController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			UserManagementService ums = new UserManagementService(); 
			
			//data
			MemberVo searchVo = new MemberVo();
			searchVo.setMemId(req.getParameter("id"));
			searchVo.setMemName(req.getParameter("name"));
			searchVo.setMemNick(req.getParameter("nick"));
			int listCount = ums.adminUserManagementSearchCount(searchVo);
			
			String currentPage__ = req.getParameter("pno");
			if(currentPage__ == null) {
				currentPage__ = "1";
			}

			int currentPage = Integer.parseInt(currentPage__); //현재 페이지
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			//service
			List<MemberVo> UserManagementVoList = ums.adminUserManagementeSearch(searchVo,pvo);
			//resule
			if(UserManagementVoList == null) {
				throw new Exception();
			}
			
			req.setAttribute("searchVo", searchVo);
			req.setAttribute("pageVo", pvo);
			req.setAttribute("UserManagementVoList", UserManagementVoList);
			req.getRequestDispatcher("/WEB-INF/views/admin/userManagement/list.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 회원 검색 기능 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
		}		
	}
}
