package com.green.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO2;
import com.green.vo.MemberVO;

@WebServlet("/FSM")
public class ForeachSearchMemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> names = new ArrayList<>();
		names.add("김길동");
		names.add("고길동");
		names.add("이길동");
		names.add("조길동");
		
		MemberDAO2 dao = MemberDAO2.getInstance();
		List<MemberVO>list = dao.foreachSearchMember(names);
		
		request.setAttribute("lists", list);
		request.getRequestDispatcher("member/lists.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
