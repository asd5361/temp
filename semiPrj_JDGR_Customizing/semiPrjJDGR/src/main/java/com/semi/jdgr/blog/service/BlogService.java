package com.semi.jdgr.blog.service;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semi.jdgr.blog.dao.BlogDao;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class BlogService {

	// url에 맞는 블로그 정보 가져오기
	public BlogVo getUserblog(String getBlogUrl) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo blogUrlVo = dao.getUserBlog(conn, getBlogUrl);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogUrlVo;
	}
	
	// 대표블로그 정보 가져오기
	public BlogVo getUserReqblog(MemberVo memberVo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo blogVo = dao.getUserReqBlog(conn, memberVo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVo;
	}
	
	// 카테고리 그룹정보 가져오기
	public List<GroupVo> getGroupList(BlogVo blogVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<GroupVo> groupVoList = dao.getGroupList(conn, blogVo);
		
		// close
		JDBCTemplate.close(conn);
		
		return groupVoList;
	}

	// 블로그 리스트 가져오기
	public List<BlogVo> getBlogList(MemberVo memberVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<BlogVo> blogVoList = dao.getBlogList(conn, memberVo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVoList;
	}

	// 대표 블로그 수정하기
	public int editBlogRep(BlogVo blogVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		int result = dao.editBlogRep(conn, blogVo);
		
		// tx
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 블로그 만들기
	public Map<String, Object> createBlog(BlogVo blogVo, MemberVo loginMemberVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		BlogDao dao = new BlogDao();
		List<BlogVo> blogAllList = dao.getBlogAllList(conn); // 모든 블로그 정보 가져오기

        // 블로그 url이 다른사람것과 겹치는지확인
        for(BlogVo listVo : blogAllList) {
        	if(listVo.getBlogUrl().equals(blogVo.getBlogUrl())) {
        		throw new Exception("이미 사용중인 URL입니다.");
        	}
        }
		int result = dao.createBlog(conn, blogVo);
		List<BlogVo> userBlogList = dao.getBlogList(conn, loginMemberVo); // 해당유저의 블로그 리스트 가져오기
		
		// 비즈니스 로직
        Pattern blogTitleRegex = Pattern.compile("^[가-힣a-zA-Z0-9\\s]{0,25}$"); // 한글, 영문, 숫자, 띄어쓰기 혼용가능 (25자 이내)
        Pattern blogUrlRegex = Pattern.compile("^[a-zA-Z0-9]{4,32}$"); // 최소 4자 최대 32자 영문, 숫자로 입력
        Pattern blogImgRegex = Pattern.compile("^(jpg|jpeg|png|gif|svg)$"); // 이미지파일만 받기
        
        // 이미지 경로
        String blogImgPath = blogVo.getBlogImg();
        String sep = File.separator;
        // 파일 이름 추출
        int lastSeparatorIndex = blogImgPath.lastIndexOf(sep);
        String fileName = lastSeparatorIndex >= 0 ? blogImgPath.substring(lastSeparatorIndex + 1) : blogImgPath;
        // 확장자 추출
        int dotIndex = fileName.lastIndexOf('.');
        String fileExtension = null;
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(dotIndex + 1);
        }
        // Matcher 객체 생성
        Matcher blogTitleMatcher = blogTitleRegex.matcher(blogVo.getBlogTitle());
        Matcher blogUrlMatcher = blogUrlRegex.matcher(blogVo.getBlogUrl());
        Matcher blogImgMatcher = blogImgRegex.matcher(fileExtension);
        // 이미지 파일 확장자 정규표현식과 일치하는지 확인
        if(!blogImgMatcher.matches()) {
            throw new Exception("올바르지 않은 이미지 파일 확장자입니다. 파일 확장자: " + fileExtension);
        }

        // 블로그 타이틀 정규표현식과 일치하는지 확인
        if(!blogTitleMatcher.matches()) {
            throw new Exception("블로그 타이틀이 적절하지 않습니다.");
        }
        // 블로그 URL 정규표현식과 일치하는지 확인
        if(!blogUrlMatcher.matches()) {
            throw new Exception("블로그 URL이 적절하지 않습니다.");
        }
        
        // 블로그 개수가 3개 이상이면 생성불가(해야됨)
        if(userBlogList.size() > 3) {
        	throw new Exception("블로그는 3개까지만 생성 가능합니다.");
        }
        // 블로그명이 없으면 닉네임님의 블로그 고정
        if(blogVo.getBlogTitle() == null) {
        	blogVo.setBlogTitle(loginMemberVo.getMemNick() +  "님의 블로그");
        }
        // 블로그 url이 값이 없는지 확인
        if(blogVo.getBlogUrl() == null) {
        	throw new Exception("블로그 URL을 입력해주세요.");
        }
        
        // 블로그 세션 업데이트를 위한 BlogList와 result
        Map<String, Object> blogInfo = new HashMap<String, Object>();
        blogInfo.put("result", result);
        blogInfo.put("userBlogList", userBlogList);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return blogInfo;
	}

	// 블로그 정보 수정
	public BlogVo editInfo(BlogVo blogVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		
		// 비즈니스 로직
        Pattern blogTitleRegex = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9\\s]{0,25}$"); // 한글, 영문, 숫자, 띄어쓰기 혼용가능 (25자 이내)
        Pattern blogImgRegex = Pattern.compile("^(jpg|jpeg|png|gif|svg)$"); // 이미지파일만 받기
        
        // 이미지 경로
        String blogImgPath = blogVo.getBlogImg();
        if(blogImgPath != null && !blogImgPath.isEmpty()) {
        	String sep = File.separator;
            // 파일 이름 추출
            int lastSeparatorIndex = blogImgPath.lastIndexOf(sep);
            String fileName = lastSeparatorIndex >= 0 ? blogImgPath.substring(lastSeparatorIndex + 1) : blogImgPath;
            // 확장자 추출
            int dotIndex = fileName.lastIndexOf('.');
            String fileExtension = null;
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                fileExtension = fileName.substring(dotIndex + 1);
            }
            // Matcher 객체 생성
            Matcher blogImgMatcher = blogImgRegex.matcher(fileExtension);
            // 이미지 파일 확장자 정규표현식과 일치하는지 확인
            if(!blogImgMatcher.matches()) {
                throw new Exception("올바르지 않은 이미지 파일 확장자입니다. 파일 확장자: " + fileExtension);
            }
        }
        
        // Matcher 객체 생성
        Matcher blogTitleMatcher = blogTitleRegex.matcher(blogVo.getBlogTitle());
        // 블로그 타이틀 정규표현식과 일치하는지 확인
        if(!blogTitleMatcher.matches()) {
            throw new Exception("블로그 타이틀이 적절하지 않습니다.");
        }
		
		// dao
		BlogDao dao = new BlogDao();
		int result = 0;
		// 이미지 경로가 null이면 이미지 업데이트 안함
		if(blogImgPath != null && !blogImgPath.isEmpty()) {
			result = dao.editInfo(conn, blogVo); // 수정 업데이트 결과값
		} else {
			result = dao.editImgNoInfo(conn, blogVo); // 수정 업데이트 결과값
		}
		
        
        // tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// 수정된 정보 select
		BlogVo editBlogVo = dao.getUserBlog(conn, blogVo.getBlogUrl());
		
		// close
		JDBCTemplate.close(conn);
		
		return editBlogVo;
	}

	// 블로그 레이아웃 수정
	public BlogVo editLayout(BlogVo blogVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// 검증로직
		blogVo.setrCommentsYn(getCheckedValue(blogVo.getrCommentsYn()));
		blogVo.setFollowBlogYn(getCheckedValue(blogVo.getFollowBlogYn()));
		blogVo.setVisitorsCntYn(getCheckedValue(blogVo.getVisitorsCntYn()));
		blogVo.setClockYn(getCheckedValue(blogVo.getClockYn()));
		blogVo.setMapYn(getCheckedValue(blogVo.getMapYn()));
		
		// dao
		BlogDao dao = new BlogDao();
		int result = dao.editLayout(conn, blogVo);
		
		
		// tx
		BlogVo editBlogVo = null;
		if(result == 1) {
			JDBCTemplate.commit(conn);
			editBlogVo = dao.getUserBlog(conn, blogVo.getBlogUrl()); // 수정된 정보 select
		} else {
			JDBCTemplate.rollback(conn);
		}
				
		// close
		JDBCTemplate.close(conn);
		
		return editBlogVo;
	}
	
	// 검증메소드
	private String getCheckedValue(String value) {
	    return (value != null) ? "Y" : "N";
	}

	// 블로그 스킨 정보 수정
	public BlogVo editSkin(BlogVo blogVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		int result = dao.editSkin(conn, blogVo);
		
		// tx
		BlogVo editBlogVo = null;
		if(result == 1) {
			JDBCTemplate.commit(conn);
			editBlogVo = dao.getUserBlog(conn, blogVo.getBlogUrl()); // 수정된 정보 select
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return editBlogVo;
	}

	// 카테고리 수정화면 가져오기
	public List<GroupVo> getEditGroupList(String userUrl) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo blogVo = dao.getUserBlog(conn, userUrl); // 블로그 정보 가져오기
		List<GroupVo> groupVoList = dao.getGroupList(conn, blogVo); // 카테고리 그룹 리스트 가져오기
		
		// close
		JDBCTemplate.close(conn);
		
		return groupVoList;
	}

	// 블로그 그룹 카테고리 추가하기
	public List<GroupVo> createGroup(GroupVo groupVo, BlogVo blogVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo userBlogVo = dao.getUserBlog(conn, blogVo.getBlogUrl()); // 블로그 정보 가져오기
		int result = dao.createGroup(conn, groupVo, userBlogVo.getBlogNo());
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		List<GroupVo> groupVoList = dao.getGroupList(conn, userBlogVo); // 카테고리 그룹 리스트 가져오기
		
		// close
		JDBCTemplate.close(conn);
		
		return groupVoList;
	}

	// 블로그 그룹 카테고리 삭제하기
	public List<GroupVo> deleteGroup(GroupVo groupVo, BlogVo blogVo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo userBlogVo = dao.getUserBlog(conn, blogVo.getBlogUrl()); // 블로그 정보 가져오기
		int result = dao.deleteGroup(conn, groupVo, userBlogVo.getBlogNo());
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		List<GroupVo> groupVoList = dao.getGroupList(conn, userBlogVo); // 카테고리 그룹 리스트 가져오기
		
		// close
		JDBCTemplate.close(conn);
		
		return groupVoList;
	}

	// 블로그 그룹 카테고리 수정하기
	public List<GroupVo> editGroup(GroupVo groupVo, BlogVo blogVo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo userBlogVo = dao.getUserBlog(conn, blogVo.getBlogUrl()); // 블로그 정보 가져오기
		int result = dao.editGroup(conn, groupVo, userBlogVo.getBlogNo());
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		List<GroupVo> groupVoList = dao.getGroupList(conn, userBlogVo); // 카테고리 그룹 리스트 가져오기
		
		// close
		JDBCTemplate.close(conn);
		
		return groupVoList;
	}

	// 메인 포스트 카테고리 가져오기
	public List<CategoryVo> getCategoryList() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<CategoryVo> categoryVoList = dao.getCategoryList(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return categoryVoList;
	}

	// 구독한 블로그 정보 가져오기
	public List<BlogVo> getFollowBlogList(String blogUrl) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<BlogVo> blogVoList = dao.getFollowBlogList(conn, blogUrl);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVoList;
	}

	// 구독한 블로그 삭제
	public int deleteSubscribeList(FollowVo[] dataArray) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		int result = dao.deleteSubscribeList(conn, dataArray);
		
		// tx
		if(result == dataArray.length) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	
	// ------------------------------------------------------------------------------- admin
	
	// 모든 블로그 조회
	public List<BlogVo> getAllBlogInfo(PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<BlogVo> blogVoList = dao.getAllBlogInfo(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVoList;
	}

	// 전체 블로그 개수
	public int selectBlogCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		int cnt = dao.selectBlogCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
	// 블로그 넘버로 블로그 정보 가져오기
	public BlogVo selectBlogByNo(String no) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo blogVo = dao.selectBlogByNo(conn, no);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVo;
	}

	// 검색값에 따른 블로그 개수 조회
	public int selectSearchBlogCount(Map<String, String> param) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// 검증
		if(param.get("blogRep").equals("0")) {
			param.put("blogRep", "");
		}
		
		// dao
		BlogDao dao = new BlogDao();
		int cnt = dao.selectSearchBlogCount(conn, param);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	// 블로그 관리 검색하기
	public List<BlogVo> adminBlogSearch(Map<String, String> param, PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// 검증
		if(param.get("blogRep").equals("0")) {
			param.put("blogRep", "");
		}
		
		// dao
		BlogDao dao = new BlogDao();
		List<BlogVo> blogVoList = dao.adminBlogSearch(conn, param, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVoList;
	}
	
}
