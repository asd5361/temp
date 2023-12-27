package com.semi.jdgr.user.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.user.reply.service.ReplyService;
import com.semi.jdgr.user.reply.vo.ReplyVo;

@WebServlet("/post/reply")
public class ReplyController extends HttpServlet{

	// 댓글 목록 조회하여 JSON 형태로 문자열 내보내기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// data
			String postNo = req.getParameter("postNo");	// 포스트 넘버 가져오기
			
			// service
			ReplyService bs = new ReplyService();
			List<ReplyVo> replyVoList = bs.getReplyListByNo(postNo);
			
			// result
			
		}catch(Exception e) {
			System.out.println("댓글 불러오기 실패 ...");
			e.printStackTrace();
		}
	}//doGet
}//class
