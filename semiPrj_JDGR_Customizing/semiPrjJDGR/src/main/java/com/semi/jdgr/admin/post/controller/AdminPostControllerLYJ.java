package com.semi.jdgr.admin.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.service.PostServiceLYJ;
import com.semi.jdgr.post.vo.PostVo;

@WebServlet("/admin/post/list")
public class AdminPostControllerLYJ extends HttpServlet{
   
   PostServiceLYJ ps = new PostServiceLYJ();      
   
   //맨 처음에 보이는 전체 리스트 조회
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      try {
            	 
		int listCount = ps.selectPostCount();
		
         
         //data
         String currentPage__ = req.getParameter("pno");
         if(currentPage__ == null) {
            currentPage__ = "1";
         }
         int currentPage = Integer.parseInt(currentPage__);   //현재 페이지
         int pageLimit = 5;
         int boardLimit = 10;
         PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);         
         
         
         //service
         List<PostVo> postVoList = ps.allSelectPostList(pvo);
         
         
         System.out.println("===============");
         for (PostVo vo : postVoList) {
            System.out.println(vo);
         }
         
         //result(==view)
         req.setAttribute("postVoList", postVoList);
         req.setAttribute("pvo" , pvo);
         req.getRequestDispatcher("/WEB-INF/views/admin/post/list.jsp").forward(req, resp);   
         
      } catch (Exception e) {
         
         System.out.println("[ERROR-B001]포스트 관리 목록 조회 중 에러 발생 ...");
         e.printStackTrace();
         req.setAttribute("errorMsg", "포스트 관리 목록 조회 에러");
         req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
      }   
   }
   
   //작성자 이름 검색 시 관리자 포스트 목록 조회
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      try {
         
         
         int listCount = ps.selectPostCount();
         //data
         String currentPage__ = req.getParameter("pno");
         if(currentPage__ == null) {
            currentPage__ = "1";
         }
         int currentPage = Integer.parseInt(currentPage__);   //현재 페이지
         int pageLimit = 5;
         int boardLimit = 10;
         PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);      
         
         
         String memName = req.getParameter("memName");
         //service
         List<PostVo> postVoList = ps.selectPostList(memName);
         
         //result(==view)
         req.setAttribute("postVoList", postVoList);
         req.setAttribute("pvo" , pvo);
          req.getRequestDispatcher("/WEB-INF/views/admin/post/list.jsp").forward(req, resp);
         
      }catch(Exception e) {
         System.out.println("[ERROR-B001]포스트 관리 목록 조회 중 에러 발생 ...");
         e.printStackTrace();
         req.setAttribute("errorMsg", "포스트 관리 목록 조회 에러");
         req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
      }
         
   }
         
}//class