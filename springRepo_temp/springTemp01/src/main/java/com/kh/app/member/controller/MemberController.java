package com.kh.app.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;
	
	@PostMapping("join")
	public void join(MemberVo vo) {
		int result = service.join(vo);
		System.out.println("회원가입 결과:"+result);
	}
	
	@GetMapping("list")
	public void list() {
		List<MemberVo> voList = service.list();
		for (MemberVo memberVo : voList) {
			System.out.println(memberVo);
		}
	}
	
	@GetMapping("detail")
	public void detail(String no) {
		MemberVo vo = service.detail(no);
		System.out.println(vo);
	}
	
	@PostMapping("update")
	public void update(MemberVo vo) {
		int resunt = service.update(vo); 
		System.out.println("수정 결과 :"+resunt);
	}
}
