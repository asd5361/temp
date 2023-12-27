package com.semi.jdgr.user.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.post.service.PostServiceJOJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.user.reply.service.ReplyService;
import com.semi.jdgr.user.reply.vo.ReplyVo;

@WebServlet("/post/detail")
public class PostControllerJOJ extends HttpServlet {

	// 포스트 상세보기 (화면)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// url정보
			String BlogUrl = req.getParameter("url");
			// data (카테고리 넘버)
			String GroupNo = req.getParameter("GroupNo");
			// data (포스트 넘버)
			String pNo = req.getParameter("pNo");
			
			int cnt = 0;
			// service
			PostServiceJOJ ps = new PostServiceJOJ();
			PostVo postDetailVo = ps.PostDetail(GroupNo, BlogUrl, pNo, cnt);
			PostVo heartCnt = ps.PostDetailHeartCnt(postDetailVo);
			PostVo replyCnt = ps.PostDetailReplyCnt(postDetailVo);

			// blogUrl에 null값 들어올 경우 (pNo값으로 포스트 조회할때) (블로그를 통한 접속이 아닐경우)
			BlogService bs = new BlogService();
			if( BlogUrl == null ) {
				BlogUrl = postDetailVo.getBlogUrl();
				GroupNo = postDetailVo.getGroupNo();
			}
				
			BlogVo blogUrlVo = bs.getUserblog(BlogUrl); // url에 맞는 블로그 가져오기
			List<GroupVo> groupVoList = bs.getGroupList(blogUrlVo); // 카테고리그룹 가져오기
			
			req.setAttribute("blogClassName", "blog");
			req.getSession().setAttribute("blogUrlVo", blogUrlVo); // url 블로그 저장
			
			req.setAttribute("groupVoList", groupVoList);

			MemberVo loginMemberVo = (MemberVo) req.getSession().getAttribute("loginMember");
			
			/****************** 댓글 ***************************/
			// service
			ReplyService replayService = new ReplyService();
			List<ReplyVo> replyVoList = replayService.getReplyListByNo(postDetailVo.getPostNo());
			System.out.println("댓글volist::" + replyVoList);
			System.out.println("포스트넘버" + postDetailVo.getPostNo());
			req.setAttribute("replyVoList", replyVoList);
			
			/********************************************************/
			
			 System.out.println(postDetailVo);
			// result
			HttpSession session = req.getSession();
			session.setAttribute("postDetailVo", postDetailVo);
			
			req.setAttribute("heartCnt", heartCnt);
			req.setAttribute("replyCnt", replyCnt);
			req.getRequestDispatcher("/WEB-INF/views/user/blog/blogView.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("블로그 포스트 상세보기 실패");
			e.printStackTrace();
			req.setAttribute("errorMsg", "블로그 포스트 상세보기 실패");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}

	}// PostDetail

	// 포스트 상세보기 (로직)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// data

		// service

		// result

	}

}
