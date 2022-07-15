package com.green.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO2;
import com.green.vo.MemberVO;

@WebServlet("/UM")
public class UpdateMemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/updateMemberForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberVO mVo = new MemberVO();
		
		mVo.setEmail(request.getParameter("email"));
		mVo.setName(request.getParameter("name"));
		mVo.setPassword(request.getParameter("password"));
		
		MemberDAO2 dao = MemberDAO2.getInstance();
		
		dao.updateMember(mVo);
		
		response.sendRedirect("http://localhost:8085/mybatis/FS");
	}

}
