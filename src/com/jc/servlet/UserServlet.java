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
		//1.���ñ���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//2.��ȡ�������Ȼ�ȡop���������жϲ���
		String op = request.getParameter("op");
		if("login".equals(op)){
			login(request,response);
		}else if("adduser".equals(op)){
			adduser(request,response);
		}else if("findAll".equals(op)){
			findAll(request,response);
		}
	}
	//��ѯȫ���ķ���
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 3.����dao����
		UserDao userDao = new UserDao();
		List<User> list = userDao.findAll();
		//4.����������򣬸�ҳ��
		request.setAttribute("list", list);
		request.getRequestDispatcher("hy_list.jsp").forward(request, response);	
	}

	//����û�
	private void adduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 2.��ȡ��½���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		// 3.����dao����
		UserDao userDao = new UserDao();
		User user = new User(username,password,sex);
		boolean flag = userDao.adduser(user);
		// 4.��Ӧ
		if (flag) {
			// ��½�ɹ�--->�ɹ�ҳ��(��ҳ)
			request.getRequestDispatcher("UserServlet?op=findAll").forward(request, response);	
		}
	}
	//��½����
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//2.��ȡ��½���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//3.����dao����
		UserDao userDao = new UserDao();
		boolean flag = userDao.findByUsernameAndPassword(username, password);
		//4.��Ӧ
		if(flag){
			//��½�ɹ�--->�ɹ�ҳ��(��ҳ)
			response.sendRedirect("index.jsp");
		}else{
			//�ص���½ҳ��
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
}
