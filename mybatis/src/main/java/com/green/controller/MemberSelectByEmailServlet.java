package com.green.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO2;
import com.green.vo.MemberVO;

@WebServlet("/MSE")
public class MemberSelectByEmailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/inputEmail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		
		MemberDAO2 dao = MemberDAO2.getInstance();
		MemberVO mVo = dao.selectByEmail(email);
		
		request.setAttribute("mVo", mVo);
		
		request.getRequestDispatcher("member/memberInfo.jsp").forward(request, response);
	}

}
