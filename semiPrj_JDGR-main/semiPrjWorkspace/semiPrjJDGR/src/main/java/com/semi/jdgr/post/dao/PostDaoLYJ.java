package com.semi.jdgr.post.dao;

public class PostDaoLYJ {
	
	//SQL
	String sql = "SELECT * FROM POST P JOIN BLOG  B ON  P .POST_NO = B .BLOG_NO JOIN CATEGORY_LIST CL ON CL.CATEGORY_NO = P.CATEGORY_NO JOIN MEMBER M ON M.MEM_NO = B.MEM_NO";
	PreparedStatement pstmt = conn.preparestatement(sql);
	ResultSet rs = pstmt.executeQuery();
	
	//rs
	List<PostVo> PostVoList = new ArrayList<PostVo>();
	while(rs.next()) {
	//close

}
