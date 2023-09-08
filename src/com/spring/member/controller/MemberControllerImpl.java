package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;

//인터페이스를 구현한 클래스
public class MemberControllerImpl extends MultiActionController implements MemberController {
	
	//DI, 스프링 프레임워크
	private MemberService memberService;

	//빈 생성 후, 초기화 작업
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/* 데이터와 뷰를 전달하는 ModelAndView */	
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//getViewName 특정 문자열 추출하는 로직(pro21참고)
		String viewName = getViewName(request);
		
		// controller -> service DB에 접근하여 데이터 가져오기
		List membersList = memberService.listMembers();
		
		// 데이터를 전달하는 로직
		ModelAndView mav = new ModelAndView(viewName);
		// 뷰를 전달하는 로직
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}
}