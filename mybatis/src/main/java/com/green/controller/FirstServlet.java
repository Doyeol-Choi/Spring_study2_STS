package com.green.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO1;
import com.green.dao.MemberDAO2;
import com.green.vo.MemberVO;

@WebServlet("/FS")
public class FirstServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/lists.jsp";
		// 마이바티스를 사용하지 않은 예제
//		MemberDAO1 dao = MemberDAO1.getInstance();
		
		// 마이바티스를 사용한 예제
		MemberDAO2 dao = MemberDAO2.getInstance();
		List<MemberVO> lists = dao.selectAll();
		request.setAttribute("lists", lists);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
