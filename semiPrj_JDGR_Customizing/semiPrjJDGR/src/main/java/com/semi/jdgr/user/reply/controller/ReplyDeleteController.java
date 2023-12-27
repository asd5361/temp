package com.semi.jdgr.user.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.user.reply.service.ReplyService;
import com.semi.jdgr.user.reply.vo.ReplyVo;

@WebServlet("/reply/delete")
public class ReplyDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String replyNo = req.getParameter("replyNo");
			String postNo = req.getParameter("postNo");
			
			ReplyVo replyVo = new ReplyVo();
			replyVo.setReplyNo(replyNo);
			
			// service
			ReplyService rs = new ReplyService();
			int result = rs.replyDelete(replyVo);
			System.out.println(result);
			// result
			if(result != 1) {
				throw new Exception();
			}
			
			resp.sendRedirect("/jdgr/post/detail?pNo=" + postNo);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
