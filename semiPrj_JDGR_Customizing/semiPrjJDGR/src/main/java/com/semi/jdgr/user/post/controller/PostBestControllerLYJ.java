package com.semi.jdgr.user.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.service.PostServiceLYJ;
import com.semi.jdgr.post.vo.PostVo;

//@WebServlet("/home/best")
public class PostBestControllerLYJ extends HttpServlet{

	
//	//인기베스트 10포스트 보여주기
//	PostServiceLYJ ps = new PostServiceLYJ();      
//	   
//   @Override
//   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      
//      try {
//         
//         //service
//         List<PostVo> bestVoList = ps.bestSelectPostList();
//         
//         
//         System.out.println("===============");
//         for (PostVo vo : bestVoList) {
//            System.out.println(vo);
//         }
//         
//         //result(==view)
//         System.out.println("컨트롤러쪽 :" +bestVoList);
//         req.setAttribute("bestVoList", bestVoList);         
//         req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);   
//         
//      } catch (Exception e) {
//         
//         System.out.println("[ERROR-B001]인기베스트 10개 포스트 조회 중 에러 발생 ...");
//         e.printStackTrace();
//         req.setAttribute("errorMsg", "인기베스트 10개 포스트 조회 중 에러");
//         req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
//      }   
//   }

	

	
}//class
