package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

// 인터페이스 장점 : 추상화,다형성 
// 자바에서 4대지향점 APIE
public interface MemberController {
	
	
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
