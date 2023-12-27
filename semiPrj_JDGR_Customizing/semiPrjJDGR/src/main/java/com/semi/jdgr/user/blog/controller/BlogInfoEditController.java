package com.semi.jdgr.user.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
@WebServlet("/blogSet/blogInfo")
public class BlogInfoEditController extends HttpServlet {
	
	// 블로그 정보 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// data
			String userUrl = req.getParameter("url");
			
			// service
			BlogService bs = new BlogService();
			BlogVo blogVo = bs.getUserblog(userUrl); // 블로그 데이터 불러오기
			
			// result
			if(blogVo == null) {
				throw new Exception("블로그 정보를 찾을 수 없습니다.");
			}
			
			req.setAttribute("blogUserData", blogVo);
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "blogInfo");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/blogInfo.jsp").forward(req, resp);
			
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
			req.setAttribute("blogSideClassName", "blogInfo");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/blogInfo.jsp").forward(req, resp);
		}
	}
	
	// 블로그 정보 수정 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// data
			String blogTitle = req.getParameter("blogTitle");
			Part blogImg = req.getPart("blogImg");
			String blogUrl = req.getParameter("blogUrl");
			String fileUrl = null;
			// 파일 설정
			String submittedFileName = blogImg.getSubmittedFileName(); // 파일 이름 가져오기
			if(submittedFileName != null && !submittedFileName.isEmpty()) {
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
				fileUrl = "/jdgr" + path + fileName;
			}
			
			BlogVo blogVo = new BlogVo();
			blogVo.setBlogTitle(blogTitle);
			blogVo.setBlogImg(fileUrl);
			blogVo.setBlogUrl(blogUrl);
			
			
			// service
			BlogService bs = new BlogService();
			BlogVo resultVo = bs.editInfo(blogVo);
			
			// result
			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "수정이 완료되었습니다!");
			popText.put("completeContent", "");
			req.getSession().setAttribute("popText", popText);
						
			req.setAttribute("blogUserData", resultVo);
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "blogInfo");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/blogInfo.jsp").forward(req, resp);
			
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
			req.setAttribute("blogSideClassName", "blogInfo");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/blogInfo.jsp").forward(req, resp);
		}
	}
	
}
