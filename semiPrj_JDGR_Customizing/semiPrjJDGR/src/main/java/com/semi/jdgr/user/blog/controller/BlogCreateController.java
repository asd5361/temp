package com.semi.jdgr.user.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10 		// 파일 하나당 크기
		, maxRequestSize = 1024 * 1024 * 50 	// 리퀘스트 전체 크기
		)
@WebServlet("/blog/create")
public class BlogCreateController extends HttpServlet {
	
	// 블로그 개설하기 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("blogClassName", "blog_set");
		req.getRequestDispatcher("/WEB-INF/views/user/blog/create.jsp").forward(req, resp);
		
	}
	
	// 블로그 개설하기 post
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("확인출력문");
			// data
			Part blogImg = req.getPart("blogImg");
			String blogTitle = req.getParameter("blogTitle");
			String blogUrl = req.getParameter("blogUrl");
			
			// 파일 설정
			String submittedFileName = blogImg.getSubmittedFileName(); // 파일 이름 가져오기
			String fileExtension = submittedFileName.substring(submittedFileName.lastIndexOf('.')); // 확장자 추출
			InputStream in = blogImg.getInputStream(); // 읽기 준비
			
			String sep = File.separator;
			
			// 내보내기 준비
			String path = sep + "resources" + sep + "user" + sep + "upload" + sep + "userImg";
			String realPath = req.getServletContext().getRealPath(path);
			String fileName = sep + "jdgrUser_" + (int)(Math.random() * 100000000) + fileExtension;
			File target = new File(realPath + fileName);
			FileOutputStream out = new FileOutputStream(target);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = in.read(buf)) != -1) {
				out.write(buf, 0, size);
			}
			
			String fileUrl = "jdgr" + path + fileName;
			
			// 로그인 유저 정보 가져오기
			MemberVo loginMemberVo = (MemberVo) req.getSession().getAttribute("loginMember");
			
			BlogVo blogVo = new BlogVo();
			blogVo.setBlogTitle(blogTitle);
			blogVo.setBlogUrl(blogUrl);
			blogVo.setBlogImg(fileUrl);
			blogVo.setMemNo(loginMemberVo.getMemNo());

			// service
			BlogService bs = new BlogService();
			Map<String, Object> blogInfo = bs.createBlog(blogVo, loginMemberVo);
			int result = (int) blogInfo.get("result");
			List<BlogVo> userBlogList = (List<BlogVo>) blogInfo.get("userBlogList");
			
			// result
			if(result != 1) {
				throw new Exception("결과값이 1이아님");
			}
			
			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "블로그 개설이 완료되었습니다!");
			popText.put("completeContent", "");
			req.getSession().setAttribute("popText", popText);
			
			// 세션 업데이트
			req.getSession().setAttribute("loginMemberBlogVoList", userBlogList);
			req.setAttribute("blogClassName", "blog_set");
			
			resp.sendRedirect("/jdgr/userSet/blog");
			
		} catch(Exception e) {
			e.printStackTrace();
			
			// 팝업메세지 전달
			
			// 에러 메시지 가져오기
		    String errorMessage = e.getMessage();
		    
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("warningId", "display: flex;");
			popText.put("warningTitle", errorMessage);
			popText.put("warningContent", "다시 확인해주세요!");
			req.getSession().setAttribute("popText", popText);
			
			req.setAttribute("blogClassName", "blog_set");
			req.getRequestDispatcher("/WEB-INF/views/user/blog/create.jsp").forward(req, resp);
		}
	}
	
}
