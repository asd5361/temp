package com.semi.jdgr.user.main;

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
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.service.PostServiceLYJ;
import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/home")
public class HomeController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			PostServiceLYJ ps = new PostServiceLYJ();   
			
			/////인기 포스트
			//service
	         List<PostVo> bestVoList = ps.bestSelectPostList();
	         
	         
//	         System.out.println("===============");
//	         for (PostVo vo : bestVoList) {
//	            System.out.println(vo);
//	         }
	         
	         //result(==view)
	         System.out.println("컨트롤러쪽 :" +bestVoList);
	         req.setAttribute("bestVoList", bestVoList);           
	         /////////////////인기 포스트
	         
	         
	         
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
	         List<PostVo> postVoList = ps.allSelectUserPostList(pvo);
	         
	         
//	         System.out.println("===============");
//	         for (PostVo vo : postVoList) {
//	            System.out.println(vo);
//	         }
	         
	         //result(==view)
	         req.setAttribute("postVoList", postVoList);
	         req.setAttribute("pvo" , pvo);
	         
			// 로그인 세션 가져오기
			HttpSession session = req.getSession();
			MemberVo memberVo = (MemberVo) session.getAttribute("loginMember");

			List<CategoryVo> categoryVoList = ps.selectCategory();
			
			if(categoryVoList == null) {
				throw new Exception();
				
			}
			 req.setAttribute("categoryVoList", categoryVoList);
			
			// 블로그 정보,리스트 세션에 저장하기
			BlogService bs = new BlogService();
			BlogVo blogVo = null;
			List<BlogVo> blogVoList = null;
			if(memberVo != null) {
				blogVo = bs.getUserReqblog(memberVo); // 대표 블로그 정보 가져오기
				blogVoList = bs.getBlogList(memberVo); // 블로그 리스트 가져오기
			}
			System.out.println(categoryVoList);
			session.setAttribute("loginMemberBlogVo", blogVo);
			session.setAttribute("loginMemberBlogVoList", blogVoList);
			
			req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}//class
