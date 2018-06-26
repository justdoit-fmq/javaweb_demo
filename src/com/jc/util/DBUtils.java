package com.jc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 连接数据库的工具类
 * @author 小白
 * @Date  2018年4月4日
 */
public class DBUtils {
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/justchange";
	private final static String username = "root";
	private final static String password = "root";
	/**
	 * 	static 使用static修饰的，可以直接 通过类名.方法名
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getCon() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,username,password);
		return con;
	}
	
	/**
	 * 数据库操作：增、删、该、查
	 * 这四个操作在java代码的执行方法中：
	 * 	executeUpdate()  增、删、该
	 * 	executeQuery() 查
	 */
	
	/**
	 * 观察有哪些不一样：
	 * 	select * from teacher 
	 * 	select * from student
	 * 	select * from teacher  where tno = 101
	 * 	select * from teacher  where tname = "老师" and tno = 101
	 * 	 
	 * 	1.sql语句不一样   ：以参数的形式传递sql语句
	 * 	2.参数数据类型不一样: Object
	 * 	3.参数个数不一样: ... 动态匹配参数
	 * 
	 * 封装一个查询的方法：
	 * 	返回的结果：无法确定： 把ResultSet返回回去，让调用者处理
	 */
	
	public static ResultSet doQuery(String sql,Object...params){
		//4.遍历结果集
		ResultSet rs = null;
		try {
			//1.2 加载驱动，获取连接
			Connection con = getCon();
			//3.执行sql语句
			PreparedStatement pstmt = con.prepareStatement(sql);
				//2.1  动态设置参数:可能有参数，可能没有参数
			if(params!=null){//当有参数的时候，我们要设置参数
				for (int i = 0; i < params.length; i++) {
					//设置参数
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static boolean doUpdate(String sql,Object...params){
		try {
			Connection con = getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			pstmt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
