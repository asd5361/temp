package com.kh.app.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.app.member.dao.MemberDao;
import com.kh.app.member.vo.MemberVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberDao dao;
	
	private final SqlSessionTemplate sst;
	
	
	public int join(MemberVo vo) {
		return dao.join(sst,vo);
	}


	public List<MemberVo> list() {
		return dao.list(sst);
	}


	public MemberVo detail(String no) {
		return dao.detail(sst,no);
	}


	public int update(MemberVo vo) {
		return dao.update(sst,vo);
	}

}
