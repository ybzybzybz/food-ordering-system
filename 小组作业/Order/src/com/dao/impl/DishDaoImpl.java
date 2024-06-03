package com.dao.impl;

import java.sql.ResultSet;

import com.bean.DishBean;

public interface DishDaoImpl {

	
	
	public int saveDish(DishBean disBean);//增加菜品
	
	public int delDish(DishBean disBean);//删除菜品
	
	public int upDish(DishBean disBean,String name);//更改菜品
	
	public ResultSet showDish(String name);//查找菜品
	
	public ResultSet showDish();
}
