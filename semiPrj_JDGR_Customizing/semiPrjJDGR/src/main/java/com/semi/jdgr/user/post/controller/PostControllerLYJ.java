package com.semi.jdgr.user.post.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.dao.PostDaoLYJ;
import com.semi.jdgr.post.service.PostServiceLYJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

//@WebServlet("/user/home")
public class PostControllerLYJ extends HttpServlet {

	PostServiceLYJ ps = new PostServiceLYJ();   
	
	//유저 홈화면에 맨 처음에 보이는 전체,카테고리 리스트 조회
	   @Override
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      
	      try {
	            	 
			int listCount = ps.selectUserPostCount();
			
	         
	         //data
	         String currentPage__ = req.getParameter("pno");
	         if(currentPage__ == null) {
	            currentPage__ = "1";
	         }
	         int currentPage = Integer.parseInt(currentPage__);   //현재 페이지
	         int pageLimit = 10;
	         int boardLimit = 5;
	         PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);         
	         
	         
	         //service
	         List<PostVo> postVoList = ps.allSelectUserPostList(pvo);
	         
	         
	         System.out.println("===============");
	         for (PostVo vo : postVoList) {
	            System.out.println(vo);
	         }
	         
	         //result(==view)
	         req.setAttribute("postVoList", postVoList);
	         req.setAttribute("pvo" , pvo);
	         req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);   
	         
	      } catch (Exception e) {
	         
	         System.out.println("[ERROR-B001]포스트 관리 목록 조회 중 에러 발생 ...");
	         e.printStackTrace();
	         req.setAttribute("errorMsg", "포스트 관리 목록 조회 에러");
	         req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
	      }   
	   }
	   
	  	 
}//class
