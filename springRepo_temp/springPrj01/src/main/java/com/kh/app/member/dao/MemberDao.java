package com.kh.app.member.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	public int insert(MemberVo vo, SqlSessionTemplate stt) {
		return stt.insert("MemberMapper.insert");
	}

}
