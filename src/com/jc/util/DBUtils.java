package com.jc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �������ݿ�Ĺ�����
 * @author С��
 * @Date  2018��4��4��
 */
public class DBUtils {
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/justchange";
	private final static String username = "root";
	private final static String password = "root";
	/**
	 * 	static ʹ��static���εģ�����ֱ�� ͨ������.������
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
	 * ���ݿ����������ɾ���á���
	 * ���ĸ�������java�����ִ�з����У�
	 * 	executeUpdate()  ����ɾ����
	 * 	executeQuery() ��
	 */
	
	/**
	 * �۲�����Щ��һ����
	 * 	select * from teacher 
	 * 	select * from student
	 * 	select * from teacher  where tno = 101
	 * 	select * from teacher  where tname = "��ʦ" and tno = 101
	 * 	 
	 * 	1.sql��䲻һ��   ���Բ�������ʽ����sql���
	 * 	2.�����������Ͳ�һ��: Object
	 * 	3.����������һ��: ... ��̬ƥ�����
	 * 
	 * ��װһ����ѯ�ķ�����
	 * 	���صĽ�����޷�ȷ���� ��ResultSet���ػ�ȥ���õ����ߴ���
	 */
	
	public static ResultSet doQuery(String sql,Object...params){
		//4.���������
		ResultSet rs = null;
		try {
			//1.2 ������������ȡ����
			Connection con = getCon();
			//3.ִ��sql���
			PreparedStatement pstmt = con.prepareStatement(sql);
				//2.1  ��̬���ò���:�����в���������û�в���
			if(params!=null){//���в�����ʱ������Ҫ���ò���
				for (int i = 0; i < params.length; i++) {
					//���ò���
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
