package com.jc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jc.dao.UserDao;
import com.jc.entity.User;

public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//2.获取参数，先获取op参数，在判断操作
		String op = request.getParameter("op");
		if("login".equals(op)){
			login(request,response);
		}else if("adduser".equals(op)){
			adduser(request,response);
		}else if("findAll".equals(op)){
			findAll(request,response);
		}
	}
	//查询全部的方法
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 3.调用dao方法
		UserDao userDao = new UserDao();
		List<User> list = userDao.findAll();
		//4.将结果放入域，给页面
		request.setAttribute("list", list);
		request.getRequestDispatcher("hy_list.jsp").forward(request, response);	
	}

	//添加用户
	private void adduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 2.获取登陆的请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		// 3.调用dao方法
		UserDao userDao = new UserDao();
		User user = new User(username,password,sex);
		boolean flag = userDao.adduser(user);
		// 4.响应
		if (flag) {
			// 登陆成功--->成功页面(首页)
			request.getRequestDispatcher("UserServlet?op=findAll").forward(request, response);	
		}
	}
	//登陆操作
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//2.获取登陆的请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//3.调用dao方法
		UserDao userDao = new UserDao();
		boolean flag = userDao.findByUsernameAndPassword(username, password);
		//4.响应
		if(flag){
			//登陆成功--->成功页面(首页)
			response.sendRedirect("index.jsp");
		}else{
			//回到登陆页面
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
}
