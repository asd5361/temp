package com.semi.jdgr.user.post.category.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.service.PostServiceLYJ;
import com.semi.jdgr.post.vo.PostVo;

@WebServlet("/post/category")
public class PostCategoryController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding("UTF-8");
			//ajax는 get으로 보낸다.
			String categoryNo = req.getParameter("categoryNo");
			//포스트리스트 불러오기
			
			PostServiceLYJ ps = new PostServiceLYJ();
			int listCount = ps.selectPostCount();
			
	         
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
			List<PostVo> postVoList = ps.separatedList(categoryNo,pvo);
			
//			System.out.println("===============");
//	        for (PostVo vo : postVoList) {
//	           System.out.println(vo);
//	        }
			
			//result(==view)
//	        req.setAttribute("postVoList", postVoList);
//	        req.setAttribute("pvo" , pvo);
			
			if(postVoList ==null) {
				throw new Exception("카테고리 분류에서오류");
			}

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pvo", pvo);
			map.put("postVoList", postVoList);
			
			Gson gson = new Gson();
			String str = gson.toJson(map);
			
			PrintWriter out = resp.getWriter();
			out.write(str);
			
		} catch (Exception e) {
		}
	}

}
