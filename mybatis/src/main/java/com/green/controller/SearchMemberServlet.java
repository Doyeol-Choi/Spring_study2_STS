package com.green.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO2;
import com.green.vo.MemberVO;

@WebServlet("/SM")
public class SearchMemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/searchMemberForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		MemberVO mVo = new MemberVO();
		mVo.setEmail(email);
		mVo.setName(name);
		
		MemberDAO2 dao = MemberDAO2.getInstance();
		List<MemberVO> list = dao.searchMember(mVo);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("member/searchMemberInfo.jsp").forward(request, response);
	}

}
