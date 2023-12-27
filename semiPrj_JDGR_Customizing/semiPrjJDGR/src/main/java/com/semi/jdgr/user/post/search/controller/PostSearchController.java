package com.semi.jdgr.user.post.search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.service.PostServiceLYJ;
import com.semi.jdgr.post.service.PostServicePBR;
import com.semi.jdgr.post.vo.PostVo;

@WebServlet("/post/search")
public class PostSearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String type = req.getParameter("searchOption");
			String content = req.getParameter("searchContent");

			PostServicePBR ps = new PostServicePBR();

			List<PostVo> postVoListSize = ps.searchPostList(type, content);

			int listCount = postVoListSize.size();

			String currentPage__ = req.getParameter("pno");
			if (currentPage__ == null) {
				currentPage__ = "1";
			}
			int currentPage = Integer.parseInt(currentPage__); // 현재 페이지
			int pageLimit = 10;
			int boardLimit = 5;
			PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

			List<PostVo> postVoList = ps.searchPostList(type, content, pageVo);

			if (postVoList == null) {
				throw new Exception("검색 오류 분류에서오류");
			}

			req.setAttribute("searchOption", type);
			req.setAttribute("searchContent", content);
			req.setAttribute("listCount", listCount);

			req.setAttribute("pageVo", pageVo);
			req.setAttribute("postVoList", postVoList);
			req.getRequestDispatcher("/WEB-INF/views/user/post/unifiedSearchPost.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
