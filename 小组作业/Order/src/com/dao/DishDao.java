package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.DishBean;
import com.dao.impl.AdminDaoImpl;
import com.dao.impl.DishDaoImpl;
import com.mysqld.Mysqld;

public class DishDao implements   DishDaoImpl{

	public int saveDish(DishBean disBean) {
		// TODO Auto-generated method stub
		
		String data[]= {disBean.getDname(),disBean.getDkouwei(),disBean.getDjiage(),disBean.getD_jianjie()};

		int a = Mysqld.upDate("insert INTO s_dish (d_name,d_kouwei,d_jiage,d_jianjie) VALUES(?,?,?,?)", data);
		
		return a;
		
	}

	public int delDish(DishBean disBean) {
		// TODO Auto-generated method stub
		String data[]= {disBean.getDname()};

		int a = Mysqld.upDate("DELETE FROM s_dish where d_name=?", data);
		
		return a;
	
	}

	public int upDish(DishBean disBean, String name) {
		// TODO Auto-generated method stub
		String data[]= {disBean.getDname(),disBean.getDkouwei(),disBean.getD_jianjie(),disBean.getD_jianjie(),name};

		int a = Mysqld.upDate("UPDATE s_dish set d_name=?,d_kouwei=?,d_jiage=?,d_jianjie=? where d_name=?", data);
		
		return a;
	}

	@Override
	public ResultSet showDish(String name) {
		// TODO Auto-generated method stub
		String data[]= {name};
		ResultSet rs = Mysqld.QueryData("select * from s_dish where d_name=?", data);
		return rs;
	}
	@Override
	public ResultSet showDish() {
		// TODO Auto-generated method stub
	
		ResultSet rs = Mysqld.QueryData("select * from s_dish ", null);
		return rs;
	}
	
	
	
	
	//查询所有的厅位 只是显示空闲的厅位
	
	
	
	

}
