package com.kh.app.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.app.member.vo.MemberVo;

@Repository
public class MemberDao {

	public int join(SqlSessionTemplate sst, MemberVo vo) {
		return sst.insert("MemberMapper.join",vo);
	}

	public List<MemberVo> list(SqlSessionTemplate sst) {
		return sst.selectList("MemberMapper.list");
	}

	public MemberVo detail(SqlSessionTemplate sst, String no) {
		return sst.selectOne("MemberMapper.detail",no);
	}

	public int update(SqlSessionTemplate sst, MemberVo vo) {
		return sst.update("MemberMapper.update",vo);
	}

}
