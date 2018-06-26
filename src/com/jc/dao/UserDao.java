package com.jc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jc.entity.User;
import com.jc.util.DBUtils;

public class UserDao {
	/**
	 * 登陆的方法
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean findByUsernameAndPassword(String username,String password){
		String sql = "select * from user where username=? and password = ?";
		ResultSet rs = DBUtils.doQuery(sql, username,password);
		try {
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//查询全部
	public List<User> findAll(){
		List<User> list = new ArrayList<User>();
		String sql = "select * from user";
		ResultSet rs = DBUtils.doQuery(sql, null);
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				User user = new User(id, username, password, sex);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean adduser(User user){
		String sql = "insert into user (username,password,sex) values(?,?,?)";
		return DBUtils.doUpdate(sql, user.getUsername(),user.getPassword(),user.getSex());
	}
	
	public boolean deleteUser(int id){
		String sql = "delete from user where id = ?";
		return DBUtils.doUpdate(sql, id);
	}
	
	public User findById(int id){
		User user = null;
		String sql = "select * from user where id = ?";
		ResultSet rs = DBUtils.doQuery(sql, id);
		try {
			while(rs.next()){
				int uid = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				user = new User(uid, username, password, sex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean updateUser(User user){
		String sql = "update user set username=?,password=?,sex=? where id=?";
		return DBUtils.doUpdate(sql, user.getUsername(),
				user.getPassword(),user.getSex(),user.getId());
	}
}

