package com.semi.jdgr.user.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 100 ,
		maxFileSize = 1024 * 1024 * 1000 ,
		maxRequestSize = 1024 * 1024 * 5000
	)
@WebServlet("/blog/write/imageUpload")
public class BlogImageUploadController extends HttpServlet {
	
	// 이미지 업로드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Part filePart = req.getPart("file");
		// Java EE 7 이상에서 제공되는 getSubmittedFileName 메서드 사용
	    String originalFileName = filePart.getSubmittedFileName();
	    // 확장자 추출
	    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
	    
		String path = "/resources/user/upload/img";
		String realPath = req.getServletContext().getRealPath(path);
		String name = "/jdgr_" + (int)(Math.random() * 100000000) + "." + fileExtension;
		File target = new File(realPath + name);
		
		InputStream in = filePart.getInputStream();
		FileOutputStream out = new FileOutputStream(target);
		
		byte[] buf = new byte[1024];
		int size = 0;
		while( (size = in.read(buf)) != -1 ) {
			out.write(buf, 0 , size);
		}
		
		String root = req.getContextPath();
		String fileUrl = root + path + name;
		resp.getWriter().write(fileUrl);
	}
	
}
