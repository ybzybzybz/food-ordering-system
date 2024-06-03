package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.AdminBean;
import com.dao.impl.AdminDaoImpl;

import com.mysqld.Mysqld;
import com.util.DBUtilZ;

public class AdminDao implements   AdminDaoImpl{

	@Override
	public String LoginAdmin(String account, String password) {
		// TODO Auto-generated method stub
		String data[]= {account,password};
		ResultSet rs = Mysqld.QueryData("select * from s_user where s_admin=? and s_password=?", data);
		try {
			while(rs.next()) {
				
				String s_type=rs.getString("s_type");//获取权限
				if(s_type.equals("1")) {
					//管理员
					return "1";
				}else
				if(s_type.equals("2")) {
					//管理员
					return "2";
				}else {
					return "0";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}

	@Override
	public int RegistAdmin(AdminBean admin) {
		// TODO Auto-generated method stub
		String data[]= { admin.getAccount(), admin.getPassword(),"2"};

		int a = Mysqld.upDate("insert into s_user (s_admin,s_password,s_type) VALUES(?,?,?)", data);
		
		return a;
	}
	
	
	

}
